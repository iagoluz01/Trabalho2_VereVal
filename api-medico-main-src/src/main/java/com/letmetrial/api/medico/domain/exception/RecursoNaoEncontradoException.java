package com.letmetrial.api.medico.domain.exception;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

    protected <T> RecursoNaoEncontradoException(T clazz, String consulta) {
        super(String.format("Recurso (%s) não encontrado dado a seguinte consulta (%s)", clazz.toString(), consulta));
    }

}
