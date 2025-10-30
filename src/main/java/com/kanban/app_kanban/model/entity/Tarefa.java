package com.kanban.app_kanban.model.entity;

import com.kanban.app_kanban.model.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataConclusao;

    private LocalDateTime dataPrevista;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
