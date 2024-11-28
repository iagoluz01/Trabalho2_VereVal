package com.letmetrial.api.medico.adapter.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmetrial.api.medico.application.usecase.BuscarAreasPrincipaisUC;
import com.letmetrial.api.medico.application.usecase.BuscarSubAreasDeUmaAreaUC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("areas")
@RestController
public class AreaController {

    private final BuscarSubAreasDeUmaAreaUC buscarSubAreasDeUmaAreaUC;

    private final BuscarAreasPrincipaisUC buscarAreasPrincipaisUC;

    @Operation(summary = "Buscar sub-áreas por ID área-princial", description = "Caso encontre a área-princial informada retorna todas as suas sub-áreas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarSubAreasDeUmaAreaUC.AreaDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}/sub-areas")
    @Cacheable(value = "subAreasCache")
    public ResponseEntity<Collection<BuscarSubAreasDeUmaAreaUC.AreaDTO>> buscarSubAreas(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(buscarSubAreasDeUmaAreaUC.run(id));
    }

    @Operation(summary = "Buscar todas áreas principais", description = "Retorna todas áreas que não possuem áreas principais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarAreasPrincipaisUC.AreaDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/principais")
    @Cacheable(value = "areasPrincipaisCache")
    public ResponseEntity<Collection<BuscarAreasPrincipaisUC.AreaDTO>> buscarAreasPrincipais() {
        return ResponseEntity.ok(buscarAreasPrincipaisUC.run());
    }

}
