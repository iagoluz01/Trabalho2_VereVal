package com.letmetrial.api.medico.domain.repository;

import java.util.Collection;

import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;

public interface CriterioEstudoRepository {

    Collection<CriterioEstudo> buscarPorEstudo(Estudo estudo);

}