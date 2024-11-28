package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.Sexo;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.CadastrarPacienteUC.CadastrarPacienteUCMapper;
import com.letmetrial.api.medico.application.usecase.CadastrarPacienteUC.PacienteDTO;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;

@SpringBootTest
class CadastrarPacienteUCTest {

    @Mock
    MedicoService medicoService;

    @Mock
    PacienteService pacienteService;

    @Spy
    CadastrarPacienteUCMapper mapper = Mappers.getMapper(CadastrarPacienteUCMapper.class);

    @InjectMocks
    CadastrarPacienteUC cadastrarPacienteUC;

    
    @Test
    void runSucesso() {
        UUID medicoId = UUID.randomUUID();

        Medico medico = new Medico();
        medico.setId(medicoId);

        CadastrarPacienteUC.PacienteNovoDTO pacienteNovoDTO= new CadastrarPacienteUC.PacienteNovoDTO("12345678","RB", LocalDate.now(),Sexo.M);
        Paciente paciente = mapper.toPaciente(pacienteNovoDTO,medico);


        when(medicoService.buscarPorId(medicoId))
                .thenReturn(medico);

        when(mapper.toPaciente(pacienteNovoDTO,medico))
                .thenCallRealMethod();

        when(pacienteService.cadastrar(any(Paciente.class)))
                .thenReturn(paciente);

        when(mapper.toPacienteDTO(any()))
                .thenCallRealMethod();

        PacienteDTO pacienteDTOEsperado = new PacienteDTO(null,"12345678","RB", LocalDate.now(), Sexo.M,medicoId);
        PacienteDTO pacienteDTOAtual = cadastrarPacienteUC.run(medicoId,pacienteNovoDTO);
        assertEquals(pacienteDTOEsperado,pacienteDTOAtual);

    }
}
