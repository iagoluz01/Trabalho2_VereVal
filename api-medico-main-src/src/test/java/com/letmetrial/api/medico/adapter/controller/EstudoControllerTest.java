package com.letmetrial.api.medico.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.letmetrial.api.medico.application.usecase.BuscarCriteriosPorEstudoIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarEstudoPorIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarEstudosPorAreaIdUC;
import com.letmetrial.api.medico.application.usecase.ValidarRespostasEstudoUC;
import com.letmetrial.api.medico.application.usecase.ValidarRespostasEstudoUC.ResultadoFinalDTO;

@SpringBootTest
public class EstudoControllerTest {

    @Mock
    BuscarEstudosPorAreaIdUC buscarEstudosPorAreaIdUC;

    @Mock
    BuscarEstudoPorIdUC buscarEstudoPorIdUC;

    @Mock
    BuscarCriteriosPorEstudoIdUC buscarCriteriosPorEstudoIdUC;

    @Mock
    ValidarRespostasEstudoUC validarRespostasEstudoUC;



    @InjectMocks
    EstudoController estudoController;

    @Test
    public void buscarEstudoPorAreaIdSucesso() {
        UUID areaId = UUID.randomUUID();
        BuscarEstudosPorAreaIdUC.AreaDTO areaDTO = new BuscarEstudosPorAreaIdUC.AreaDTO(areaId, "nome", null);
        BuscarEstudosPorAreaIdUC.EstudoDTO estudoDTO = new BuscarEstudosPorAreaIdUC.EstudoDTO(
                UUID.randomUUID(),
                "nome",
                "link",
                "info",
                null,
                Collections.singleton(areaDTO));

        when(buscarEstudosPorAreaIdUC.run(areaId)).thenReturn(Collections.singleton(estudoDTO));

        assertEquals(ResponseEntity.ok(Collections.singleton(estudoDTO)),
                estudoController.buscarEstudoPorAreaId(areaId));

        verify(buscarEstudosPorAreaIdUC, times(1)).run(areaId);
        verify(buscarEstudosPorAreaIdUC, times(1)).run(any());
    }

    @Test
    public void buscarEstudoPorIdSucesso() {
        UUID estudoId = UUID.randomUUID();
        BuscarEstudoPorIdUC.EstudoDTO estudoDTO = new BuscarEstudoPorIdUC.EstudoDTO(
                estudoId,
                "nome",
                "link",
                "info",
                null,
                Collections.singleton(new BuscarEstudoPorIdUC.AreaDTO(UUID.randomUUID(), "nome", null)));

        when(buscarEstudoPorIdUC.run(estudoId))
                .thenReturn(estudoDTO);

        assertEquals(ResponseEntity.ok(estudoDTO), estudoController.buscarEstudoPorId(estudoId));

        verify(buscarEstudoPorIdUC, times(1)).run(estudoId);
        verify(buscarEstudoPorIdUC, times(1)).run(any());
    }

    @Test
    public void validarRespostasSucesso(){
        UUID estudoId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        Collection<ValidarRespostasEstudoUC.RespostaDTO> respostas = new ArrayList<>();
        ValidarRespostasEstudoUC.ResultadoFinalDTO resultadoFinalDTO = new ResultadoFinalDTO(false, null);

        when(validarRespostasEstudoUC.run(estudoId, medicoId, pacienteId, respostas))
                .thenReturn(resultadoFinalDTO);
        
        assertEquals(ResponseEntity.ok(resultadoFinalDTO), estudoController.validarRespostas(estudoId, medicoId, pacienteId, respostas));

        verify(validarRespostasEstudoUC, times(1)).run(estudoId, medicoId, pacienteId, respostas);
        verify(validarRespostasEstudoUC, times(1)).run(any(), any(), any(), any());
    }

    @Test
    public void buscarCriteriosPorEstudoIdSucesso() {
        UUID estudoId = UUID.randomUUID();
        BuscarCriteriosPorEstudoIdUC.CriterioDTO criterioDTO = new BuscarCriteriosPorEstudoIdUC.CriterioDTO(
                UUID.randomUUID(),
                "descricao");

        when(buscarCriteriosPorEstudoIdUC.run(estudoId)).thenReturn(Collections.singleton(criterioDTO));

        assertEquals(ResponseEntity.ok(Collections.singleton(criterioDTO)),
                estudoController.buscarCriteriosPorEstudoId(estudoId));

        verify(buscarCriteriosPorEstudoIdUC, times(1)).run(estudoId);
        verify(buscarCriteriosPorEstudoIdUC, times(1)).run(any());
    }
}