package com.letmetrial.api.medico.application.usecase;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.service.AreaService;
import com.letmetrial.api.medico.domain.service.EstudoService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BuscarEstudosPorAreaIdUC {

    private final EstudoService estudoService;

    private final AreaService areaService;

    private final BuscarEstudosPorAreaUCMapper mapper;

    public Collection<EstudoDTO> run(UUID areaId) {
        return mapper.toEstudosDTO(estudoService.buscarPorArea(areaService.buscarPorId(areaId)));
    }

    public record EstudoDTO(
            UUID id,
            String nome,
            String link,
            String informacoes,
            LocalDate dataPublicacao,
            Set<AreaDTO> areas) {
    }

    public record AreaDTO(UUID id, String nome, UUID areaPrincipalId) {
    }

    @Mapper
    public interface BuscarEstudosPorAreaUCMapper {

        Collection<EstudoDTO> toEstudosDTO(Collection<Estudo> estudos);

        @Mapping(target = "areaPrincipalId", source = "areaPrincipal.id")
        AreaDTO toAreaDTO(Area area);

    }
}
