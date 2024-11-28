package com.letmetrial.api.medico.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;

public interface RespostaRepository {

    Optional<Resposta> buscarPorCriterioIdEPacienteId(UUID criterioId, UUID pacienteId);

    Collection<Resposta> salvarTodas(Collection<Resposta> respostas);

    Collection<Resposta> buscarPorEstudoEPacienteId(UUID estudoId, UUID pacienteId);

    Collection<Resposta> buscarTodasRespostasPorPaciente(Paciente paciente);

}
