package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.PacienteJpa;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;

@SpringBootTest
public class PacienteRepositoryJpaTest {

    @Mock
    PacienteRepositoryJpa.PacienteRepositoryJpaQueries pacienteRepositoryJpaQueries;

    @Mock
    PacienteRepositoryJpa.PacienteRepositoryJpaMapper pacienteRepositoryJpaMapper;

    @InjectMocks
    PacienteRepositoryJpa pacienteRepositoryJpa;

    /*@Test
    public void existePacienteTrue() {
        UUID medicoId = UUID.randomUUID();

        String cep = "12345678";

        Paciente pacienteNovo = new Paciente();

        Medico medico = new Medico();
        medico.setId(medicoId);
        pacienteNovo.setCep(cep);
        pacienteNovo.setMedico(medico);

        when(pacienteRepositoryJpaQueries.existsByCepAndMedicoId(cep, medicoId))
                .thenReturn(true);

        assertEquals(true, pacienteRepositoryJpa.existe(pacienteNovo));
    }*/

   /* @Test
    public void existePacienteFalse() {
        UUID medicoId = UUID.randomUUID();

        String cep = "12345678";

        Paciente pacienteNovo = new Paciente();

        Medico medico = new Medico();
        medico.setId(medicoId);
        pacienteNovo.setCep(cep);
        pacienteNovo.setMedico(medico);

        when(pacienteRepositoryJpaQueries.existsByCepAndMedicoId(cep, medicoId))
                .thenReturn(false);

        assertEquals(false, pacienteRepositoryJpa.existe(pacienteNovo));
    }*/

    @Test
    void salvarPacienteSucesso() {

        UUID medicoId = UUID.randomUUID();

        Paciente paciente = new Paciente();
        PacienteJpa pacienteJpa = new PacienteJpa();

        Medico medico = new Medico();
        medico.setId(medicoId);
        pacienteJpa.setCep("94970092");

        when(pacienteRepositoryJpaMapper.toPacienteJpa(paciente))
                .thenReturn(pacienteJpa);

        when(pacienteRepositoryJpaQueries.save(pacienteJpa))
                .thenReturn(pacienteJpa);

        when(pacienteRepositoryJpaMapper.toPaciente(pacienteJpa))
                .thenReturn(paciente);

        assertEquals(paciente, pacienteRepositoryJpa.salvar(paciente));

        verify(pacienteRepositoryJpaQueries, times(1)).save(any());

    }

    @Test
    public void buscarTodosPacientesPorMedicoIdSucesso() {
        UUID medicoId = UUID.randomUUID();
        Medico medico = new Medico();
        medico.setId(medicoId);

        List<Paciente> pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente();
        paciente1.setId(UUID.randomUUID());
        pacientes.add(paciente1);
        Paciente paciente2 = new Paciente();
        paciente2.setId(UUID.randomUUID());
        pacientes.add(paciente2);

        Collection<PacienteJpa> pacientesJpa = new ArrayList<>();

        PacienteJpa pacienteJpa1 = new PacienteJpa();
        pacienteJpa1.setId(UUID.randomUUID());
        pacientesJpa.add(pacienteJpa1);
        PacienteJpa pacienteJpa2 = new PacienteJpa();
        pacienteJpa2.setId(UUID.randomUUID());
        pacientesJpa.add(pacienteJpa2);

        when(pacienteRepositoryJpaQueries.findAllByMedicoId(medicoId))
                .thenReturn(pacientesJpa);

        when(pacienteRepositoryJpaMapper.toPacienteCollection(pacientesJpa))
                .thenReturn(pacientes);

        assertEquals(pacientes, pacienteRepositoryJpa.buscarTodosPacientesPorMedico(medico));

        verify(pacienteRepositoryJpaQueries, times(1)).findAllByMedicoId(medicoId);
    }

}
