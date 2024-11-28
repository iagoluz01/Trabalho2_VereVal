package com.letmetrial.api.medico.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Medico;

public class AreaComReferenciaCliclicaExceptionTest {

    @Test
    public void testExceptionMessage() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Area principal = new Area();
        principal.setId(id1);

        Area atual = new Area();
        atual.setId(id2);


        principal.setAreaPrincipal(atual);
        

        AreaComReferenciaCliclicaException exception = assertThrows(AreaComReferenciaCliclicaException.class, () -> {
            atual.setAreaPrincipal(principal);
        });

        String expectedMessage = String.format("Recurso (%s) possui seguinte referencia ciclica (%s)", Medico.class.toString(), id1.toString() + " (->) " + id2.toString() + " (->) " + id2.toString());
        assertEquals(expectedMessage, exception.getMessage());
    }
}