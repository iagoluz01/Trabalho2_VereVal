package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Paciente;

public class PacienteJaExistenteException extends RecursoJaExistenteException {

    private PacienteJaExistenteException(String identificador) {
        super(Paciente.class, identificador);
    }

    public static PacienteJaExistenteException COM_MEDICO_ID_E_CEP(UUID medicoId, String cep) {
        return new PacienteJaExistenteException(String.format("[medicoId=%s;cep=%s]", medicoId.toString(), cep));
    }

}
