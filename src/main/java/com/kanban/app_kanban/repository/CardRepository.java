package com.kanban.app_kanban.repository;


import com.kanban.app_kanban.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
