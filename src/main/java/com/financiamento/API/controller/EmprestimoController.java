package com.financiamento.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financiamento.API.entity.Emprestimo;
import com.financiamento.API.services.EmprestimoDto;
import com.financiamento.API.services.EmprestimoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/financiamento/clientes/{cpf}/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    @PostMapping
    public ResponseEntity<Emprestimo> cadastrarEmprestimo(@PathVariable String cpf, @RequestBody EmprestimoDto emprestimoDto) {
        Emprestimo emprestimo = emprestimoService.cadastrarEmprestimo(cpf, emprestimoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable String cpf, @PathVariable Long id) {
        emprestimoService.deletarEmprestimo(cpf, id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimo(@PathVariable String cpf, @PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(cpf, id);
        return ResponseEntity.ok(emprestimo);
    }
    
    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarEmprestimos(@PathVariable String cpf) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos(cpf);
        return ResponseEntity.ok(emprestimos);
    }
    
}
