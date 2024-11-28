package com.letmetrial.api.medico.adapter.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.MedicoJpa;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MedicoRepositoryJpa implements MedicoRepository {

    private final MedicoRepositoryJpaQueries medicoRepositoryJpaQueries;

    private final MedicoRepositoryJpaMapper mapper;

    @Override
    public Optional<Medico> buscarPorId(UUID id) {
        return medicoRepositoryJpaQueries.findById(Objects.requireNonNull(id))
                .map(medicoJpa -> mapper.toMedico(medicoJpa));
    }

    @Mapper
    public interface MedicoRepositoryJpaMapper {

        Medico toMedico(MedicoJpa medicoJpa);

    }

    @Repository
    public interface MedicoRepositoryJpaQueries extends JpaRepository<MedicoJpa, UUID> {

    }

}
