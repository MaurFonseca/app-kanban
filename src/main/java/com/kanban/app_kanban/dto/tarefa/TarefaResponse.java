package com.kanban.app_kanban.dto.tarefa;


import com.kanban.app_kanban.model.enums.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponse(Long idTarefa,
                             String titulo,
                             String descricao,
                             Long idCard,
                             StatusTarefa statusTarefa,
                             LocalDateTime dataCriacao,
                             LocalDateTime dataPrevista,
                             LocalDateTime dataFinal) {
}
