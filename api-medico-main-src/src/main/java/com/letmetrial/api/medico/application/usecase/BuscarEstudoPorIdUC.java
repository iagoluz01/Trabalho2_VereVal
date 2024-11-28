package com.letmetrial.api.medico.application.usecase;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.service.EstudoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarEstudoPorIdUC {

    private final EstudoService estudoService;

    private final BuscarEstudoPorIdUCMapper mapper;

    public EstudoDTO run(UUID id) {
        return mapper.toEstudoDTO(estudoService.buscarPorId(id));
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
    public interface BuscarEstudoPorIdUCMapper {

        EstudoDTO toEstudoDTO(Estudo estudo);

        @Mapping(target = "areaPrincipalId", source = "areaPrincipal.id")
        AreaDTO toAreaDTO(Area area);

    }
}
