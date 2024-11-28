package com.letmetrial.api.medico.domain.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estudo {

    private UUID id;

    private String nome;

    private String link;

    private String informacoes;

    private LocalDate dataPublicacao;

    private Set<Area> areas;

}
