package com.letmetrial.api.medico.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.ResponseEntity;

import com.letmetrial.api.medico.application.usecase.BuscarMedicoPorIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarRespostasPacienteEstudoUC;
import com.letmetrial.api.medico.application.usecase.BuscarRespostasPorPacienteUC;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;

@SpringBootTest
public class MedicoControllerTest {

    @Mock
    BuscarMedicoPorIdUC buscarMedicoPorIdUC;

    @Mock
    BuscarRespostasPacienteEstudoUC buscarRespostasPacienteEstudoUC;

    @Mock
    BuscarRespostasPorPacienteUC buscarRespostasPorPacienteUC;

    @InjectMocks
    MedicoController medicoController;

    @Test
    public void buscarMedicoPorIdSucesso() {

        UUID medicoId = UUID.randomUUID();

        BuscarMedicoPorIdUC.MedicoDTO medicoDto = new BuscarMedicoPorIdUC.MedicoDTO(
                medicoId,
                "0123456789/SP");

        when(buscarMedicoPorIdUC.run(medicoId))
                .thenReturn(medicoDto);

        assertEquals(ResponseEntity.ok(medicoDto), medicoController.buscarMedicoPorId(medicoId));

        verify(buscarMedicoPorIdUC, times(1)).run(medicoId);
        verify(buscarMedicoPorIdUC, times(1)).run(any());

    }

    @Test
    public void buscarRespostasPacienteEstudoSucesso() {
        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID estudoId = UUID.randomUUID();
        UUID criterioId = UUID.randomUUID();
        BuscarRespostasPacienteEstudoUC.RespostaDTO respostaDTO = new BuscarRespostasPacienteEstudoUC.RespostaDTO(
                criterioId, TipoResposta.SIM);

        when(buscarRespostasPacienteEstudoUC.run(medicoId, pacienteId, estudoId))
                .thenReturn(Collections.singleton(respostaDTO));

        assertEquals(ResponseEntity.ok(Collections.singleton(respostaDTO)),
                medicoController.buscarRespostasPacienteEstudo(medicoId, pacienteId, estudoId));

        verify(buscarRespostasPacienteEstudoUC, times(1)).run(medicoId, pacienteId, estudoId);
    }

    @Test
    public void buscarTodasRespostasPorPacienteSucesso() {

        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        Collection<BuscarRespostasPorPacienteUC.RespostaDTO> respostasDTO = Collections.emptyList();

        when(buscarRespostasPorPacienteUC.run(medicoId, pacienteId))
                .thenReturn(respostasDTO);

        assertEquals(ResponseEntity.ok(respostasDTO),
                medicoController.buscarRespostasPorPaciente(medicoId, pacienteId));
        verify(buscarRespostasPorPacienteUC, times(1)).run(medicoId, pacienteId);
    }

}
