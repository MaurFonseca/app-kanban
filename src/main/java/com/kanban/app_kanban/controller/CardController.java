package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.card.CardRequest;
import com.kanban.app_kanban.dto.card.CardResponse;
import com.kanban.app_kanban.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<CardResponse> criarCard(@RequestBody CardRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/boards/cards/create").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(cardService.criarCard(request));
    }
}
