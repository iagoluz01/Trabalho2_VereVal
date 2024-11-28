package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.CriterioService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SalvarRespostasPacienteUC {

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final CriterioService criterioService;

    private final RespostaService respostaService;

    private final SalvarRespostasPacienteUCMapper mapper;

    public void run(UUID medicoId, UUID pacienteId, Collection<RespostaDTO> respostas) {

        final Paciente paciente = pacienteService.buscarPorIdMedico(pacienteId, medicoService.buscarPorId(medicoId));

        respostaService.salvarTodas(respostas
                .stream()
                .map(resposta -> mapper.toResposta(
                        resposta,
                        criterioService.buscarPorId(resposta.criterioId()),
                        paciente))
                .toList());
    }

    public record RespostaDTO(TipoResposta resposta, UUID criterioId) {
    }

    @Mapper
    public interface SalvarRespostasPacienteUCMapper {

        @Mapping(target = "id", ignore = true)
        Resposta toResposta(RespostaDTO respostaDTO, Criterio criterio, Paciente paciente);
    }

}
