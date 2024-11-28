package com.letmetrial.api.medico.domain.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paciente {

    private UUID id;

    private String cep;

    private String apelido;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private Medico medico;

    private Collection<Estudo> estudosValidados;

}
