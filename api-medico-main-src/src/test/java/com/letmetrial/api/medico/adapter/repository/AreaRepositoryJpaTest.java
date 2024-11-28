package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.AreaJpa;
import com.letmetrial.api.medico.domain.entity.Area;

@SpringBootTest
public class AreaRepositoryJpaTest {

    @Mock
    AreaRepositoryJpa.AreaRepositoryJpaQueries areaRepositoryJpaQueries;

    @Spy
    AreaRepositoryJpa.AreaRepositoryJpaMapper mapper = Mappers
            .getMapper(AreaRepositoryJpa.AreaRepositoryJpaMapper.class);

    @InjectMocks
    AreaRepositoryJpa areaRepositoryJpa;

    @Test
    public void buscarPorIdSucesso() {

        UUID areaId = UUID.randomUUID();

        Area areaDomain = new Area();

        AreaJpa areaJpa = new AreaJpa();

        when(mapper.toArea(areaJpa))
                .thenReturn(areaDomain);

        when(areaRepositoryJpaQueries.findById(Objects.requireNonNull(areaId)))
                .thenReturn(Optional.of(areaJpa));

        assertEquals(Optional.of(areaDomain), areaRepositoryJpa.buscarPorId(areaId));

        verify(areaRepositoryJpaQueries, times(1)).findById(areaId);
    }

    @Test
    public void buscarSubAreasSucesso() {

        Area areaDomain = new Area();

        List<Area> areasDomain = new ArrayList<>(List.of(areaDomain));

        AreaJpa areaJpa = new AreaJpa();

        List<AreaJpa> areasJpa = new ArrayList<>(List.of(areaJpa));

        when(mapper.toAreaJpa(areaDomain))
                .thenReturn(areaJpa);

        when(areaRepositoryJpaQueries.findByAreaPrincipal(areaJpa))
                .thenReturn(areasJpa);

        when(mapper.toAreas(areasJpa))
                .thenReturn(areasDomain);

        assertEquals(areasDomain, areaRepositoryJpa.buscarSubAreas(areaDomain));

        verify(areaRepositoryJpaQueries, times(1)).findByAreaPrincipal(areaJpa);

    }

    @Test
    public void buscarAreasPrincipaisSucesso() {

        Area areaDomain = new Area();
        List<Area> areasDomain = new ArrayList<>(List.of(areaDomain));

        AreaJpa areaJpa = new AreaJpa();

        List<AreaJpa> areasJpa = new ArrayList<>(List.of(areaJpa));

        when(mapper.toAreaJpa(areaDomain))
                .thenReturn(areaJpa);

        when(areaRepositoryJpaQueries.findByAreaPrincipal(null))
                .thenReturn(areasJpa);

        when(mapper.toAreas(areasJpa))
                .thenReturn(areasDomain);

        assertEquals(areasDomain, areaRepositoryJpa.buscarAreasPrincipais());

        verify(areaRepositoryJpaQueries, times(1)).findByAreaPrincipal(null);

    }

}
