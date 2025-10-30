package com.kanban.app_kanban.dto.tarefa;


import com.kanban.app_kanban.model.enums.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponse(String nomeBoard,
                             String nomeCard,
                             String titulo,
                             String descricao,
                             StatusTarefa statusTarefa,
                             LocalDateTime dataCriacao,
                             LocalDateTime dataPrevista,
                             LocalDateTime dataFinal) {
}
