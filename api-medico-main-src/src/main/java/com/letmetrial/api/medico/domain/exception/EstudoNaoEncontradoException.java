package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Estudo;

public class EstudoNaoEncontradoException extends RecursoNaoEncontradoException {

    private EstudoNaoEncontradoException(String consulta) {
        super(Estudo.class, consulta);
    }

    public static EstudoNaoEncontradoException BUSCA_POR_ID(UUID id) {
        return new EstudoNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
