package com.letmetrial.api.medico.application.usecase;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.service.CriterioEstudoService;
import com.letmetrial.api.medico.domain.service.EstudoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarCriteriosPorEstudoIdUC {

    private final CriterioEstudoService criterioEstudoService;

    private final EstudoService estudoService;

    private final BuscarCriteriosPorEstudoIdUCMapper mapper;

    public Collection<CriterioDTO> run(UUID id) {
        return mapper.toCriterioDTO(criterioEstudoService.buscarPorEstudo(estudoService.buscarPorId(id)).stream()
                .map(CriterioEstudo::getCriterio).collect(Collectors.toList()));
    }

    public record CriterioDTO(UUID id, String pergunta) {
    }

    @Mapper
    public interface BuscarCriteriosPorEstudoIdUCMapper {
        Collection<CriterioDTO> toCriterioDTO(Collection<Criterio> criterio);

    }
}
