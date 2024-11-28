package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Medico;

public class MedicoNaoEncontradoException extends RecursoNaoEncontradoException {

    private MedicoNaoEncontradoException(String consulta) {
        super(Medico.class, consulta);
    }

    public static MedicoNaoEncontradoException BUSCA_POR_ID(UUID id) {
        return new MedicoNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
