package com.letmetrial.api.medico.domain.service;

import com.letmetrial.api.medico.domain.entity.Estudo;
import com.letmetrial.api.medico.domain.entity.Paciente;
import com.letmetrial.api.medico.domain.entity.Resposta;
import com.letmetrial.api.medico.domain.repository.RespostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;

    public Collection<Resposta> salvarTodas(Collection<Resposta> respostas) {
        return respostaRepository.salvarTodas(respostas
                .stream()
                .map(this::atualizarResposta)
                .toList());
    }

    public Collection<Resposta> buscarPorEstudoEPacienteId(Estudo estudo, Paciente paciente) {
        return respostaRepository.buscarPorEstudoEPacienteId(estudo.getId(), paciente.getId());
    }

    private Resposta atualizarResposta(Resposta resposta) {
        Resposta auxiliar = respostaRepository
                .buscarPorCriterioIdEPacienteId(resposta.getCriterio().getId(), resposta.getPaciente().getId())
                .orElse(resposta);
        auxiliar.setResposta(resposta.getResposta());
        return auxiliar;
    }

    public Collection<Resposta> buscarTodasDoPaciente(Paciente paciente){
        return respostaRepository.buscarTodasRespostasPorPaciente(paciente);
    }

}
