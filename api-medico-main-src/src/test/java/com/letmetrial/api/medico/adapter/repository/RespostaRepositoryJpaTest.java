package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.PacienteJpa;
import com.letmetrial.api.medico.adapter.entity.RespostaJpa;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;

@SpringBootTest
public class RespostaRepositoryJpaTest {

    @Mock
    RespostaRepositoryJpa.RespostaRepositoryJpQueries queries;

    @Mock
    RespostaRepositoryJpa.RespostaRepositoryJpaMapper mapper;

    @InjectMocks
    RespostaRepositoryJpa respostaRepositoryJpa;

    @Test
    public void buscarPorCriterioIdEPacienteIdSucesso() {
        UUID criterioId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        RespostaJpa respostaJpa = new RespostaJpa();
        Resposta resposta = new Resposta();
        when(queries.findByCriterioIdAndPacienteId(criterioId, pacienteId))
                .thenReturn(Optional.of(respostaJpa));
        when(mapper.toResposta(respostaJpa))
                .thenReturn(resposta);

        Optional<Resposta> retorno = respostaRepositoryJpa.buscarPorCriterioIdEPacienteId(criterioId, pacienteId);
        assertEquals(Optional.of(resposta), retorno);
        verify(queries, times(1)).findByCriterioIdAndPacienteId(any(), any());
    }

    @Test
    public void salvarTodas() {
        Collection<Resposta> respostas = new ArrayList<>();
        List<RespostaJpa> respostasJpa = new ArrayList<>();
        when(mapper.toRespostasJpa(respostas))
                .thenReturn(respostasJpa);
        when(queries.saveAll(respostasJpa))
                .thenReturn(respostasJpa);
        when(mapper.toRespostas(respostasJpa))
                .thenReturn(respostas);

        assertEquals(respostas, respostaRepositoryJpa.salvarTodas(respostas));
        verify(queries, times(1)).saveAll(any());
    }

    @Test
    public void buscarTodasDoPaciente() {
        Collection<Resposta> respostas = new ArrayList<>();
        List<RespostaJpa> respostasJpa = new ArrayList<>();
        PacienteJpa pacienteJpa = new PacienteJpa();
        Paciente paciente = new Paciente();

        when(mapper.toRespostas(respostasJpa))
                .thenReturn(respostas);
        when(queries.findByPaciente(pacienteJpa))
                .thenReturn(respostasJpa);
        when(mapper.toPacienteJpa(paciente))
                .thenReturn(pacienteJpa);

        assertEquals(respostas, respostaRepositoryJpa.buscarTodasRespostasPorPaciente(paciente));
        verify(queries, times(1)).findByPaciente(pacienteJpa);
    }

    @Test
    public void buscarPorEstudoEPacienteIdSucesso() {
        UUID estudoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        Collection<RespostaJpa> respostasJpa = Collections.singleton(new RespostaJpa());
        Collection<Resposta> respostas = Collections.singleton(new Resposta());
        when(queries.findByEstudoEPacienteId(estudoId, pacienteId))
                .thenReturn(respostasJpa);
        when(mapper.toRespostas(respostasJpa))
                .thenReturn(respostas);

        Collection<Resposta> response = respostaRepositoryJpa.buscarPorEstudoEPacienteId(estudoId, pacienteId);
        assertEquals(respostas, response);
        verify(queries, times(1)).findByEstudoEPacienteId(any(), any());
    }

}
