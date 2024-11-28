package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.exception.MedicoNaoEncontradoException;
import com.letmetrial.api.medico.domain.exception.PacienteNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.PacienteRepository;

@SpringBootTest
class PacienteServiceTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;

    @Test
    public void cadastrarPacienteSucesso() {

        UUID medicoId = UUID.randomUUID();

        Medico medico = new Medico();
        medico.setId(medicoId);

        Paciente paciente = new Paciente();
        paciente.setMedico(medico);

        when(pacienteRepository.salvar(paciente)).thenReturn(paciente);

        Paciente pacienteSalvo = pacienteService.cadastrar(paciente);

        assertEquals(paciente, pacienteSalvo);

        verify(pacienteRepository, times(1)).salvar(any());

    }

    @Test
    public void buscarPorIdEMedicoIdPacienteNaoEncontrado() {

        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();

        Medico medico = new Medico();
        medico.setId(medicoId);

        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);

        when(pacienteRepository.buscarPorMedicoEId( medico, pacienteId))
                .thenReturn(Optional.empty());

        assertThrows(PacienteNaoEncontradoException.class,
                () -> pacienteService.buscarPorIdMedico(pacienteId, medico));

        verify(pacienteRepository, times(1)).buscarPorMedicoEId(any(), any());
        verify(pacienteRepository, times(0)).salvar(any());

    }

    @Test
    public void buscarTodosPacientesPorMedicoIdSucesso() {

        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        Collection<Paciente> pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente();
        paciente1.setId(UUID.randomUUID());
        pacientes.add(paciente1);
        Paciente paciente2 = new Paciente();
        paciente2.setId(UUID.randomUUID());
        pacientes.add(paciente2);

        when(pacienteRepository.buscarTodosPacientesPorMedico(medico))
                .thenReturn(pacientes);

        assertEquals(pacientes, pacienteService.buscarTodosPorMedico(medico));
        verify(pacienteRepository, times(1)).buscarTodosPacientesPorMedico(medico);
    }

    @Test
    public void buscarTodosPacientePorMedicoIdSemPaciente() {

        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        Collection<Paciente> pacientes = new ArrayList<>();

        when(pacienteRepository.buscarTodosPacientesPorMedico(medico))
                .thenReturn(pacientes);

        assertEquals(pacientes, pacienteService.buscarTodosPorMedico(medico));
        verify(pacienteRepository, times(1)).buscarTodosPacientesPorMedico(medico);
    }

    public void buscarTodosPacientesPorMedicoIdMedicoNaoEncontrado() {

        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        when(pacienteRepository.buscarTodosPacientesPorMedico(medico))
                .thenThrow(MedicoNaoEncontradoException.class);

        assertThrows(MedicoNaoEncontradoException.class, () -> pacienteService.buscarTodosPorMedico(medico));
        verify(pacienteRepository, times(1)).buscarTodosPacientesPorMedico(medico);
    }
}
