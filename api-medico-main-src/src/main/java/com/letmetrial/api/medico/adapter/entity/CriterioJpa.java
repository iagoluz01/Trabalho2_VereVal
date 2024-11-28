package com.letmetrial.api.medico.adapter.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "criterios")
@Entity
public class CriterioJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String pergunta;

}
