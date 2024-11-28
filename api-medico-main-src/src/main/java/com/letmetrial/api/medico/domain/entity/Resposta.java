package com.letmetrial.api.medico.domain.entity;

import java.util.UUID;

import com.letmetrial.api.medico.domain.enumeration.TipoResposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resposta {

    private UUID id;

    private TipoResposta resposta;

    private Paciente paciente;

    private Criterio criterio;

}
