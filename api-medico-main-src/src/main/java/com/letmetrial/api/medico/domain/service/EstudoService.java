package com.letmetrial.api.medico.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.letmetrial.api.medico.domain.entity.Area;
import com.letmetrial.api.medico.domain.entity.CriterioEstudo;
import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.exception.CriterioAusenteException;
import com.letmetrial.api.medico.domain.exception.EstudoNaoEncontradoException;
import com.letmetrial.api.medico.domain.exception.RespostaDuplicadaException;
import com.letmetrial.api.medico.domain.repository.EstudoRepository;
import com.letmetrial.api.medico.domain.vo.RespostaValidada;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EstudoService {

    private final EstudoRepository estudoRepository;

    public Collection<Estudo> buscarPorArea(Area area) {
        return estudoRepository.buscarPorArea(area);
    }

    public Estudo buscarPorId(UUID id) {
        return estudoRepository
                .buscarPorId(id)
                .orElseThrow(() -> EstudoNaoEncontradoException.BUSCA_POR_ID(id));
    }

    public Collection<RespostaValidada> validar(
            Collection<CriterioEstudo> criteriosEstudo,
            Collection<Resposta> respostas) {

        return criteriosEstudo
                .stream()
                .map(criterioEstudo -> {

                    List<Resposta> respostasCriterio = respostas
                            .stream()
                            .filter(resposta -> criterioEstudo.getCriterio().getId()
                                    .equals(resposta.getCriterio().getId()))
                            .toList();

                    if (respostasCriterio.size() == 0)
                        throw CriterioAusenteException.COM_ID(criterioEstudo.getCriterio().getId());

                    Resposta resposta = respostasCriterio.getFirst();

                    if (respostasCriterio.size() > 1)
                        throw RespostaDuplicadaException.COM_CRITERIO_ID(resposta.getCriterio().getId());

                    boolean passou = criterioEstudo
                            .getRespostaEsperada().equals(resposta.getResposta()) ||
                            criterioEstudo.isOpcional();

                    return new RespostaValidada(resposta, criterioEstudo, passou);

                }).toList();
    }

}
