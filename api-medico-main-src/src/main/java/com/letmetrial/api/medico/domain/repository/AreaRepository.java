package com.letmetrial.api.medico.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Area;

public interface AreaRepository {

    Optional<Area> buscarPorId(UUID id);

    Collection<Area> buscarSubAreas(Area areaPrincipal);

    Collection<Area> buscarAreasPrincipais();

}
