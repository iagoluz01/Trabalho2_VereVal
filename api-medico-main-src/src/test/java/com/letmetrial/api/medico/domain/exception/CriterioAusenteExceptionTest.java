package com.letmetrial.api.medico.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.letmetrial.api.medico.domain.entity.Criterio;

public class CriterioAusenteExceptionTest {

    @Test
    public void testExceptionMessage() {
        UUID id = UUID.randomUUID();

        CriterioAusenteException exception = assertThrows(CriterioAusenteException.class, () -> {
            throw CriterioAusenteException.COM_ID(id);
        });

        String expectedMessage = String.format("Recurso (%s) ausente, com o seguinte identificador ([id=%s])", Criterio.class.toString(), id.toString());
        assertEquals(expectedMessage, exception.getMessage());
    }
}