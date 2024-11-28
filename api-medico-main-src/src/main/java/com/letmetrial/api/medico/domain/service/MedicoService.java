package com.letmetrial.api.medico.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.exception.MedicoNaoEncontradoException;
import com.letmetrial.api.medico.domain.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public Medico buscarPorId(UUID id) {
        return medicoRepository
                .buscarPorId(id)
                .orElseThrow(() -> MedicoNaoEncontradoException.BUSCA_POR_ID(id));
    }

}
