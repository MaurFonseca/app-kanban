package com.kanban.app_kanban.dto.tarefa;

import java.time.LocalDateTime;

public record TarefaRequest (String titulo, String descricao, LocalDateTime dataPrevista, Long idCard){
}
