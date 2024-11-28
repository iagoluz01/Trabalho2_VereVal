package com.letmetrial.api.medico.domain.exception;

import com.letmetrial.api.medico.domain.entity.Area;

import java.util.UUID;

public class AreaNaoEncontradoException extends RecursoNaoEncontradoException {
    private AreaNaoEncontradoException(String consulta) {
        super(Area.class, consulta);
    }

    public static AreaNaoEncontradoException BUSCA_POR_ID(UUID id) {
        return new AreaNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }
}
