package br.com.serratec.projetogabriel.service;

import java.math.BigDecimal;

/**
 * Interface para cálculo de Imposto de Renda.
 */
public interface IRCalculavel {
    
    /**
     * Calcula o desconto do IR baseado no salário e deduções.
     * @param salarioBruto O salário bruto do funcionário
     * @param descontoINSS O valor já descontado do INSS
     * @param valorDependentes O valor de dedução por dependentes
     * @return O valor do desconto do IR
     */
    BigDecimal calcularIR(BigDecimal salarioBruto, BigDecimal descontoINSS, BigDecimal valorDependentes);
}