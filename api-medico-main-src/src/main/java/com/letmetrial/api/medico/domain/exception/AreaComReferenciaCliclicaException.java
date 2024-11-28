package com.letmetrial.api.medico.domain.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.Medico;

public class AreaComReferenciaCliclicaException extends ReferenciaCiclicaException {

    public AreaComReferenciaCliclicaException(Area principal, Area atual) {
        super(Medico.class, printarCadeiaDeReferencias(principal, atual));
    }

    private static String printarCadeiaDeReferencias(Area principal, Area atual) {
        return String.join(" (->) ",
                buscarCadeiaDeReferencias(principal, atual, new ArrayList<>()).stream()
                        .map(UUID::toString)
                        .toList());
    }

    private static List<UUID> buscarCadeiaDeReferencias(Area principal, Area atual, List<UUID> idsVisitados) {
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
