package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarRespostasPorPacienteUC {

    private final PacienteService pacienteService;

    private final MedicoService medicoService;

    private final RespostaService respostaService;

    private final BuscarRespostasPorPacienteMapper mapper;

    public Collection<RespostaDTO> run(UUID medicoId, UUID pacienteId) {
        return mapper.toRespostaDTOs(respostaService.buscarTodasDoPaciente(
                pacienteService.buscarPorIdMedico(pacienteId, medicoService.buscarPorId(medicoId))));
    }

    public record RespostaDTO(
            UUID id,
            TipoResposta resposta,
            CriterioDTO criterio) {
    }

    public record CriterioDTO(UUID id, String pergunta) {
    }

    @Mapper
    public interface BuscarRespostasPorPacienteMapper {

        Collection<RespostaDTO> toRespostaDTOs(Collection<Resposta> respostas);

        @Mapping(target = "resposta", source = "resposta")
        @Mapping(target = "criterio", source = "criterio")
        RespostaDTO toRespostaDTO(Resposta resposta);

    }

}
