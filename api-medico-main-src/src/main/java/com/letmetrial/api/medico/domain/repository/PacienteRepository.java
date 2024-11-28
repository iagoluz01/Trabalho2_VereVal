package com.letmetrial.api.medico.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;

public interface PacienteRepository {

    Paciente salvar(Paciente paciente);

    Collection<Paciente> buscarTodosPacientesPorMedico(Medico medico);

    Optional<Paciente> buscarPorMedicoEId(Medico medico, UUID pacienteId);
}