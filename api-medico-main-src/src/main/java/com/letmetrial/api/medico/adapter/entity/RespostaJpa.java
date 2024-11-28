package com.letmetrial.api.medico.adapter.entity;

import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.TipoResposta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "respostas", uniqueConstraints = { @UniqueConstraint(columnNames = { "paciente_id", "criterio_id" }) })
@Entity
public class RespostaJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoResposta resposta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private PacienteJpa paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "criterio_id")
    private CriterioJpa criterio;

}