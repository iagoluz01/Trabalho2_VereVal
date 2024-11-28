package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.repository.RespostaRepository;

@SpringBootTest
class RespostaServiceTest {

    @Mock
    RespostaRepository respostaRepository;

    @InjectMocks
    RespostaService respostaService;

    @Test
    public void buscarPorEstudoEPacienteIdSucesso() {
        Estudo estudo = new Estudo();
        estudo.setId(UUID.randomUUID());
        Paciente paciente = new Paciente();
        paciente.setId(UUID.randomUUID());
        Resposta resposta = new Resposta();
        when(respostaRepository.buscarPorEstudoEPacienteId(estudo.getId(), paciente.getId()))
                .thenReturn(Collections.singletonList(resposta));

        var result = respostaService.buscarPorEstudoEPacienteId(estudo, paciente);

        assertEquals(1, result.size());
        assertEquals(resposta, result.iterator().next());
    }

    @Test
    public void salvarTodasSucesso() {

        Collection<Resposta> respostas = Collections.emptyList();

        when(respostaRepository.salvarTodas(respostas))
                .thenReturn(respostas);

        assertEquals(respostas, respostaService.salvarTodas(respostas));
        verify(respostaRepository, times(1)).salvarTodas(respostas);
    }

    @Test
    public void buscarTodasDoPacienteSucesso() {
        Paciente paciente = new Paciente();

        Collection<Resposta> respostas = Collections.emptyList();

        when(respostaRepository.buscarTodasRespostasPorPaciente(paciente))
                .thenReturn(respostas);

        assertEquals(respostas, respostaService.buscarTodasDoPaciente(paciente));
        verify(respostaRepository, times(1)).buscarTodasRespostasPorPaciente(paciente);
    }

}