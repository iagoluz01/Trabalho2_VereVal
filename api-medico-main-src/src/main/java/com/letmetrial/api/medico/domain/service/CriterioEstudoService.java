package com.letmetrial.api.medico.domain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.repository.CriterioEstudoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CriterioEstudoService {

    private final CriterioEstudoRepository criterioEstudoRepository;

    public Collection<CriterioEstudo> buscarPorEstudo(Estudo estudo) {
        return criterioEstudoRepository.buscarPorEstudo(estudo);
    }
}
