package com.financiamento.API.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.financiamento.API.exception.LimiteEmprestimoExcedidoException;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @CPF
    @Column(unique = true)
    private String cpf;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}")
    private String telefone;

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @Pattern(regexp = "[0-9]{5}\\-[0-9]{3}")
    private String cep;

    @DecimalMin("0.0")
    private Double rendaMensal;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;
    
    private int limiteCredito;
    
    private BigDecimal valorTotalEmprestimos;

    // Construtor vazio
    public Cliente() {
    }

    // Construtor com parâmetros
    public Cliente(String nome, String cpf, String email, String telefone, String rua, String numero, String cep,
            Double rendaMensal) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.rendaMensal = rendaMensal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        if (emprestimos != null && !emprestimos.isEmpty()) {
            double rendaTotal = emprestimos.stream()
                    .flatMapToDouble(e -> DoubleStream.of(e.getValorInicial().doubleValue()))
                    .sum();
            if (rendaTotal > getRendaMensal() * 10) {
                throw new LimiteEmprestimoExcedidoException("Limite de crédito excedido.");
            }
        }
        this.emprestimos = emprestimos;
    }

    
    public void addEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void removeEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }
    
    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataNascimento = LocalDate.of(1995, 8, 27); // supondo que a data de nascimento é fixa para o exemplo
        return Period.between(dataNascimento, hoje).getYears();
    }    

    public BigDecimal getLimiteCredito() {
        int idadeCliente = this.getIdade();
        double rendaCliente = this.getRendaMensal();
        BigDecimal limiteCredito = BigDecimal.ZERO;
        if (idadeCliente >= 18 && idadeCliente <= 25) {
            if (rendaCliente <= 2000) limiteCredito = new BigDecimal("1000.00");
            else if (rendaCliente <= 5000) limiteCredito = new BigDecimal("3000.00");
            else limiteCredito = new BigDecimal("5000.00");
        } else if (idadeCliente >= 26 && idadeCliente <= 35) {
            if (rendaCliente <= 2000) limiteCredito = new BigDecimal("2000.00");
            else if (rendaCliente <= 5000) limiteCredito = new BigDecimal("4000.00");
            else limiteCredito = new BigDecimal("7000.00");
        } else if (idadeCliente >= 36 && idadeCliente <= 50) {
            if (rendaCliente <= 2000) limiteCredito = new BigDecimal("3000.00");
            else if (rendaCliente <= 5000) limiteCredito = new BigDecimal("5000.00");
            else limiteCredito = new BigDecimal("9000.00");
        } else if (idadeCliente >= 51) {
            if (rendaCliente <= 2000) limiteCredito = new BigDecimal("4000.00");
            else if (rendaCliente <= 5000) limiteCredito = new BigDecimal("6000.00");
            else limiteCredito = new BigDecimal("11000.00");
        }
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal novoLimiteCredito) {
        int idadeCliente = getIdade();
        double rendaCliente = getRendaMensal().doubleValue();
        int limiteCredito = 0;
        if (idadeCliente >= 18 && idadeCliente <= 25) {
            if (rendaCliente <= 2000) {
                limiteCredito = 1000;
            } else if (rendaCliente <= 5000) {
                limiteCredito = 3000;
            } else {
                limiteCredito = 5000;
            }
        } else if (idadeCliente >= 26 && idadeCliente <= 35) {
            if (rendaCliente <= 3000) {
                limiteCredito = 5000;
            } else if (rendaCliente <= 7000) {
                limiteCredito = 10000;
            } else {
                limiteCredito = 15000;
            }
        } else if (idadeCliente >= 36 && idadeCliente <= 50) {
            if (rendaCliente <= 5000) {
                limiteCredito = 10000;
            } else if (rendaCliente <= 10000) {
                limiteCredito = 20000;
            } else {
                limiteCredito = 30000;
            }
        } else if (idadeCliente > 50) {
            if (rendaCliente <= 8000) {
                limiteCredito = 20000;
            } else if (rendaCliente <= 15000) {
                limiteCredito = 30000;
            } else {
                limiteCredito = 50000;
            }
        }
        // Define o limite de crédito do cliente
        this.limiteCredito = limiteCredito;
    }
    
    public BigDecimal getValorTotalEmprestimos() {
        return valorTotalEmprestimos != null ? valorTotalEmprestimos : BigDecimal.ZERO;
    }

    public void setValorTotalEmprestimos(BigDecimal valorTotalEmprestimos) {
        this.valorTotalEmprestimos = valorTotalEmprestimos;
    }

}   
