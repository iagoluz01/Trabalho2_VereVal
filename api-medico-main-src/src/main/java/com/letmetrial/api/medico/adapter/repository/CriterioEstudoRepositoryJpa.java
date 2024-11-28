package com.letmetrial.api.medico.adapter.repository;

import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.CriterioEstudoJpa;
import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.repository.CriterioEstudoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CriterioEstudoRepositoryJpa implements CriterioEstudoRepository {

    private final CriterioEstudoRepositoryJpaQueries queries;

    private final CriterioEstudoJpaMapper mapper;

    @Override
    public Collection<CriterioEstudo> buscarPorEstudo(Estudo estudo) {
        return mapper.toCriteriosEstudos(queries.findByEstudoId(estudo.getId()));
    }

    @Mapper
    public interface CriterioEstudoJpaMapper {

        Collection<CriterioEstudo> toCriteriosEstudos(Collection<CriterioEstudoJpa> criteriosEstudos);

    }

    @Repository
    public interface CriterioEstudoRepositoryJpaQueries extends JpaRepository<CriterioEstudoJpa, UUID> {

        Collection<CriterioEstudoJpa> findByEstudoId(UUID estudoId);

    }
}
