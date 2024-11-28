package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.EstudoService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

@SpringBootTest
public class BuscarRespostasPacienteEstudoUCTest {
    @Mock
    private PacienteService pacienteService;

    @Mock
    private EstudoService estudoService;

    @Mock
    private MedicoService medicoService;

    @Mock
    private RespostaService respostaService;

    @InjectMocks
    private BuscarRespostasPacienteEstudoUC buscarRespostasPacienteEstudoUC;

    @Spy
    BuscarRespostasPacienteEstudoUC.BuscarRespostasPacienteEstudoUCMapper mapper = Mappers
            .getMapper(BuscarRespostasPacienteEstudoUC.BuscarRespostasPacienteEstudoUCMapper.class);

    @Test
    public void runSucesso() {
        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID estudoId = UUID.randomUUID();

        Medico medico = new Medico();
        medico.setId(medicoId);

        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);

        Estudo estudo = new Estudo();
        estudo.setId(estudoId);

        Resposta resposta = new Resposta();
        resposta.setResposta(TipoResposta.SIM);

        when(medicoService.buscarPorId(medicoId)).thenReturn(medico);
        when(pacienteService.buscarPorIdMedico(pacienteId, medico)).thenReturn(paciente);
        when(estudoService.buscarPorId(estudoId)).thenReturn(estudo);
        when(respostaService.buscarPorEstudoEPacienteId(estudo, paciente))
                .thenReturn(Collections.singletonList(resposta));

        var result = buscarRespostasPacienteEstudoUC.run(medicoId, pacienteId, estudoId);

        assertEquals(1, result.size());
        assertEquals(TipoResposta.SIM, result.iterator().next().resposta());
    }

}
