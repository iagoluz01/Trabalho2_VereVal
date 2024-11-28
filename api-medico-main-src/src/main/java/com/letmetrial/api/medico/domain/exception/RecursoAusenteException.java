package com.letmetrial.api.medico.domain.exception;

public abstract class RecursoAusenteException extends RuntimeException {

    protected <T> RecursoAusenteException(T clazz, String identificador) {
        super(String.format("Recurso (%s) ausente, com o seguinte identificador (%s)",
                clazz.toString(),
                identificador));
    }

}
