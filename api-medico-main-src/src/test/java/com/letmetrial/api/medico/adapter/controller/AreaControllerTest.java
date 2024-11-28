package com.letmetrial.api.medico.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.letmetrial.api.medico.application.usecase.BuscarAreasPrincipaisUC;
import com.letmetrial.api.medico.application.usecase.BuscarSubAreasDeUmaAreaUC;

@SpringBootTest
public class AreaControllerTest {

    @Mock
    BuscarSubAreasDeUmaAreaUC buscarSubAreasDeUmaAreaUC;

    @Mock
    BuscarAreasPrincipaisUC buscarAreasPrincipaisUC;

    @InjectMocks
    AreaController areaController;

    @Test
    public void buscarSubAreasSucesso() {

        UUID id = UUID.randomUUID();
        BuscarSubAreasDeUmaAreaUC.AreaDTO area = new BuscarSubAreasDeUmaAreaUC.AreaDTO(id, null, null);
        List<BuscarSubAreasDeUmaAreaUC.AreaDTO> areas = new ArrayList<>(List.of(area));

        when(buscarSubAreasDeUmaAreaUC.run(id))
                .thenReturn(areas);

        assertEquals(ResponseEntity.ok(areas), areaController.buscarSubAreas(id));

        verify(buscarSubAreasDeUmaAreaUC, times(1)).run(id);

    }

    @Test
    public void buscarAreasPrincipaisSucesso() {

        BuscarAreasPrincipaisUC.AreaDTO area = new BuscarAreasPrincipaisUC.AreaDTO(UUID.randomUUID(), null, null);
        List<BuscarAreasPrincipaisUC.AreaDTO> areas = new ArrayList<>(List.of(area));

        when(buscarAreasPrincipaisUC.run())
                .thenReturn(areas);

        assertEquals(ResponseEntity.ok(areas), areaController.buscarAreasPrincipais());

        verify(buscarAreasPrincipaisUC, times(1)).run();

    }

}
