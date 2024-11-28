package com.letmetrial.api.medico.domain.exception;

import com.letmetrial.api.medico.domain.entity.Paciente;

public class PacienteInvalidoExpection extends RecursoInvalidoExpection {

    public PacienteInvalidoExpection(Atributo... atributos) {
        super(Paciente.class, atributos);
    }

}
