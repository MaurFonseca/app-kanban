package com.kanban.app_kanban.service;

import com.kanban.app_kanban.dto.card.CardRequest;
import com.kanban.app_kanban.dto.card.CardResponse;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Card;
import com.kanban.app_kanban.repository.BoardRepository;
import com.kanban.app_kanban.repository.CardRepository;
import com.kanban.app_kanban.service.utilitario.UsuarioPermitido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UsuarioPermitido usuarioPermitido;

    private CardResponse toCardResponse(Card card){
        CardResponse response = new CardResponse(card.getNome(), card.getBoard().getNome());
        return response;
    }

    public CardResponse criarCard(CardRequest request){
        Board board = boardRepository.findById(request.id()).orElseThrow(()-> new RuntimeException("Board não encontrado"));
        if (board.getUsuario().getId() == usuarioPermitido.getAuthenticatedUserId().getId()){
            Card card = new Card(null, request.nome(), board);
            cardRepository.save(card);
            return toCardResponse(card);
        }
        return null;
    }

}
