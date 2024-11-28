package com.letmetrial.api.medico.domain.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.exception.AreaNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.AreaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public Area buscarPorId(UUID id) {
        return areaRepository
                .buscarPorId(id)
                .orElseThrow(() -> AreaNaoEncontradoException.BUSCA_POR_ID(id));
    }

    public Collection<Area> buscarSubAreas(UUID id) {
        return areaRepository.buscarSubAreas(buscarPorId(id));
    }

    public Collection<Area> buscarAreasPrincipais() {
        return areaRepository.buscarAreasPrincipais();
    }

}
