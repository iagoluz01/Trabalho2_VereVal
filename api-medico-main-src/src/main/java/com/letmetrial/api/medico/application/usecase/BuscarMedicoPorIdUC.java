package com.letmetrial.api.medico.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.service.MedicoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarMedicoPorIdUC {

    private final MedicoService medicoService;

    private final BuscarMedicoPorIdUCMapepr mapper;

    public MedicoDTO run(UUID id) {
        return mapper.toMedicoDTO(medicoService.buscarPorId(id));
    }

    public record MedicoDTO(UUID id, String crm) {
    }

    @Mapper
    interface BuscarMedicoPorIdUCMapepr {

        MedicoDTO toMedicoDTO(Medico medico);

    }

}
