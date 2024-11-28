package com.letmetrial.api.medico.adapter.entity;

import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.TipoResposta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "criterios_estudos")
@Entity
public class CriterioEstudoJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private boolean opcional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoResposta respostaEsperada;

    @ManyToOne(optional = false)
    private CriterioJpa criterio;

    @ManyToOne(optional = false)
    private EstudoJpa estudo;

}
