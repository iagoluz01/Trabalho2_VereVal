package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.CriterioEstudoJpa;
import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;

@SpringBootTest
public class CriterioEstudoRepositoryJpaTest {
    @Mock
    CriterioEstudoRepositoryJpa.CriterioEstudoRepositoryJpaQueries queries;

    @Spy
    CriterioEstudoRepositoryJpa.CriterioEstudoJpaMapper mapper = Mappers.getMapper(CriterioEstudoRepositoryJpa.CriterioEstudoJpaMapper.class);

    @InjectMocks
    CriterioEstudoRepositoryJpa repository;

    @Test
    public void buscarPorEstudoSucesso() {
        UUID estudoId = UUID.randomUUID();
        Collection<CriterioEstudoJpa> criteriosEstudoJpa = Collections.singletonList(new CriterioEstudoJpa());
        Collection<CriterioEstudo> criteriosEstudo = Collections.singletonList(new CriterioEstudo());

        when(queries.findByEstudoId(estudoId)).thenReturn(criteriosEstudoJpa);
        when(mapper.toCriteriosEstudos(criteriosEstudoJpa)).thenReturn(criteriosEstudo);

        Estudo estudo = new Estudo();
        estudo.setId(estudoId);

        Collection<CriterioEstudo> result = repository.buscarPorEstudo(estudo);

        assertEquals(criteriosEstudo, result);
        verify(queries, times(1)).findByEstudoId(estudoId);
    }

}
