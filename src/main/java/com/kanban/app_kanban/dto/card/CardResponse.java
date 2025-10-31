package com.kanban.app_kanban.dto.card;

import com.kanban.app_kanban.dto.tarefa.TarefaResponse;
import com.kanban.app_kanban.model.entity.Tarefa;

import java.util.List;

public record CardResponse (String nomeCard, List<TarefaResponse> tarefas){
}
