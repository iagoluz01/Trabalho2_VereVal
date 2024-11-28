package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.Sexo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;

@SpringBootTest
public class BuscarTodosPacienteDoMedicoTest {

    @Mock
    PacienteService pacienteService;

    @Mock
    MedicoService medicoService;

    @Spy
    BuscarTodosPacienteDoMedicoUC.BuscarTodosPacienteDoMedicoMapper mapper;

    @InjectMocks
    BuscarTodosPacienteDoMedicoUC buscarTodosPacienteDoMedico;

    @Test
    public void runSucesso() {

        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        Collection<Paciente> pacientes = Collections.emptyList();
        Collection<BuscarTodosPacienteDoMedicoUC.PacienteDTO> pacientesDTO = Collections.emptyList();

        when(pacienteService.buscarTodosPorMedico(medico))
                .thenReturn(pacientes);

        when(mapper.toPacienteDTOColletion(pacientes))
                .thenReturn(pacientesDTO);

        when(medicoService.buscarPorId(medicoId))
                .thenReturn(medico);

        assertEquals(pacientesDTO, buscarTodosPacienteDoMedico.run(medicoId));
        verify(pacienteService, times(1)).buscarTodosPorMedico(medico);

    }
    @Test
    public void BuscarTodosPacienteDoMedicoComPacientes() {

            UUID medicoId = UUID.randomUUID();
            Medico medico = new Medico();
            medico.setId(medicoId);

            Paciente paciente = new Paciente();
            paciente.setId(UUID.randomUUID());
            paciente.setCep("83169808");
            paciente.setApelido("NC");
            paciente.setDataNascimento(null);
            paciente.setSexo(Sexo.M);
            paciente.setMedico(medico);

            List<Paciente> pacientes = List.of(paciente);

            BuscarTodosPacienteDoMedicoUC.PacienteDTO pacienteDTO = new BuscarTodosPacienteDoMedicoUC.PacienteDTO(
                    paciente.getId(),
                    paciente.getCep(),
                    paciente.getApelido(),
                    paciente.getDataNascimento(),
                    paciente.getSexo(),
                    paciente.getMedico().getId()
            );

            when(pacienteService.buscarTodosPorMedico(medico))
                    .thenReturn(pacientes);

            when(mapper.toPacienteDTOColletion(pacientes))
                    .thenReturn(List.of(pacienteDTO));

            when(medicoService.buscarPorId(medicoId))
                    .thenReturn(medico);

            assertEquals(List.of(pacienteDTO), buscarTodosPacienteDoMedico.run(medicoId));
            verify(pacienteService, times(1)).buscarTodosPorMedico(medico);

        }
}