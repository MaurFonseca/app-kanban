package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.board.BoardRequest;
import com.kanban.app_kanban.dto.board.BoardResponse;
import com.kanban.app_kanban.service.BoardService;
import com.kanban.app_kanban.service.CardService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<BoardResponse>> buscarTodos(){
        return ResponseEntity.ok().body(boardService.findAll());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<BoardResponse> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok().body(boardService.findByNome(nome));
    }

    @PostMapping("/create")
    public ResponseEntity<BoardResponse> criarBoard(@Valid @RequestBody BoardRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/boards/create").buildAndExpand().toUri();
        BoardResponse board = boardService.criarBoard(request);
        return ResponseEntity.created(uri).body(board);
    }

    @PutMapping("/update/{nome}")
    public ResponseEntity<BoardResponse> atualizarNomeBoard(@PathVariable String nome, @Valid @RequestBody BoardRequest request){
        return ResponseEntity.ok().body(boardService.atualizarBoard(nome, request));
    }
}
