package com.letmetrial.api.medico.application.usecase;

import java.time.LocalDate;
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
public class BuscarPacienteDoMedicoPorIDUC {

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final BuscarPacienteDoMedicoMapper mapper;

    public PacienteDTO run(UUID pacienteId, UUID medicoId) {
        return mapper
                .toPacienteDTO(pacienteService.buscarPorIdMedico(pacienteId, medicoService.buscarPorId(medicoId)));
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
    public interface BuscarPacienteDoMedicoMapper {

        @Mapping(target = "medicoId", source = "medico.id")
        PacienteDTO toPacienteDTO(Paciente paciente);

    }

}
