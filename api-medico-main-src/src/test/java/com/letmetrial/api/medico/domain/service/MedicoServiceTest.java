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

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.exception.MedicoNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.MedicoRepository;

@SpringBootTest
public class MedicoServiceTest {

    @Mock
    MedicoRepository medicoRepository;

    @InjectMocks
    MedicoService medicoService;

    @Test
    public void buscarPorIdSucesso() {

        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        when(medicoRepository.buscarPorId(medicoId))
                .thenReturn(Optional.of(medico));

        assertEquals(medico, medicoService.buscarPorId(medicoId));

        verify(medicoRepository, times(1)).buscarPorId(any());
    }

    @Test
    public void buscarPorIdMedicoNaoEncontrado() {

        UUID medicoId = UUID.randomUUID();

        when(medicoRepository.buscarPorId(medicoId))
                .thenReturn(Optional.empty());

        assertThrows(MedicoNaoEncontradoException.class, () -> medicoService.buscarPorId(medicoId));

        verify(medicoRepository, times(1)).buscarPorId(any());

    }

}
