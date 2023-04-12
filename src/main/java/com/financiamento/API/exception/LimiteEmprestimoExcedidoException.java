package com.financiamento.API.exception;

public class LimiteEmprestimoExcedidoException extends RuntimeException {

    public LimiteEmprestimoExcedidoException(String message) {
        super(message);
    }
}
