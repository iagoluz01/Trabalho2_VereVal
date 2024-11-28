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
public class BuscarAreasPrincipaisUC {

    private final AreaService areaService;

    private final BuscarAreasPrincipaisUCMapper mapper;

    public Collection<AreaDTO> run() {
        return mapper.toAreaDTOs(areaService.buscarAreasPrincipais());
    }

    public record AreaDTO(UUID id, String nome, UUID areaPrincipalId) {
    };

    @Mapper
    public interface BuscarAreasPrincipaisUCMapper {

        Collection<AreaDTO> toAreaDTOs(Collection<Area> areas);

        @Mapping(target = "areaPrincipalId", source = "areaPrincipal.id")
        AreaDTO toAreaDTO(Area area);

    }

}
