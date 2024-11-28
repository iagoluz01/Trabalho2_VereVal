package com.letmetrial.api.medico.adapter.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "pacientes")
@Entity
public class PacienteJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Pattern(regexp = "\\d{8}")
    @Column(nullable = false, unique = false)
    private String cep;

    @Pattern(regexp = "^(?!\\s*$).+")
    @Column(nullable = false)
    private String apelido;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id")
    private MedicoJpa medico;

    @ManyToMany
    @JoinTable(name = "pacientes_estudos", joinColumns = @JoinColumn(name = "paciente_id"), inverseJoinColumns = @JoinColumn(name = "estudo_id"))
    private Collection<EstudoJpa> estudosValidados;

}
