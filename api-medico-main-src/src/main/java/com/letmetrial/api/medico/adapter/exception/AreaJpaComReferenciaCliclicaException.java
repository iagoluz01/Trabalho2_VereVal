package com.letmetrial.api.medico.adapter.exception;

import com.letmetrial.api.medico.adapter.entity.AreaJpa;
import com.letmetrial.api.medico.domain.entity.Medico;
import com.letmetrial.api.medico.domain.exception.ReferenciaCiclicaException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AreaJpaComReferenciaCliclicaException extends ReferenciaCiclicaException {

    public AreaJpaComReferenciaCliclicaException(AreaJpa principal, AreaJpa atual) {
        super(Medico.class, printarCadeiaDeReferencias(principal, atual));
    }

    private static String printarCadeiaDeReferencias(AreaJpa principal, AreaJpa atual) {
        return String.join(" (->) ",
                buscarCadeiaDeReferencias(principal, atual, new ArrayList<>()).stream()
                        .map(UUID::toString)
                        .toList());
    }

    private static List<UUID> buscarCadeiaDeReferencias(AreaJpa principal, AreaJpa atual, List<UUID> idsVisitados) {
        idsVisitados.add(principal.getId());

        if (idsVisitados.contains(atual.getId())) {
            idsVisitados.add(atual.getId());
            return idsVisitados;
        }

        if (principal.getAreaPrincipal() == null)
            return idsVisitados;

        return buscarCadeiaDeReferencias(principal.getAreaPrincipal(), atual, idsVisitados);
    }

}
