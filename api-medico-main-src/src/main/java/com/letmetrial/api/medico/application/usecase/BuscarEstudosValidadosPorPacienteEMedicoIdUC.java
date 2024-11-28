package com.letmetrial.api.medico.application.usecase;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarEstudosValidadosPorPacienteEMedicoIdUC {

    private final PacienteService pacienteService;

    private final MedicoService medicoService;

    private final BuscarEstudosValidadosPorPacienteEMedicoIdMapper mapper;

    public Collection<EstudoDTO> run(UUID pacienteId, UUID medicoId) {
        return mapper.toEstudoDTO(pacienteService.buscarEstudosValidadosPorPacienteEMedicoId(pacienteId,
                medicoService.buscarPorId(medicoId)));
    }

    public record EstudoDTO(
            UUID id,
            String nome,
            String link,
            String informacoes,
            LocalDate dataPublicacao,
            Set<AreaDTO> areas) {
    }

    public record AreaDTO(
            UUID id,
            String nome,
            UUID areaPrincipalId) {
    }

    @Mapper
    public interface BuscarEstudosValidadosPorPacienteEMedicoIdMapper {

        Collection<EstudoDTO> toEstudoDTO(Collection<Estudo> estudos);

        @Mapping(target = "areaPrincipalId", source = "areaPrincipal.id")
        AreaDTO toAreaDTO(Area area);

    }
}
