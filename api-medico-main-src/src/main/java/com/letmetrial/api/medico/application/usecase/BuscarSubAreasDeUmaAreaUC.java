package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.service.AreaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarSubAreasDeUmaAreaUC {

    private final AreaService areaService;

    private final BuscarSubAreasDeUmaAreaUCMapper mapper;

    public Collection<AreaDTO> run(UUID id) {
        return mapper.toAreaDTOs(areaService.buscarSubAreas(id));
    }

    public record AreaDTO(UUID id, String nome, UUID areaPrincipalId) {
    };

    @Mapper
    public interface BuscarSubAreasDeUmaAreaUCMapper {

        Collection<AreaDTO> toAreaDTOs(Collection<Area> areas);

        @Mapping(target = "areaPrincipalId", source = "areaPrincipal.id")
        AreaDTO toAreaDTO(Area area);

    }

}
