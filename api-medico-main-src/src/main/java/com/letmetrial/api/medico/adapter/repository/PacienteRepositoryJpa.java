package com.letmetrial.api.medico.adapter.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.PacienteJpa;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PacienteRepositoryJpa implements PacienteRepository {

    private final PacienteRepositoryJpaQueries queries;

    private final PacienteRepositoryJpaMapper mapper;

    @Override
    public Paciente salvar(Paciente paciente) {
        return mapper.toPaciente(queries.save(Objects.requireNonNull(mapper.toPacienteJpa(paciente))));
    }

    @Override
    public Collection<Paciente> buscarTodosPacientesPorMedico(Medico medico) {
        return mapper.toPacienteCollection(queries.findAllByMedicoId(medico.getId()));
    }

    @Override
    public Optional<Paciente> buscarPorMedicoEId(Medico medico, UUID pacienteId) {
        return queries.findByIdAndMedicoId(pacienteId, medico.getId()).map(mapper::toPaciente);
    }

    @Mapper
    public interface PacienteRepositoryJpaMapper {

        Collection<Paciente> toPacienteCollection(Collection<PacienteJpa> pacienteJpa);

        PacienteJpa toPacienteJpa(Paciente paciente);

        Paciente toPaciente(PacienteJpa pacienteJpa);

    }

    @Repository
    public interface PacienteRepositoryJpaQueries extends JpaRepository<PacienteJpa, UUID> {

        Optional<PacienteJpa> findByIdAndMedicoId(UUID pacienteId, UUID medicoId);

        Collection<PacienteJpa> findAllByMedicoId(UUID medicoId);
    }

}