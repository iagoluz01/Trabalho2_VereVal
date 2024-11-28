package com.letmetrial.api.medico.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.exception.CriterioNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.CriterioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CriterioService {

    private final CriterioRepository criterioRepository;

    public Criterio buscarPorId(UUID id) {
        return criterioRepository
                .buscarPorId(id)
                .orElseThrow(() -> CriterioNaoEncontradoException.BUSCA_POR_ID(id));
    }

}
