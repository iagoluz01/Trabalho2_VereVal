package com.letmetrial.api.medico.adapter.entity;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "medicos")
@Entity
public class MedicoJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Pattern(regexp = "\\d{4,10}\\/[A-Z]{2}")
    @Column(nullable = false, unique = true)
    private String crm;

    @ManyToOne
    private AreaJpa area;

}
