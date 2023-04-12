package com.financiamento.API.exception;

public class EmprestimoNotFoundException extends RuntimeException {
    public EmprestimoNotFoundException(Long id, String cpfCliente) {
        super("Emprestimo não encontrado com id " + id + " e CPF do cliente " + cpfCliente);
    }
}
