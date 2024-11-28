package com.letmetrial.api.medico.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Criterio;

public interface CriterioRepository {

    Optional<Criterio> buscarPorId(UUID id);

}
