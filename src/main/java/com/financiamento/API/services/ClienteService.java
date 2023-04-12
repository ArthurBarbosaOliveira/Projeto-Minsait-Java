package com.financiamento.API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financiamento.API.entity.Cliente;
import com.financiamento.API.exception.ClienteNotFoundException;
import com.financiamento.API.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNotFoundException(cpf));
    }


    public void excluirClientePorCpf(String cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isPresent()) {
            clienteRepository.delete(clienteOptional.get());
        }
    }


    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cpf);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setRua(clienteAtualizado.getRua());
            cliente.setNumero(clienteAtualizado.getNumero());
            cliente.setCep(clienteAtualizado.getCep());
            cliente.setRendaMensal(clienteAtualizado.getRendaMensal());
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public String getEmail(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isPresent()) {
            return cliente.get().getEmail();
        } else {
            throw new ClienteNotFoundException(cpf);
        }
    }

    public Cliente adicionarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
