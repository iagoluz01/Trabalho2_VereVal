package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.enumeration.TipoResposta;
import com.letmetrial.api.medico.domain.service.EstudoService;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;
import com.letmetrial.api.medico.domain.service.RespostaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarRespostasPacienteEstudoUC {

    private final PacienteService pacienteService;

    private final EstudoService estudoService;

    private final MedicoService medicoService;

    private final RespostaService respostaService;

    private final BuscarRespostasPacienteEstudoUCMapper mapper;

    public Collection<RespostaDTO> run(UUID medicoId, UUID pacienteId, UUID estudoId) {
        return mapper.toRespostasDTO(respostaService.buscarPorEstudoEPacienteId(estudoService.buscarPorId(estudoId),
                pacienteService.buscarPorIdMedico(pacienteId, medicoService.buscarPorId(medicoId))));
    }

    public record RespostaDTO(UUID criterioId, TipoResposta resposta) {
    };

    @Mapper
    public interface BuscarRespostasPacienteEstudoUCMapper {

        @Mapping(target = "criterioId", source = "criterio.id")
        RespostaDTO toRespostaDTO(Resposta resposta);

        Collection<RespostaDTO> toRespostasDTO(Collection<Resposta> respostas);

    }

}
