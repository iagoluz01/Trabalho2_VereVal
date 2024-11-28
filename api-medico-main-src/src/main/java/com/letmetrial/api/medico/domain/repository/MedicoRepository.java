package com.letmetrial.api.medico.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Medico;

public interface MedicoRepository {

    Optional<Medico> buscarPorId(UUID id);

}
