package com.letmetrial.api.medico.domain.exception;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RecursoInvalidoExpection extends RuntimeException {

    protected <T> RecursoInvalidoExpection(T clazz, Atributo... atributos) {
        super(String.format("Recurso (%s) inválido atributos -> (%s)",
                clazz.toString(),
                String.join(";", List.of(atributos).stream().map(Atributo::toString).collect(Collectors.toList()))));
    }

    public record Atributo(String nome, String valor) {

        public String toString() {
            return String.format("%s=%s", nome, valor);
        }

    }
}
