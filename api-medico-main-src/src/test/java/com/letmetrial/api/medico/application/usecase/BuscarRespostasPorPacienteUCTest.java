package com.letmetrial.api.medico.application.usecase;

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
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.BuscarRespostasPorPacienteUC.BuscarRespostasPorPacienteMapper;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

@SpringBootTest
public class BuscarRespostasPorPacienteUCTest{
    
    @Mock
    PacienteService pacienteService;
    
    @Mock
    MedicoService medicoService;

    @Mock
    RespostaService respostaService;

    @Spy
    BuscarRespostasPorPacienteMapper mapper;

    @InjectMocks
    BuscarRespostasPorPacienteUC buscarRespostasPorPacienteUC;

    @Test
    public void runSucesso(){
       UUID idMedico = UUID.randomUUID();
       Medico medico = new Medico();
       medico.setId(idMedico);

       UUID idPaciente = UUID.randomUUID();
       Paciente paciente = new Paciente();
       paciente.setId(idPaciente);

       Collection<Resposta> respostas = Collections.emptyList();
       Collection<BuscarRespostasPorPacienteUC.RespostaDTO> respostasDTO = Collections.emptyList();

       when(medicoService.buscarPorId(idMedico))
                .thenReturn(medico);

       when(pacienteService.buscarPorIdMedico(idPaciente, medico))
                .thenReturn(paciente);

       when(respostaService.buscarTodasDoPaciente(paciente))
                .thenReturn(respostas);

       when(mapper.toRespostaDTOs(respostas))
                .thenReturn(respostasDTO);

       assertEquals(respostasDTO, buscarRespostasPorPacienteUC.run(idMedico, idPaciente));
       verify(respostaService, times(1)).buscarTodasDoPaciente(paciente);

    }
}