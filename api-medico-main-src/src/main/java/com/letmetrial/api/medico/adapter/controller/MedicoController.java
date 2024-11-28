package com.letmetrial.api.medico.adapter.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmetrial.api.medico.application.usecase.BuscarEstudosValidadosPorPacienteEMedicoIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarMedicoPorIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarPacienteDoMedicoPorIDUC;
import com.letmetrial.api.medico.application.usecase.BuscarRespostasPacienteEstudoUC;
import com.letmetrial.api.medico.application.usecase.BuscarRespostasPorPacienteUC;
import com.letmetrial.api.medico.application.usecase.BuscarTodosPacienteDoMedicoUC;
import com.letmetrial.api.medico.application.usecase.CadastrarPacienteUC;
import com.letmetrial.api.medico.application.usecase.CadastrarPacienteUC.PacienteDTO;
import com.letmetrial.api.medico.application.usecase.SalvarRespostasPacienteUC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("medicos")
@RestController
public class MedicoController {

    private final BuscarMedicoPorIdUC buscarMedicoPorIdUC;

    private final CadastrarPacienteUC cadastrarPacienteUC;

    private final SalvarRespostasPacienteUC salvarRespostasPacienteUC;

    private final BuscarTodosPacienteDoMedicoUC buscarTodosPacienteDoMedico;

    private final BuscarPacienteDoMedicoPorIDUC buscarPacienteDoMedicoPorIDUC;

    private final BuscarEstudosValidadosPorPacienteEMedicoIdUC buscarEstudosValidadosPorPacienteEMedicoIdUC;

    private final BuscarRespostasPacienteEstudoUC buscarRespostasPacienteEstudoUC;

    private final BuscarRespostasPorPacienteUC buscarRespostasPorPacienteUC;

    @Operation(summary = "Buscar médico por ID", description = "Caso exista, retorna médico do ID informado ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BuscarMedicoPorIdUC.MedicoDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BuscarMedicoPorIdUC.MedicoDTO> buscarMedicoPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(buscarMedicoPorIdUC.run(id));
    }

    @Operation(summary = "Salvar respostas do paciente", description = "Salva as respostas do paciente para os respectivos critérios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping("/{medicoId}/pacientes/{pacienteId}/criterios")
    public ResponseEntity<Void> salvarRespostasPaciente(
            @PathVariable("medicoId") UUID medicoId,
            @PathVariable("pacienteId") UUID pacienteId,
            @RequestBody Collection<SalvarRespostasPacienteUC.RespostaDTO> respostas) {
        salvarRespostasPacienteUC.run(medicoId, pacienteId, respostas);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Cadastro de Paciente por Médico", description = "Caso os dados do paciente sejam válidos, será cadastrado o Paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CadastrarPacienteUC.PacienteDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping("{medicoId}/pacientes")
    public ResponseEntity<CadastrarPacienteUC.PacienteDTO> cadastrarPaciente(@PathVariable("medicoId") UUID medicoId,
            @RequestBody @Validated CadastrarPacienteUC.PacienteNovoDTO paciente) {

        PacienteDTO pacienteCriado = cadastrarPacienteUC.run(medicoId, paciente);

        return ResponseEntity.created(
                Objects.requireNonNull(
                        URI.create(String.format("medicos/%s/pacientes/%s", medicoId, pacienteCriado.id()))))
                .body(pacienteCriado);
    }

    @Operation(summary = "Buscar todos os pacientes do médico", description = "Retorna todos os pacientes do médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarTodosPacienteDoMedicoUC.PacienteDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("{medicoId}/pacientes")
    public ResponseEntity<Collection<BuscarTodosPacienteDoMedicoUC.PacienteDTO>> buscarTodosPacientes(
            @PathVariable("medicoId") UUID medicoId) {
        return ResponseEntity.ok(buscarTodosPacienteDoMedico.run(medicoId));
    }

    @Operation(summary = "Buscar todos os estudo validados por paciente", description = "Retorna todos os estudos validados por paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarEstudosValidadosPorPacienteEMedicoIdUC.EstudoDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("{medicoId}/pacientes/{pacienteId}/estudos")
    public ResponseEntity<Collection<BuscarEstudosValidadosPorPacienteEMedicoIdUC.EstudoDTO>> buscarEstudosValidadosPaciente(
            @PathVariable("medicoId") UUID medicoId, @PathVariable("pacienteId") UUID pacienteId) {
        return ResponseEntity.ok(buscarEstudosValidadosPorPacienteEMedicoIdUC.run(pacienteId, medicoId));
    }

    @Operation(summary = "Buscar paciente do médico", description = "Retorna um paciente específico do médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BuscarPacienteDoMedicoPorIDUC.PacienteDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("{medicoId}/pacientes/{pacienteId}")
    public ResponseEntity<BuscarPacienteDoMedicoPorIDUC.PacienteDTO> buscarPaciente(
            @PathVariable("medicoId") UUID medicoId, @PathVariable("pacienteId") UUID pacienteId) {
        return ResponseEntity.ok(buscarPacienteDoMedicoPorIDUC.run(pacienteId, medicoId));
    }

    @Operation(summary = "Buscar respostas do paciente de um estudo", description = "Retorna as respostas do paciente para um estudo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarRespostasPacienteEstudoUC.RespostaDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("{medicoId}/pacientes/{pacienteId}/estudos/{estudoId}/respostas")
    public ResponseEntity<Collection<BuscarRespostasPacienteEstudoUC.RespostaDTO>> buscarRespostasPacienteEstudo(
            @PathVariable("medicoId") UUID medicoId, @PathVariable("pacienteId") UUID pacienteId,
            @PathVariable("estudoId") UUID estudoId) {
        return ResponseEntity.ok(buscarRespostasPacienteEstudoUC.run(medicoId, pacienteId, estudoId));
    }

    @Operation(summary = "Buscar todas as respostas respondidas por um paciente", description = "Retorna todas as respostas respondidas por um paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarRespostasPorPacienteUC.RespostaDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("{medicoId}/pacientes/{pacienteId}/estudos/respostas")
    public ResponseEntity<Collection<BuscarRespostasPorPacienteUC.RespostaDTO>> buscarRespostasPorPaciente(
            @PathVariable("medicoId") UUID medicoId,
            @PathVariable("pacienteId") UUID pacienteId) {
        return ResponseEntity.ok(buscarRespostasPorPacienteUC.run(medicoId, pacienteId));
    }

}
