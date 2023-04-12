package com.financiamento.API.types;

import java.math.BigDecimal;

public enum TipoRelacionamento {
    BRONZE {
        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial, int qtdEmprestimos) {
            return valorInicial.multiply(new BigDecimal("1.80"));
        }

        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial) {
            return calcularValorFinal(valorInicial, 0);
        }
    },
    PRATA {
        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial, int qtdEmprestimos) {
            if (valorInicial.compareTo(new BigDecimal("5000")) > 0) {
                return valorInicial.multiply(new BigDecimal("1.40"));
            } else {
                return valorInicial.multiply(new BigDecimal("1.60"));
            }
        }

        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial) {
            return calcularValorFinal(valorInicial, 0);
        }
    },
    OURO {
        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial, int qtdEmprestimos) {
            if (qtdEmprestimos <= 1) {
                return valorInicial.multiply(new BigDecimal("1.2"));
            } else {
                return valorInicial.multiply(new BigDecimal("1.3"));
            }
        }

        @Override
        public BigDecimal calcularValorFinal(BigDecimal valorInicial) {
            throw new UnsupportedOperationException("Método não suportado para OURO");
        }
    };

    public abstract BigDecimal calcularValorFinal(BigDecimal valorInicial, int qtdEmprestimos);

    public abstract BigDecimal calcularValorFinal(BigDecimal valorInicial);
}

