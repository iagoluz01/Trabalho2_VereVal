package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

public class RespostaDuplicadaException extends RuntimeException {

    private RespostaDuplicadaException(String identificador) {
        super(String.format("Respostas duplicadas para o mesmo (%s)", identificador));
    }

    public static RespostaDuplicadaException COM_CRITERIO_ID(UUID criterioId) {
        return new RespostaDuplicadaException(String.format("[criterioId=%s]", criterioId.toString()));
    }

}
