package com.letmetrial.api.medico.adapter.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "estudos")
@Entity
public class EstudoJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String link;

    @Column(length = 1000)
    private String informacoes;

    @Column
    private LocalDate dataPublicacao;

    @ManyToMany
    @JoinTable(name = "estudo_area", joinColumns = @JoinColumn(name = "estudo_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<AreaJpa> areas;

}
