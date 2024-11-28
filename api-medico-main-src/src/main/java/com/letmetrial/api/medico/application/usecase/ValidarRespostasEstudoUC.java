package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.CriterioEstudoService;
import com.letmetrial.api.medico.domain.service.CriterioService;
import com.letmetrial.api.medico.domain.service.EstudoService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.vo.RespostaValidada;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ValidarRespostasEstudoUC {

    private final MedicoService medicoService;

    private final EstudoService estudoService;

    private final CriterioEstudoService criterioEstudoService;

    private final CriterioService criterioService;

    private final PacienteService pacienteService;

    private final ValidarRespostasEstudoUCMapper mapper;

    public ResultadoFinalDTO run(
            UUID estudoId,
            UUID medicoId,
            UUID pacienteId,
            Collection<RespostaDTO> respostas) {

        final Paciente paciente = pacienteService.buscarPorIdMedico(pacienteId, medicoService.buscarPorId(medicoId));
        final Estudo estudo = estudoService.buscarPorId(estudoId);
        paciente.getEstudosValidados().add(estudo);

        Collection<ResultadoValidacaoDTO> resultados = estudoService.validar(
                criterioEstudoService.buscarPorEstudo(
                        estudo),
                respostas
                        .stream()
                        .map(respostaDto -> mapper.toResposta(
                                respostaDto,
                                criterioService.buscarPorId(respostaDto.criterioId),
                                paciente))
                        .collect(Collectors.toList()))
                .stream()
                .map(mapper::toResultadoValidacaoDTO)
                .collect(Collectors.toList());

        boolean passou = resultados.stream().allMatch(resultado -> resultado.passou);

        pacienteService.atualizar(paciente);

        return new ResultadoFinalDTO(passou, resultados);
    }

    public record RespostaDTO(UUID criterioId, TipoResposta resposta) {
    }

    public record ResultadoFinalDTO(boolean passou, Collection<ResultadoValidacaoDTO> resultados) {
    }

    public record ResultadoValidacaoDTO(
            TipoResposta respostaDada,
            TipoResposta respostaEsperada,
            Boolean opcional,
            Boolean passou) {
    }

    @Mapper
    public interface ValidarRespostasEstudoUCMapper {

        @Mapping(target = "id", ignore = true)
        Resposta toResposta(RespostaDTO respostaDTO, Criterio criterio, Paciente paciente);

        @Mapping(target = "respostaDada", source = "resposta.resposta")
        @Mapping(target = "respostaEsperada", source = "criterioEstudo.respostaEsperada")
        @Mapping(target = "opcional", source = "criterioEstudo.opcional")
        ResultadoValidacaoDTO toResultadoValidacaoDTO(RespostaValidada respostaValidada);

    }

}
