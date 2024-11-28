package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.BuscarMedicoPorIdUC.BuscarMedicoPorIdUCMapepr;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.service.MedicoService;

@SpringBootTest
public class BuscarMedicoPorIdUCTest {

    @Mock
    MedicoService medicoService;

    @Spy
    BuscarMedicoPorIdUCMapepr mapper = Mappers.getMapper(BuscarMedicoPorIdUCMapepr.class);

    @InjectMocks
    BuscarMedicoPorIdUC buscarMedicoPorIdUC;

    @Test
    public void runSucesso() {

        UUID idMedico = UUID.randomUUID();
        String nome = "nome";

        Medico medico = new Medico();
        medico.setId(idMedico);
        medico.setCrm(nome);

        when(medicoService.buscarPorId(idMedico))
                .thenReturn(medico);

        when(mapper.toMedicoDTO(any()))
                .thenCallRealMethod();

        BuscarMedicoPorIdUC.MedicoDTO medicoDTO = buscarMedicoPorIdUC.run(idMedico);

        assertEquals(medico.getId(), medicoDTO.id());
        assertEquals(medico.getCrm(), medicoDTO.crm());

        verify(medicoService, times(1)).buscarPorId(any());

    }

}
