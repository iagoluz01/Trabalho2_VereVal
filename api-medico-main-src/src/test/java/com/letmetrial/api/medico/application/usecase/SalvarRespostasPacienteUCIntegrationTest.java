package com.letmetrial.api.medico.application.usecase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.CriterioService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

@SpringBootTest
public class SalvarRespostasPacienteUCIntegrationTest {

    @Autowired
    private SalvarRespostasPacienteUC salvarRespostasPacienteUC;

    @MockBean
    private MedicoService medicoService;

    @MockBean
    private PacienteService pacienteService;

    @MockBean
    private CriterioService criterioService;

    @MockBean
    private RespostaService respostaService;

    @Test
    public void testRun() {
        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID criterioId = UUID.randomUUID();

        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        Criterio criterio = new Criterio();

        SalvarRespostasPacienteUC.RespostaDTO respostaDTO = new SalvarRespostasPacienteUC.RespostaDTO(TipoResposta.SIM, criterioId);
        Collection<SalvarRespostasPacienteUC.RespostaDTO> respostasDTO = List.of(respostaDTO);

        when(medicoService.buscarPorId(medicoId)).thenReturn(medico);
        when(pacienteService.buscarPorIdMedico(pacienteId, medico)).thenReturn(paciente);
        when(criterioService.buscarPorId(criterioId)).thenReturn(criterio);

        salvarRespostasPacienteUC.run(medicoId, pacienteId, respostasDTO);

        ArgumentCaptor<Collection<Resposta>> argumentCaptor = ArgumentCaptor.forClass(Collection.class);
        verify(respostaService).salvarTodas(argumentCaptor.capture());

        Collection<Resposta> respostasCapturadas = argumentCaptor.getValue();
        assertEquals(1, respostasCapturadas.size());
        Resposta respostaCapturada = respostasCapturadas.iterator().next();
        assertEquals(TipoResposta.SIM, respostaCapturada.getResposta());
        assertEquals(criterio, respostaCapturada.getCriterio());
        assertEquals(paciente, respostaCapturada.getPaciente());
    }
}