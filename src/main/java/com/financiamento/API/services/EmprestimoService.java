package com.financiamento.API.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financiamento.API.entity.Cliente;
import com.financiamento.API.entity.Emprestimo;
import com.financiamento.API.entity.EmprestimoId;
import com.financiamento.API.exception.ClienteNotFoundException;
import com.financiamento.API.exception.EmprestimoNotFoundException;
import com.financiamento.API.exception.LimiteEmprestimoExcedidoException;
import com.financiamento.API.exception.TipoRelacionamentoInvalidoException;
import com.financiamento.API.repository.ClienteRepository;
import com.financiamento.API.repository.EmprestimoRepository;
import com.financiamento.API.request.EmprestimoRequest;
import com.financiamento.API.types.TipoRelacionamento;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Emprestimo cadastrarEmprestimo(String cpfCliente, EmprestimoDto emprestimoDto) {
        Cliente cliente = clienteRepository.findByCpf(cpfCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        BigDecimal limiteCredito = cliente.getLimiteCredito();
        BigDecimal valorInicial = emprestimoDto.getValorInicial();
        BigDecimal valorTotalEmprestimos = cliente.getValorTotalEmprestimos();

        if (valorInicial.compareTo(limiteCredito) > 0) {
            throw new LimiteEmprestimoExcedidoException("Valor inicial do empréstimo excede o limite de crédito do cliente");
        }

        TipoRelacionamento tipoRelacionamento = emprestimoDto.getTipoRelacionamento();

        if (valorTotalEmprestimos.add(valorInicial).compareTo(limiteCredito) > 0) {
            throw new LimiteEmprestimoExcedidoException("Valor total dos empréstimos do cliente excede o limite de crédito");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setValorInicial(valorInicial);
        emprestimo.setTipoRelacionamento(tipoRelacionamento);
        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());

        BigDecimal valorFinal;
        if (tipoRelacionamento == TipoRelacionamento.OURO) {
            int qtdEmprestimos = cliente.getEmprestimos().size();
            valorFinal = tipoRelacionamento.calcularValorFinal(valorInicial, qtdEmprestimos);
        } else {
            valorFinal = tipoRelacionamento.calcularValorFinal(valorInicial);
        }

        emprestimo.setValorFinal(valorFinal);

        emprestimo = emprestimoRepository.save(emprestimo);

        List<Emprestimo> emprestimos = cliente.getEmprestimos();
        emprestimos.add(emprestimo);
        cliente.setEmprestimos(emprestimos);
        
        // Atualizar o valor total de empréstimos do cliente
        cliente.setValorTotalEmprestimos(atualizarValorTotalEmprestimos(cliente));
        
        clienteRepository.save(cliente);

        return emprestimo;
    }

    private BigDecimal atualizarValorTotalEmprestimos(Cliente cliente) {
        BigDecimal totalEmprestimos = BigDecimal.ZERO;
        for (Emprestimo emprestimo : cliente.getEmprestimos()) {
            totalEmprestimos = totalEmprestimos.add(emprestimo.getValorInicial());
        }
        return totalEmprestimos;
    }



    public EmprestimoDto findEmprestimoByIdAndCliente(Long id, String cpfCliente) {
        Emprestimo emprestimo = findByIdAndCliente(id, cpfCliente);
        return EmprestimoDto.fromEmprestimo(emprestimo);
    }
    
    public List<Emprestimo> listarEmprestimos(String cpfCliente) {
        return emprestimoRepository.findByClienteCpf(cpfCliente);
    }

    public List<EmprestimoDto> findEmprestimosByCliente(String cpfCliente) {
        List<Emprestimo> emprestimos = findByCliente(cpfCliente).getEmprestimos();
        return emprestimos.stream().map(EmprestimoDto::fromEmprestimo).collect(Collectors.toList());
    }

    public void deletarEmprestimo(String cpfCliente, Long idEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimo(cpfCliente, idEmprestimo);
        double valorInicial = emprestimo.getValorInicial().doubleValue();

        Cliente cliente = emprestimo.getCliente();
        List<Emprestimo> emprestimos = cliente.getEmprestimos();

        // Verifica se o valor do empréstimo a ser deletado foi usado para calcular o limite de crédito de outros empréstimos.
        double rendaTotal = emprestimos.stream()
                .filter(e -> !e.getId().equals(emprestimo.getId())) // exclui o empréstimo a ser deletado
                .flatMapToDouble(e -> DoubleStream.of(e.getValorInicial().doubleValue()))
                .sum();
        if (rendaTotal > cliente.getRendaMensal() * 10) {
            throw new LimiteEmprestimoExcedidoException("Não é possível excluir o empréstimo porque o valor foi usado para calcular o limite de crédito de outros empréstimos.");
        }

        emprestimos.remove(emprestimo);
        cliente.setEmprestimos(emprestimos);
        cliente.setLimiteCredito(cliente.getLimiteCredito().add(new BigDecimal(valorInicial)));
        clienteRepository.save(cliente);

        emprestimoRepository.delete(emprestimo);
    }

    
    public Emprestimo buscarEmprestimo(String cpf, Long idEmprestimo) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o CPF: " + cpf));

        return emprestimoRepository.findByIdAndCliente(idEmprestimo, cliente)
                .orElseThrow(() -> new EmprestimoNotFoundException(idEmprestimo, "Empréstimo não encontrado para o cliente de CPF: " + cpf));
    }


    private void validateLimiteEmprestimo(EmprestimoRequest emprestimoRequest, Cliente cliente) {
    	double rendaTotal = cliente.getEmprestimos().stream()
    			.flatMapToDouble(e -> DoubleStream.of(e.getValorInicial().doubleValue()))
                .sum() + emprestimoRequest.getValorInicial();

        double limiteEmprestimo = cliente.getRendaMensal() * 10;
        if (rendaTotal > limiteEmprestimo) {
            throw new LimiteEmprestimoExcedidoException("Limite de empréstimo excedido");
        }
    }


    private Cliente findByCliente(String cpfCliente) {
        return clienteRepository.findByCpf(cpfCliente)
                .orElseThrow(() -> new ClienteNotFoundException(cpfCliente));
    }

    private Emprestimo findByIdAndCliente(Long id, String cpfCliente) {
        return emprestimoRepository.findByIdAndClienteCpf(id, cpfCliente)
                .orElseThrow(() -> new EmprestimoNotFoundException(id, cpfCliente));
    }



    private double calculateValorFinal(EmprestimoRequest emprestimoRequest, Cliente cliente) {
        double valorFinal;
        switch (emprestimoRequest.getTipoRelacionamento()) {
            case OURO:
                valorFinal = emprestimoRequest.getValorInicial() * 1.3;
                break;
            case PRATA:
                valorFinal = emprestimoRequest.getValorInicial() * 1.2;
                break;
            default:
                valorFinal = emprestimoRequest.getValorInicial() * 1.1;
                break;
        }
        return valorFinal;
    }
}
