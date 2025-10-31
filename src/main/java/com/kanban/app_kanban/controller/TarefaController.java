package com.kanban.app_kanban.controller;

import com.kanban.app_kanban.dto.tarefa.TarefaAtualizar;
import com.kanban.app_kanban.dto.tarefa.TarefaMoverCard;
import com.kanban.app_kanban.dto.tarefa.TarefaRequest;
import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/{id}/update/status")
    public ResponseEntity<TarefaResponse> autalizarStatus(@PathVariable Long id, @Valid @RequestBody TarefaAtualizar atualizar){
        return ResponseEntity.ok().body(tarefaService.autalizarStatus(id, atualizar));
    }

    @PutMapping("/{id}/update/card")
    public ResponseEntity<TarefaResponse> atualizarCard(@PathVariable Long id, @Valid @RequestBody TarefaMoverCard moverCard){
        return ResponseEntity.ok().body(tarefaService.atualizarCardTarefa(id, moverCard));
    }

}
