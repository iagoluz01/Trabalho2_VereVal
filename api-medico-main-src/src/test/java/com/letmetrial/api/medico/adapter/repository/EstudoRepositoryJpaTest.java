package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.EstudoJpa;
import com.letmetrial.api.medico.domain.entity.Estudo;

@SpringBootTest
public class EstudoRepositoryJpaTest {

    @Mock
    EstudoRepositoryJpa.EstudoRepositoryJpaQueries estudoRepositoryJpaQueries;

    @Spy
    EstudoRepositoryJpa.EstudoRepositoryJpaMapper mapper = Mappers.getMapper(EstudoRepositoryJpa.EstudoRepositoryJpaMapper.class);

    @InjectMocks
    EstudoRepositoryJpa estudoRepositoryJpa;

    @Test
    public void buscarPorIdSucesso() {

        UUID estudoId = UUID.randomUUID();

        Estudo estudoDomain = new Estudo();

        EstudoJpa estudoJpa = new EstudoJpa();

        when(mapper.toEstudo(estudoJpa))
                .thenReturn(estudoDomain);

        when(estudoRepositoryJpaQueries.findById(Objects.requireNonNull(estudoId)))
                .thenReturn(Optional.of(estudoJpa));

        assertEquals(Optional.of(estudoDomain), estudoRepositoryJpa.buscarPorId(estudoId));

        verify(estudoRepositoryJpaQueries, times(1)).findById(estudoId);
    }

    @Test
    public void buscarPorAreaIdSucesso() {

        UUID areaId = UUID.randomUUID();
        Collection<EstudoJpa> estudosJpa = Collections.singletonList(new EstudoJpa());

        when(estudoRepositoryJpaQueries.findByAreasId(areaId)).thenReturn(estudosJpa);

        Collection<EstudoJpa> result = estudoRepositoryJpaQueries.findByAreasId(areaId);

        assertEquals(estudosJpa, result);
        verify(estudoRepositoryJpaQueries, times(1)).findByAreasId(areaId);
    }


}
