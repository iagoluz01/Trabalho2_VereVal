package com.letmetrial.api.medico.domain.service;

import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.exception.PacienteInvalidoExpection;
import com.letmetrial.api.medico.domain.exception.PacienteNaoEncontradoException;
import com.letmetrial.api.medico.domain.exception.RecursoInvalidoExpection.Atributo;
import com.letmetrial.api.medico.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public Paciente cadastrar(Paciente paciente) {

        if (paciente.getMedico() == null || paciente.getMedico().getId() == null)
            throw new PacienteInvalidoExpection(new Atributo("medico",
                    paciente.getMedico() == null ? "null" : paciente.getMedico().toString()));

        return pacienteRepository.salvar(paciente);
    }

    public Collection<Paciente> buscarTodosPorMedico(Medico medico) {
        return pacienteRepository.buscarTodosPacientesPorMedico(medico);
    }

    public Collection<Estudo> buscarEstudosValidadosPorPacienteEMedicoId(UUID pacienteId, Medico medico) {
        return buscarPorIdMedico(pacienteId, medico).getEstudosValidados();
    }

    public Paciente atualizar(Paciente paciente) {
        return pacienteRepository.salvar(paciente);
    }

    public Paciente buscarPorIdMedico(UUID pacienteId, Medico medico) {
        return pacienteRepository
                .buscarPorMedicoEId(medico, pacienteId)
                .orElseThrow(() -> PacienteNaoEncontradoException.BUSCA_POR_ID_E_MEDICO(pacienteId, medico));
    }
}