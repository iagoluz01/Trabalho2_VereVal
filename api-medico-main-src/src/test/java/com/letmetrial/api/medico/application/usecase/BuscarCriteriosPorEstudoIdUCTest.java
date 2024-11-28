package com.letmetrial.api.medico.application.usecase;


import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.service.CriterioEstudoService;
import com.letmetrial.api.medico.domain.service.EstudoService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;


@SpringBootTest
public class BuscarCriteriosPorEstudoIdUCTest {

    @Mock
    EstudoService estudoService;

    @Mock
    CriterioEstudoService criterioEstudoService;

    @Spy
    BuscarCriteriosPorEstudoIdUC.BuscarCriteriosPorEstudoIdUCMapper mapper = Mappers.getMapper(BuscarCriteriosPorEstudoIdUC.BuscarCriteriosPorEstudoIdUCMapper.class);

    @InjectMocks
    BuscarCriteriosPorEstudoIdUC buscarCriteriosPorEstudoIdUC;

    @Test
    public void runReturnsCriteriosWhenFound() {
        UUID id = UUID.randomUUID();
        CriterioEstudo criterioEstudo = new CriterioEstudo();
        Estudo estudo = new Estudo();
        estudo.setId(id);
        Collection<CriterioEstudo> criteriosCollection = Collections.singleton(criterioEstudo);

        when(estudoService.buscarPorId(id)).thenReturn(estudo);
        when(criterioEstudoService.buscarPorEstudo(estudo)).thenReturn(criteriosCollection);
        when(mapper.toCriterioDTO(any())).thenReturn(Collections.singleton(new BuscarCriteriosPorEstudoIdUC.CriterioDTO(UUID.randomUUID(), "pergunta")));

        Collection<BuscarCriteriosPorEstudoIdUC.CriterioDTO> result = buscarCriteriosPorEstudoIdUC.run(id);

        assertFalse(result.isEmpty());
        verify(estudoService, times(1)).buscarPorId(id);
        verify(criterioEstudoService, times(1)).buscarPorEstudo(estudo);
        verify(mapper, times(1)).toCriterioDTO(any());
    }

}
