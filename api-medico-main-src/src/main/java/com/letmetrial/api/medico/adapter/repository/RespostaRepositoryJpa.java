package com.letmetrial.api.medico.adapter.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.letmetrial.api.medico.adapter.entity.PacienteJpa;
import com.letmetrial.api.medico.adapter.entity.RespostaJpa;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.repository.RespostaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RespostaRepositoryJpa implements RespostaRepository {

    private final RespostaRepositoryJpQueries queries;

    private final RespostaRepositoryJpaMapper mapper;

    @Override
    public Optional<Resposta> buscarPorCriterioIdEPacienteId(UUID criterioId, UUID pacienteId) {
        return queries
                .findByCriterioIdAndPacienteId(criterioId, pacienteId)
                .map(mapper::toResposta);
    }

    @Override
    public Collection<Resposta> salvarTodas(Collection<Resposta> respostas) {
        return mapper.toRespostas(queries.saveAll(mapper.toRespostasJpa(respostas)));
    }

    @Override
    public Collection<Resposta> buscarPorEstudoEPacienteId(UUID estudoId, UUID pacienteId) {
        return mapper.toRespostas(queries.findByEstudoEPacienteId(estudoId, pacienteId));
    }

    @Override
    public Collection<Resposta> buscarTodasRespostasPorPaciente(Paciente paciente) {
        return mapper.toRespostas(queries.findByPaciente(mapper.toPacienteJpa(paciente)));
    }

    @Repository
    public interface RespostaRepositoryJpQueries extends JpaRepository<RespostaJpa, UUID> {

        Optional<RespostaJpa> findByCriterioIdAndPacienteId(UUID criterioid, UUID pacienteId);

        @Query("SELECT r FROM RespostaJpa r LEFT JOIN CriterioEstudoJpa ce ON r.criterio.id = ce.criterio.id WHERE ce.estudo.id = :estudoId AND r.paciente.id = :pacienteId")
        Collection<RespostaJpa> findByEstudoEPacienteId(UUID estudoId, UUID pacienteId);

        Collection<RespostaJpa> findByPaciente(PacienteJpa paciente);

    }

    @Mapper
    public interface RespostaRepositoryJpaMapper {

        Resposta toResposta(RespostaJpa respostaJpa);

        Collection<RespostaJpa> toRespostasJpa(Collection<Resposta> respostas);

        Collection<Resposta> toRespostas(Collection<RespostaJpa> respostasJpa);

        PacienteJpa toPacienteJpa(Paciente paciente);
    }

}
