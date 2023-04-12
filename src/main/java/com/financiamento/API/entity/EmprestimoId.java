package com.financiamento.API.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmprestimoId implements Serializable {

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_emprestimo")
    private Long idEmprestimo;

    public EmprestimoId() {
    }

    public EmprestimoId(Long idCliente, Long idEmprestimo) {
        this.idCliente = idCliente;
        this.idEmprestimo = idEmprestimo;
    }

    // getters e setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    // equals e hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmprestimoId that = (EmprestimoId) o;

        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;
        return idEmprestimo != null ? idEmprestimo.equals(that.idEmprestimo) : that.idEmprestimo == null;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (idEmprestimo != null ? idEmprestimo.hashCode() : 0);
        return result;
    }
}
