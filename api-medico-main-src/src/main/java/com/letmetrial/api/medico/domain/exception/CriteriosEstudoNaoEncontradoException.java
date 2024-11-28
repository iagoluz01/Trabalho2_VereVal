package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Criterio;

public class CriteriosEstudoNaoEncontradoException extends RecursoNaoEncontradoException {

    private CriteriosEstudoNaoEncontradoException(String consulta) {
        super(Criterio.class, consulta);
    }

    public static CriteriosEstudoNaoEncontradoException BUSCA_NO_ESTUDO(UUID id) {
        return new CriteriosEstudoNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}