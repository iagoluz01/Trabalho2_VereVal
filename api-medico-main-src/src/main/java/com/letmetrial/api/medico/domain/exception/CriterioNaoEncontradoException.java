package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Criterio;

public class CriterioNaoEncontradoException extends RecursoNaoEncontradoException {

    private CriterioNaoEncontradoException(String consulta) {
        super(Criterio.class, consulta);
    }

    public static CriterioNaoEncontradoException BUSCA_POR_ID(UUID id) {
        return new CriterioNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

    public static CriterioNaoEncontradoException BUSCA_NA_LISTA_DE_CRITERIOS_RESPONDIDOS(UUID id) {
        return new CriterioNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
