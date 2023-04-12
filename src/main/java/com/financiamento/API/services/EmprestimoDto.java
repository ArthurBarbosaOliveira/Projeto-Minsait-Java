package com.financiamento.API.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.financiamento.API.entity.Emprestimo;
import com.financiamento.API.types.TipoRelacionamento;

public class EmprestimoDto {
    
    @NotNull
    private Long idCliente;
    
    @NotNull
    private BigDecimal valorInicial;
    
    @NotNull
    private LocalDate dataEmprestimo;
    
    @NotNull
    private TipoRelacionamento tipoRelacionamento;
    
    public EmprestimoDto() {
    }
    
    public Long getIdCliente() {
        return idCliente;
    }
    
    public BigDecimal getValorInicial() {
        return valorInicial;
    }
    
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }
    
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }
    
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }
    public static EmprestimoDto fromEmprestimo(Emprestimo emprestimo) {
        EmprestimoDto dto = new EmprestimoDto();
        dto.setIdCliente(emprestimo.getCliente().getId());
        dto.setValorInicial(emprestimo.getValorInicial());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setTipoRelacionamento(emprestimo.getTipoRelacionamento());
        return dto;
    }    
}
