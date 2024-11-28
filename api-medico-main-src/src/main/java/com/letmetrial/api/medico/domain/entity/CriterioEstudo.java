package com.letmetrial.api.medico.domain.entity;

import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.TipoResposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriterioEstudo {

    private UUID id;

    private boolean opcional;

    private TipoResposta respostaEsperada;

    private Criterio criterio;

    private Estudo estudo;

}
