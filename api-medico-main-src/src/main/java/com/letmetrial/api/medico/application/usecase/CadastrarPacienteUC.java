package com.letmetrial.api.medico.application.usecase;

import java.time.LocalDate;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.enumeration.Sexo;
import com.letmetrial.api.medico.domain.service.MedicoService;
import com.letmetrial.api.medico.domain.service.PacienteService;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CadastrarPacienteUC {

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    private final CadastrarPacienteUCMapper mapper;

    public PacienteDTO run(UUID medicoId, PacienteNovoDTO pacienteNovoDTO) {
        return mapper.toPacienteDTO(pacienteService
                .cadastrar(mapper.toPaciente(pacienteNovoDTO, medicoService.buscarPorId(medicoId))));
    }

    public record PacienteNovoDTO(
            @Schema(example = "83169808") @Pattern(regexp = "\\d{8}", message = "CEP deve conter somente 8 dígitos") @NotBlank String cep,
            @Schema(example = "fulano") @Pattern(regexp = "^(?!\\s*$).+", message = "Apelido em um formato inválido") @NotBlank String apelido,
            @NotNull LocalDate dataNascimento,
            @NotNull Sexo sexo) {
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
    public interface CadastrarPacienteUCMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "estudosValidados", ignore = true)
        Paciente toPaciente(PacienteNovoDTO pacienteNovoDTO, Medico medico);

        @Mapping(target = "medicoId", source = "medico.id")
        PacienteDTO toPacienteDTO(Paciente paciente);

    }

}
