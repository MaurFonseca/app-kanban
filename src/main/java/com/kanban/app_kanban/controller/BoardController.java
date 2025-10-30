package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.card.CardRequest;
import com.kanban.app_kanban.dto.card.CardResponse;
import com.kanban.app_kanban.dto.tarefa.TarefaRequest;
import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Card;
import com.kanban.app_kanban.service.BoardService;
import com.kanban.app_kanban.service.CardService;
import com.kanban.app_kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Board>> findAll(){
        return ResponseEntity.ok().body(boardService.findAll());
    }

    @Autowired
    public TarefaService tarefaService;

    @PostMapping("/cards/create")
    public ResponseEntity<CardResponse> criarCard(@RequestBody CardRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/boards/cards/create").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(cardService.criarCard(request));
    }

    @PostMapping("/tarefas/create")
    public ResponseEntity<TarefaResponse> criarTarefa(@RequestBody TarefaRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tarefas/create").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(tarefaService.criarTarefa(request));
    }

}
