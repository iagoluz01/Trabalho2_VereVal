package com.letmetrial.api.medico.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.CriterioJpa;
import com.letmetrial.api.medico.domain.entity.Criterio;
import com.letmetrial.api.medico.domain.repository.CriterioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CriterioRepositoryJpa implements CriterioRepository {

    private final CriterioRepositoryJpaQueries queries;

    private final CriterioRepositoryJpaMapper mapper;

    @Override
    public Optional<Criterio> buscarPorId(UUID id) {
        return queries
                .findById(id)
                .map(mapper::toCriterio);
    }

    @Repository
    public interface CriterioRepositoryJpaQueries extends JpaRepository<CriterioJpa, UUID> {

    }

    @Mapper
    public interface CriterioRepositoryJpaMapper {

        Criterio toCriterio(CriterioJpa criterioJpa);

    }

}
