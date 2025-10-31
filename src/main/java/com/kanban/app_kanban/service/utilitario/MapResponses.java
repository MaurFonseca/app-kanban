package com.kanban.app_kanban.service.utilitario;

import com.kanban.app_kanban.dto.board.BoardResponse;
import com.kanban.app_kanban.dto.card.CardResponse;
import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Card;
import com.kanban.app_kanban.model.entity.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class MapResponses {

    public BoardResponse toBoardResponse(Board board){
        BoardResponse response = new BoardResponse(board.getId(), board.getNome(),
                board.getCard().stream()
                        .map(this::toCardResponse).toList());
        return response;
    }

    public CardResponse toCardResponse(Card card){
        CardResponse cardResponse = new CardResponse(card.getNome(),
                card.getTarefas().stream()
                        .map(this::tarefaResponse).toList());
        return cardResponse;
    }

    public TarefaResponse tarefaResponse(Tarefa tarefa){
        TarefaResponse tarefaResponse = new TarefaResponse(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getCard().getId(),
                tarefa.getStatus(), tarefa.getDataCriacao(), tarefa.getDataPrevista(), tarefa.getDataConclusao());
        return tarefaResponse;
    }
}
