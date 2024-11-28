package com.letmetrial.api.medico.domain.vo;

import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Resposta;

public record RespostaValidada(Resposta resposta, CriterioEstudo criterioEstudo, Boolean passou) {

}
