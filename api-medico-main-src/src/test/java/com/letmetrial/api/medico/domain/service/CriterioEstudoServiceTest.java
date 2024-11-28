package com.letmetrial.api.medico.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.repository.CriterioEstudoRepository;

@SpringBootTest
public class CriterioEstudoServiceTest {

    @Mock
    CriterioEstudoRepository criterioEstudoRepository;

    @InjectMocks
    CriterioEstudoService criterioEstudoService;

    @Test
    public void buscarPorEstudoReturnsCriteriosWhenFound() {
        Estudo estudo = new Estudo();
        Collection<CriterioEstudo> criteriosCollection = Collections.singleton(new CriterioEstudo());

        when(criterioEstudoRepository.buscarPorEstudo(estudo)).thenReturn(criteriosCollection);

        Collection<CriterioEstudo> result = criterioEstudoService.buscarPorEstudo(estudo);

        assertEquals(criteriosCollection, result);
        verify(criterioEstudoRepository, times(1)).buscarPorEstudo(estudo);
    }

}
