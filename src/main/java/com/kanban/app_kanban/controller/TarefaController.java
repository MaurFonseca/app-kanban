package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.tarefa.TarefaRequest;
import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    public TarefaService tarefaService;

    @PostMapping("/create")
    public ResponseEntity<TarefaResponse> criarTarefa(@RequestBody TarefaRequest request){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tarefas/create").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(tarefaService.criarTarefa(request));
    }

}
