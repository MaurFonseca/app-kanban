package com.kanban.app_kanban.repository;

import com.kanban.app_kanban.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


}
