package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.exception.AreaNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.AreaRepository;

@SpringBootTest
public class AreaServiceTest {

    @Mock
    AreaRepository areaRepository;

    @InjectMocks
    AreaService areaService;

    @Test
    public void buscarPorIdSucesso() {
        UUID id = UUID.randomUUID();
        Area area = new Area();

        when(areaRepository.buscarPorId(id)).thenReturn(Optional.of(area));
        assertEquals(area, areaService.buscarPorId(id));

        verify(areaRepository, times(1)).buscarPorId(any());
    }

    @Test
    public void buscarPorIdAreaNaoEncontrada() {
        UUID id = UUID.randomUUID();

        when(areaRepository.buscarPorId(id)).thenReturn(Optional.empty());
        assertThrows(AreaNaoEncontradoException.class, () -> areaService.buscarPorId(id));

        verify(areaRepository, times(1)).buscarPorId(any());
    }

    @Test
    public void buscarSubAreasSucesso() {

        UUID id = UUID.randomUUID();

        Area area = new Area();
        area.setId(id);

        List<Area> areas = new ArrayList<>(List.of(area));

        when(areaRepository.buscarPorId(id))
                .thenReturn(Optional.of(area));

        when(areaRepository.buscarSubAreas(area))
                .thenReturn(areas);

        assertEquals(areas, areaService.buscarSubAreas(id));

        verify(areaRepository, times(1)).buscarPorId(any());
        verify(areaRepository, times(1)).buscarSubAreas(any());

    }

    @Test
    public void buscarAreasPrincipaisSucesso() {

        UUID id = UUID.randomUUID();

        Area area = new Area();
        area.setId(id);

        List<Area> areas = new ArrayList<>(List.of(area));

        when(areaRepository.buscarAreasPrincipais())
                .thenReturn(areas);

        assertEquals(areas, areaService.buscarAreasPrincipais());

        verify(areaRepository, times(1)).buscarAreasPrincipais();

    }

}
