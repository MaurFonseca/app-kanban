package com.kanban.app_kanban.repository;

import com.kanban.app_kanban.model.entity.Board;
import com.kanban.app_kanban.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUsuarioId(Long login);

    Board findByNome(String nome);

    Board findByCardId(Long id);
}
