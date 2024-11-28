package com.letmetrial.api.medico.adapter.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.exception.ReferenciaCiclicaException;

@SpringBootTest
public class AreaJpaTest {

    AreaJpa area1;

    AreaJpa area2;

    AreaJpa area3;

    @BeforeEach
    public void instanciaAreas() {
        this.area1 = new AreaJpa();
        this.area1.setId(UUID.randomUUID());
        this.area1.setNome("area1");

        this.area2 = new AreaJpa();
        this.area2.setId(UUID.randomUUID());
        this.area2.setNome("area2");

        this.area3 = new AreaJpa();
        this.area3.setId(UUID.randomUUID());
        this.area3.setNome("area3");
    }

    @Test
    public void setAreaPrincipalSemCiclo() {
        area3.setAreaPrincipal(area2);
        area2.setAreaPrincipal(area1);

        assertEquals(area1, area2.getAreaPrincipal());
        assertEquals(area2, area3.getAreaPrincipal());
    }

    @Test
    public void setAreaPrincipalComCiclo() {
        area3.setAreaPrincipal(area2);
        area2.setAreaPrincipal(area1);

        assertEquals(area1, area2.getAreaPrincipal());
        assertEquals(area2, area3.getAreaPrincipal());

        assertThrows(ReferenciaCiclicaException.class, () -> area1.setAreaPrincipal(area3));
    }

    @Test
    public void setAreaPrincipalComAutoReferencia() {
        assertThrows(ReferenciaCiclicaException.class, () -> area1.setAreaPrincipal(area1));
    }

    @Test
    public void setAreaPrincipalComReferenciaNula() {
        AreaJpa area1 = new AreaJpa();
        area1.setId(UUID.randomUUID());

        assertDoesNotThrow(() -> area1.setAreaPrincipal(null));
    }

}
