package com.letmetrial.api.medico.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.letmetrial.api.medico.adapter.entity.MedicoJpa;
import com.letmetrial.api.medico.adapter.repository.MedicoRepositoryJpa.MedicoRepositoryJpaMapper;
import com.letmetrial.api.medico.adapter.repository.MedicoRepositoryJpa.MedicoRepositoryJpaQueries;
import com.letmetrial.api.medico.domain.entity.Medico;

@SpringBootTest
public class MedicoRepositoryJpaTest {

    @Mock
    MedicoRepositoryJpaQueries medicoRepositoryJpaQueries;

    @Spy
    MedicoRepositoryJpaMapper mapper = Mappers.getMapper(MedicoRepositoryJpaMapper.class);

    @InjectMocks
    MedicoRepositoryJpa medicoRepositoryJpa;

    @Test
    public void buscarPorIdSucesso() {

        UUID medicoId = UUID.randomUUID();

        Medico medicoDomain = new Medico();

        MedicoJpa medicoJpa = new MedicoJpa();

        when(mapper.toMedico(medicoJpa))
                .thenReturn(medicoDomain);

        when(medicoRepositoryJpaQueries.findById(Objects.requireNonNull(medicoId)))
                .thenReturn(Optional.of(medicoJpa));

        assertEquals(Optional.of(medicoDomain), medicoRepositoryJpa.buscarPorId(medicoId));

        verify(medicoRepositoryJpaQueries, times(1)).findById(medicoId);
    }

    @Test
    public void toMedicoMapperSucesso() {

        UUID medicoId = UUID.randomUUID();
        String crm = "0123456789/SP";

        MedicoJpa medicoJpa = new MedicoJpa();
        medicoJpa.setId(medicoId);
        medicoJpa.setCrm(crm);

        when(mapper.toMedico(any()))
                .thenCallRealMethod();

        Medico medicoDomainRetornado = mapper.toMedico(medicoJpa);

        assertEquals(medicoDomainRetornado.getId(), medicoJpa.getId());
        assertEquals(medicoDomainRetornado.getCrm(), medicoJpa.getCrm());
    }

}
