package com.letmetrial.api.medico.adapter.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmetrial.api.medico.application.usecase.BuscarCriteriosPorEstudoIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarEstudoPorIdUC;
import com.letmetrial.api.medico.application.usecase.BuscarEstudosPorAreaIdUC;
import com.letmetrial.api.medico.application.usecase.ValidarRespostasEstudoUC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("estudos")
@RestController
public class EstudoController {

    private final BuscarEstudosPorAreaIdUC buscarEstudosPorAreaIdUC;

    private final BuscarEstudoPorIdUC buscarEstudoPorIdUC;

    private final ValidarRespostasEstudoUC validarRespostasEstudoUC;

    private final BuscarCriteriosPorEstudoIdUC buscarCriteriosPorEstudoIdUC;

    @Operation(summary = "Buscar estudos por Area ID", description = "Caso encontre estudos para a area informada, retorna os estudos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarEstudosPorAreaIdUC.EstudoDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("areas/{id}")
    public ResponseEntity<Collection<BuscarEstudosPorAreaIdUC.EstudoDTO>> buscarEstudoPorAreaId(
            @PathVariable("id") UUID id) {
        return ResponseEntity.ok(buscarEstudosPorAreaIdUC.run(id));
    }

    @Operation(summary = "Buscar estudo por ID", description = "Caso encontre algum estudo para o id informado, retorna o estudo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BuscarEstudoPorIdUC.EstudoDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<BuscarEstudoPorIdUC.EstudoDTO> buscarEstudoPorId(
            @PathVariable("id") UUID id) {
        return ResponseEntity.ok(buscarEstudoPorIdUC.run(id));
    }

    @Operation(summary = "Valida respostas do paciente com estudo", description = "Retorna todas as respostas validadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ValidarRespostasEstudoUC.ResultadoFinalDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/{estudoId}/matches/medicos/{medicoId}/pacientes/{pacienteId}")
    public ResponseEntity<ValidarRespostasEstudoUC.ResultadoFinalDTO> validarRespostas(
            @PathVariable("estudoId") UUID estudoId,
            @PathVariable("medicoId") UUID medicoId,
            @PathVariable("pacienteId") UUID pacienteId,
            @RequestBody Collection<ValidarRespostasEstudoUC.RespostaDTO> respostas) {

        return ResponseEntity.ok(validarRespostasEstudoUC.run(estudoId, medicoId, pacienteId, respostas));

    }

    @Operation(summary = "Buscar criterios por estudo ID", description = "Caso encontre algum criterio para o id informado, retorna o criterio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BuscarCriteriosPorEstudoIdUC.CriterioDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("{id}/criterios")
    public ResponseEntity<Collection<BuscarCriteriosPorEstudoIdUC.CriterioDTO>> buscarCriteriosPorEstudoId(
            @PathVariable("id") UUID id) {
        return ResponseEntity.ok(buscarCriteriosPorEstudoIdUC.run(id));
    }

}
