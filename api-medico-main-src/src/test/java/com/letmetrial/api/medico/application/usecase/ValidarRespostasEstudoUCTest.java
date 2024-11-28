package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.ValidarRespostasEstudoUC.ResultadoValidacaoDTO;
import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.CriterioEstudoService;
import com.letmetrial.api.medico.domain.service.CriterioService;
import com.letmetrial.api.medico.domain.service.EstudoService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.vo.RespostaValidada;

@SpringBootTest
class ValidarRespostasEstudoUCTest {

    @Mock
    MedicoService medicoService;

    @Mock
    EstudoService estudoService;

    @Mock
    CriterioEstudoService criterioEstudoService;

    @Mock
    CriterioService criterioService;

    @Mock
    PacienteService pacienteService;

    @Spy
    ValidarRespostasEstudoUC.ValidarRespostasEstudoUCMapper mapper = Mappers
            .getMapper(ValidarRespostasEstudoUC.ValidarRespostasEstudoUCMapper.class);

    @InjectMocks
    ValidarRespostasEstudoUC validarRespostasEstudoUC;

    @Test
    void runSucesso() {

        UUID estudoId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();

        Medico medico = new Medico();
        medico.setId(medicoId);

        UUID criterioId1 = UUID.randomUUID();
        Criterio criterio1 = new Criterio();
        criterio1.setId(criterioId1);

        ValidarRespostasEstudoUC.RespostaDTO respostaDTO1 = new ValidarRespostasEstudoUC.RespostaDTO(criterioId1,
                TipoResposta.SIM);
        Collection<ValidarRespostasEstudoUC.RespostaDTO> respostasDTO = new ArrayList<>();
        respostasDTO.add(respostaDTO1);

        UUID pacienteId = UUID.randomUUID();
        Paciente paciente = new Paciente();
        paciente.setEstudosValidados(new ArrayList<>());
        paciente.setId(pacienteId);

        Estudo estudo = new Estudo();
        estudo.setId(estudoId);

        UUID criterioEstudoId = UUID.randomUUID();
        CriterioEstudo criterioEstudo1 = new CriterioEstudo();
        criterioEstudo1.setId(criterioEstudoId);
        criterioEstudo1.setEstudo(estudo);
        criterioEstudo1.setCriterio(criterio1);
        Collection<CriterioEstudo> criteriosEstudo = new ArrayList<>();
        criteriosEstudo.add(criterioEstudo1);

        Resposta resposta1 = new Resposta();
        resposta1.setCriterio(criterio1);
        resposta1.setPaciente(paciente);
        Collection<Resposta> respostas = new ArrayList<>();
        respostas.add(resposta1);

        RespostaValidada respostaValidada1 = new RespostaValidada(resposta1, criterioEstudo1, true);
        Collection<RespostaValidada> respostasValidadas = new ArrayList<>();
        respostasValidadas.add(respostaValidada1);

        ResultadoValidacaoDTO resultado1 = new ResultadoValidacaoDTO(TipoResposta.SIM, TipoResposta.SIM, false, true);
        Collection<ResultadoValidacaoDTO> resultados = new ArrayList<>();
        resultados.add(resultado1);

        when(medicoService.buscarPorId(medicoId))
                .thenReturn(medico);

        when(pacienteService.buscarPorIdMedico(pacienteId, medico))
                .thenReturn(paciente);

        when(estudoService.buscarPorId(estudoId))
                .thenReturn(estudo);

        when(criterioEstudoService.buscarPorEstudo(estudo))
                .thenReturn(criteriosEstudo);

        when(criterioService.buscarPorId(criterioId1))
                .thenReturn(criterio1);

        when(mapper.toResposta(respostaDTO1, criterio1, paciente))
                .thenReturn(resposta1);

        when(estudoService.validar(criteriosEstudo, respostas))
                .thenReturn(respostasValidadas);

        when(mapper.toResultadoValidacaoDTO(respostaValidada1))
                .thenReturn(resultado1);

        assertEquals(new ValidarRespostasEstudoUC.ResultadoFinalDTO(true, resultados),
                validarRespostasEstudoUC.run(estudoId, medicoId, pacienteId, respostasDTO));

    }
}
