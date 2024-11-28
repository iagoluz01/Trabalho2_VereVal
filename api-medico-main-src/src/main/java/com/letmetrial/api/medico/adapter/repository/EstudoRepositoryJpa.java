package com.letmetrial.api.medico.adapter.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.EstudoJpa;
import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.repository.EstudoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class EstudoRepositoryJpa implements EstudoRepository {
    private final EstudoRepositoryJpaQueries queries;
    private final EstudoRepositoryJpaMapper mapper;

    @Override
    public Collection<Estudo> buscarPorArea(Area area) {
        return mapper.toEstudos(queries.findByAreasId(area.getId()));
    }

    @Override
    public Optional<Estudo> buscarPorId(UUID id) {
        return queries.findById(Objects.requireNonNull(id))
                .map(mapper::toEstudo);
    }
    @Mapper
    public interface EstudoRepositoryJpaMapper {

        Collection<Estudo> toEstudos(Collection<EstudoJpa> estudos);

        Estudo toEstudo(EstudoJpa estudo);

    }

    @Repository
    public interface EstudoRepositoryJpaQueries extends JpaRepository<EstudoJpa, UUID> {

        Collection<EstudoJpa> findByAreasId(UUID areaId);

    }
}
