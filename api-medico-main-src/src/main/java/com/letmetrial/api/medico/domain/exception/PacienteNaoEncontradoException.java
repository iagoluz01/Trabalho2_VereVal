package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;

public class PacienteNaoEncontradoException extends RecursoNaoEncontradoException {

    private PacienteNaoEncontradoException(String consulta) {
        super(Paciente.class, consulta);
    }

    public static PacienteNaoEncontradoException BUSCA_POR_ID_E_MEDICO(UUID pacienteId, Medico medico) {
        return new PacienteNaoEncontradoException(
                String.format("[id=%s,medicoId=%s]", pacienteId.toString(), medico.getId().toString()));
    }

}
