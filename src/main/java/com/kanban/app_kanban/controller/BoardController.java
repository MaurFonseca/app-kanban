package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> findAll(){
        return ResponseEntity.ok().body(boardService.findAll());
    }

}
