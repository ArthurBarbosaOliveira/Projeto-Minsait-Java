package com.financiamento.API.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financiamento.API.types.TipoRelacionamento;

@Entity
public class Emprestimo implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    @Column(name = "valor_inicial", precision = 10, scale = 2)
    private BigDecimal valorInicial;

    @Column(name = "valor_final", precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    private TipoRelacionamento tipoRelacionamento;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;
    
    private BigDecimal valorTotalEmprestimos;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Cliente cliente, BigDecimal valorInicial, BigDecimal valorFinal,
            TipoRelacionamento tipoRelacionamento, LocalDate dataEmprestimo) {
        this.id = id;
        this.cliente = cliente;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.tipoRelacionamento = tipoRelacionamento;
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorInicial() {
        return valorInicial != null ? valorInicial : BigDecimal.ZERO;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal != null ? valorFinal : BigDecimal.ZERO;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public BigDecimal getValorTotalEmprestimos() {
        return valorTotalEmprestimos;
    }

    public void setValorTotalEmprestimos(BigDecimal valorTotalEmprestimos) {
        this.valorTotalEmprestimos = valorTotalEmprestimos;
    }
}
