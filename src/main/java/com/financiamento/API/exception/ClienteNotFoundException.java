package com.financiamento.API.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String cpf) {
        super("Cliente com CPF " + cpf + " não encontrado.");
    }
}
