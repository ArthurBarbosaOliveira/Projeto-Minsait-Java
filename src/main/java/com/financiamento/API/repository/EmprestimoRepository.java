package com.financiamento.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financiamento.API.entity.Cliente;
import com.financiamento.API.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByClienteCpf(String cpfCliente);
    Optional<Emprestimo> findByIdAndClienteCpf(Long id, String cpfCliente);
    List<Emprestimo> findByClienteCpfAndId(String cpfCliente, Long id);
    Optional<Emprestimo> findByIdAndCliente(Long idEmprestimo, Cliente cliente);
}
