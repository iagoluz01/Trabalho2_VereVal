package com.letmetrial.api.medico.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.application.usecase.BuscarEstudoPorIdUC.BuscarEstudoPorIdUCMapper;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.service.EstudoService;
import com.letmetrial.api.medico.domain.entity.Area;

@SpringBootTest
public class BuscarEstudoPorIdUCTest {

    @Mock
    EstudoService estudoService;

    @Spy
    BuscarEstudoPorIdUCMapper mapper = Mappers.getMapper(BuscarEstudoPorIdUCMapper.class);

    @InjectMocks
    BuscarEstudoPorIdUC buscarEstudoPorIdUC;

    @Test
    public void runSucesso() {

        UUID estudoId = UUID.randomUUID();

        Estudo estudo = new Estudo();
        Area area = new Area();
        estudo.setId(estudoId);
        estudo.setAreas(Collections.singleton(area));

        when(estudoService.buscarPorId(estudoId))
                .thenReturn(estudo);

        when(mapper.toEstudoDTO(any()))
                .thenCallRealMethod();

        when(mapper.toAreaDTO(any()))
                .thenCallRealMethod();

        BuscarEstudoPorIdUC.EstudoDTO estudoDTO = buscarEstudoPorIdUC.run(estudoId);

        assertEquals(estudo.getId(), estudoDTO.id());


        verify(estudoService, times(1)).buscarPorId(any());

    }

}
