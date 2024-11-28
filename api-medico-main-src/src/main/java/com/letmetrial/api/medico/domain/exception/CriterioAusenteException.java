package com.letmetrial.api.medico.domain.exception;

import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Criterio;

public class CriterioAusenteException extends RecursoAusenteException{
        private CriterioAusenteException(String consulta) {
        super(Criterio.class, consulta);
    }

    public static CriterioAusenteException COM_ID(UUID id) {
        return new CriterioAusenteException(String.format("[id=%s]", id.toString()));
    }
}
