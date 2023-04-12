package com.financiamento.API.request;

import javax.validation.constraints.NotNull;

import com.financiamento.API.types.TipoRelacionamento;

public class EmprestimoRequest {

    @NotNull
    private String cpfCliente;
    
    @NotNull
    private Double valorInicial;
    
    @NotNull
    private Integer numeroParcelas;
    
    @NotNull
    private TipoRelacionamento tipoRelacionamento;

    
    public EmprestimoRequest() {}
    
    public EmprestimoRequest(String cpfCliente, Double valorInicial, Integer numeroParcelas) { // alteração do nome do parâmetro
        this.cpfCliente = cpfCliente;
        this.valorInicial = valorInicial;
        this.numeroParcelas = numeroParcelas;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Double getValorInicial() { 
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }
    
}
