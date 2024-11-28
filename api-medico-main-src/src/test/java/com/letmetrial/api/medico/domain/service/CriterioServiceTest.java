package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.exception.CriterioNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.CriterioRepository;

@SpringBootTest
public class CriterioServiceTest {

    @Mock
    CriterioRepository criterioRepository;

    @InjectMocks
    CriterioService criterioService;

    @Test
    public void buscarPorIdSucesso() {
        UUID criterioId = UUID.randomUUID();
        Criterio criterio = new Criterio();
        criterio.setId(criterioId);

        when(criterioRepository.buscarPorId(criterioId))
                .thenReturn(Optional.of(criterio));

        assertEquals(criterio, criterioService.buscarPorId(criterioId));

        verify(criterioRepository, times(1)).buscarPorId(any());
    }

    @Test
    public void buscarPorIdCriterioNaoEncontrado() {

        UUID criterioId = UUID.randomUUID();

        when(criterioRepository.buscarPorId(criterioId))
                .thenReturn(Optional.empty());

        assertThrows(CriterioNaoEncontradoException.class, () -> criterioService.buscarPorId(criterioId));

        verify(criterioRepository, times(1)).buscarPorId(any());
    }
}
