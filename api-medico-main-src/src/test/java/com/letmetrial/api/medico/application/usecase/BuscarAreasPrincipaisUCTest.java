package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.BuscarAreasPrincipaisUC.AreaDTO;
import com.letmetrial.api.medico.application.usecase.BuscarAreasPrincipaisUC.BuscarAreasPrincipaisUCMapper;
import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.service.AreaService;

@SpringBootTest
public class BuscarAreasPrincipaisUCTest {

    @Mock
    AreaService areaService;

    @Mock
    BuscarAreasPrincipaisUCMapper mapper;

    @InjectMocks
    BuscarAreasPrincipaisUC buscarAreasPrincipaisUC;

    @Test
    public void runSucesso() {

        Area area = new Area();
        AreaDTO areaDTO = new AreaDTO(null, null, null);

        List<Area> areas = new ArrayList<>(List.of(area));

        List<AreaDTO> areaDTOs = new ArrayList<>(List.of(areaDTO));

        when(areaService.buscarAreasPrincipais())
                .thenReturn(areas);

        when(mapper.toAreaDTOs(areas))
                .thenReturn(areaDTOs);

        assertEquals(areaDTOs, buscarAreasPrincipaisUC.run());

        verify(areaService, times(1)).buscarAreasPrincipais();
    }

}
