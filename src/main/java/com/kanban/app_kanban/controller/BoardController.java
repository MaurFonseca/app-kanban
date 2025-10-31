package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.board.BoardResponse;
import com.kanban.app_kanban.service.BoardService;
import com.kanban.app_kanban.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
