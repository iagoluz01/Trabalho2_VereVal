package com.letmetrial.api.medico.application.usecase;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.enumeration.Sexo;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarTodosPacienteDoMedicoUC {

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final BuscarTodosPacienteDoMedicoMapper mapper;

    public Collection<PacienteDTO> run(UUID medicoId) {
        return mapper.toPacienteDTOColletion(pacienteService.buscarTodosPorMedico(medicoService.buscarPorId(medicoId)));
    }

    public record PacienteDTO(
            UUID id,
            String cep,
            String apelido,
            LocalDate dataNascimento,
            Sexo sexo,
            UUID medicoId) {
    }

    @Mapper
    public interface BuscarTodosPacienteDoMedicoMapper {

        Collection<PacienteDTO> toPacienteDTOColletion(Collection<Paciente> paciente);

        @Mapping(target = "medicoId", source = "medico.id")
        PacienteDTO toPacienteDTO(Paciente paciente);

    }

}
