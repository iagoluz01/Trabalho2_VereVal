package com.letmetrial.api.medico.adapter.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.letmetrial.api.medico.domain.exception.MedicoNaoEncontradoException;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void preparaTeste() {
        this.globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void handleThrowable() {

        String mensagemErro = "ocorreu um erro";
        Throwable erro = new Throwable(mensagemErro);

        ResponseEntity<String> response = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro.getMessage());

        assertEquals(response, globalExceptionHandler.handle(erro));

    }

    @Test
    public void handleRecursoNaoEncontradoException() {

        MedicoNaoEncontradoException erro = MedicoNaoEncontradoException.BUSCA_POR_ID(UUID.randomUUID());

        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());

        assertEquals(response, globalExceptionHandler.handle(erro));

    }

}
