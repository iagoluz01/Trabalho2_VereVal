package com.letmetrial.api.medico.adapter.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.letmetrial.api.medico.adapter.exception.AreaJpaComReferenciaCliclicaException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "areas")
@Entity
public class AreaJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "area_principal_id")
    private AreaJpa areaPrincipal;

    public void setAreaPrincipal(AreaJpa areaPrincipal) {
        if (possuiCiclo(areaPrincipal))
            throw new AreaJpaComReferenciaCliclicaException(areaPrincipal, this);

        this.areaPrincipal = areaPrincipal;
    }

    private boolean possuiCiclo(AreaJpa areaPrincipal) {

        Set<UUID> idsVisitados = new HashSet<>();

        do {
            if (areaPrincipal == null)
                return false;

            idsVisitados.add(areaPrincipal.id);
            if (idsVisitados.contains(this.id))
                return true;
        } while ((areaPrincipal = areaPrincipal.areaPrincipal == null ? null : areaPrincipal.areaPrincipal) != null);

        return false;
    }

}
