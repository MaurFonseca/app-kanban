package com.kanban.app_kanban.service;

import com.kanban.app_kanban.dto.tarefa.TarefaAtualizar;
import com.kanban.app_kanban.dto.tarefa.TarefaMoverCard;
import com.kanban.app_kanban.dto.tarefa.TarefaRequest;
import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Card;
import com.kanban.app_kanban.model.entity.Tarefa;
import com.kanban.app_kanban.model.enums.StatusTarefa;
import com.kanban.app_kanban.repository.BoardRepository;
import com.kanban.app_kanban.repository.CardRepository;
import com.kanban.app_kanban.repository.TarefaRepository;
import com.kanban.app_kanban.service.utilitario.MapResponses;
import com.kanban.app_kanban.service.utilitario.UsuarioPermitido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UsuarioPermitido usuarioPermitido;

    @Autowired
    private MapResponses mapResponses;

    public TarefaResponse criarTarefa(TarefaRequest request){
        log.info("idCard recebido: " + request.idCard());
        if(request.idCard() == null) {
            throw new IllegalArgumentException("idCard não pode ser nulo");
        }
        Board board = boardRepository.findByCardId(request.idCard());
        if (board == null) {
            throw new RuntimeException("Board não encontrado para o card " + request.idCard());
        }
        if(usuarioPermitido.getAuthenticatedUserId().getId().equals(board.getUsuario().getId())){
            Card card = cardRepository.findById(request.idCard())
                    .orElseThrow(() -> new RuntimeException("Card não encontrado"));

            if(!card.getBoard().getId().equals(board.getId())) {
                throw new RuntimeException("O card não pertence a este board");
            }
            Tarefa tarefa = Tarefa.builder()
                    .titulo(request.titulo())
                    .descricao(request.descricao())
                    .dataPrevista(request.dataPrevista())
                    .dataCriacao(LocalDateTime.now())
                    .status(StatusTarefa.AGUARDANDO)
                    .card(card)
                    .build();
            tarefaRepository.save(tarefa);
            return mapResponses.tarefaResponse(tarefa);
        }
        throw new RuntimeException("Usuário não autorizado para criar tarefa neste board");
    }

    public TarefaResponse autalizarStatus(Long id ,TarefaAtualizar atualizar){
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getCard().getBoard().getUsuario().getId().equals(usuarioPermitido.getAuthenticatedUserId().getId())){
            throw new RuntimeException("Acesso negado");
        }
        if (tarefa.getStatus() == atualizar.statusTarefa()){
            return null;
        }
        tarefa.setStatus(atualizar.statusTarefa());
        tarefaRepository.save(tarefa);
        return mapResponses.tarefaResponse(tarefa);
    }

    public TarefaResponse atualizarCardTarefa(Long id, TarefaMoverCard moverCard){
        Card card = cardRepository.findById(moverCard.idCard()).orElseThrow(()-> new RuntimeException("Card Não encontrado"));
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getCard().getBoard().getUsuario().getId().equals(usuarioPermitido.getAuthenticatedUserId().getId())){
            throw new RuntimeException("Acesso negado");
        }
        if (tarefa.getCard().getId().equals(moverCard.idCard())){
            return null;
        }
        tarefa.setCard(card);
        tarefaRepository.save(tarefa);
        return mapResponses.tarefaResponse(tarefa);
    }
}
