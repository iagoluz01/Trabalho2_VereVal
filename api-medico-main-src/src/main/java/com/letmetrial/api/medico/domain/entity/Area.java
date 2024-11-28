package com.letmetrial.api.medico.domain.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.letmetrial.api.medico.domain.exception.AreaComReferenciaCliclicaException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area {

    private UUID id;

    private String nome;

    private Area areaPrincipal;

    public void setAreaPrincipal(Area areaPrincipal) {
        if (possuiCiclo(areaPrincipal))
            throw new AreaComReferenciaCliclicaException(areaPrincipal, this);

        this.areaPrincipal = areaPrincipal;
    }

    private boolean possuiCiclo(Area areaPrincipal) {

        Set<UUID> idsVisitados = new HashSet<>();

        do {

            if(areaPrincipal == null)
                return false;

            idsVisitados.add(areaPrincipal.id);
            if (idsVisitados.contains(this.id))
                return true;
        } while ((areaPrincipal = areaPrincipal.areaPrincipal == null ? null : areaPrincipal.areaPrincipal) != null);

        return false;
    }

}
