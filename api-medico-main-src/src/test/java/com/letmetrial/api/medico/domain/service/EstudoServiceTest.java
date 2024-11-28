package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.exception.EstudoNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.EstudoRepository;

@SpringBootTest
public class EstudoServiceTest {

    @Mock
    EstudoRepository estudoRepository;

    @InjectMocks
    EstudoService estudoService;

    @Test
    public void buscarPorAreaSucesso() {
        Area area = new Area();
        Collection<Estudo> estudosCollection = Collections.singleton(new Estudo());

        when(estudoRepository.buscarPorArea(area)).thenReturn(estudosCollection);
        assertEquals(estudosCollection, estudoService.buscarPorArea(area));

        verify(estudoRepository, times(1)).buscarPorArea(any());
    }

    @Test
    public void buscarPorIdSucesso() {

        UUID estudoId = UUID.randomUUID();
        Estudo estudo = new Estudo();
        estudo.setId(estudoId);

        when(estudoRepository.buscarPorId(estudoId))
                .thenReturn(Optional.of(estudo));

        assertEquals(estudo, estudoService.buscarPorId(estudoId));

        verify(estudoRepository, times(1)).buscarPorId(any());
    }

    @Test
    public void buscarPorIdEstudoNaoEncontrado() {

        UUID estudoId = UUID.randomUUID();

        when(estudoRepository.buscarPorId(estudoId))
                .thenReturn(Optional.empty());

        assertThrows(EstudoNaoEncontradoException.class, () -> estudoService.buscarPorId(estudoId));

        verify(estudoRepository, times(1)).buscarPorId(any());

    }
}
