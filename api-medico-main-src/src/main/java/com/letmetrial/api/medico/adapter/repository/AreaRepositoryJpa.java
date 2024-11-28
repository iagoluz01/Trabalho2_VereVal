package com.letmetrial.api.medico.adapter.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.AreaJpa;
import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.repository.AreaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AreaRepositoryJpa implements AreaRepository {

    private final AreaRepositoryJpaQueries queries;

    private final AreaRepositoryJpaMapper mapper;

    @Override
    public Optional<Area> buscarPorId(UUID id) {
        return queries.findById(Objects.requireNonNull(id)).map(mapper::toArea);
    }

    @Override
    public Collection<Area> buscarSubAreas(Area areaPrincipal) {
        return mapper.toAreas(queries.findByAreaPrincipal(mapper.toAreaJpa(areaPrincipal)));
    }

    @Override
    public Collection<Area> buscarAreasPrincipais() {
        return mapper.toAreas(queries.findByAreaPrincipal(null));
    }

    @Mapper
    public interface AreaRepositoryJpaMapper {

        AreaJpa toAreaJpa(Area area);

        Area toArea(AreaJpa area);

        Collection<Area> toAreas(Collection<AreaJpa> areasJpa);

    }

    @Repository
    public interface AreaRepositoryJpaQueries extends JpaRepository<AreaJpa, UUID> {

        Collection<AreaJpa> findByAreaPrincipal(AreaJpa areaPrincipal);

    }

}