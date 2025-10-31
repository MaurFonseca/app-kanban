package com.kanban.app_kanban.dto.board;

import com.kanban.app_kanban.dto.card.CardResponse;

import java.util.List;

public record BoardResponse(Long id,
                            String nome,
                            List<CardResponse> cards) {
}
