package com.letmetrial.api.medico.domain.repository;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EstudoRepository {
    Collection<Estudo> buscarPorArea(Area area);

    Optional<Estudo> buscarPorId(UUID id);
}
