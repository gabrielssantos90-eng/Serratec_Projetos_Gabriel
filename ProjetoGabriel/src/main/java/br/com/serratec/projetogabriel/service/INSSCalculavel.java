package br.com.serratec.projetogabriel.service;

import java.math.BigDecimal;

/**
 * Interface para cálculo de INSS.
 */
public interface INSSCalculavel {
    
    /**
     * Calcula o desconto do INSS baseado no salário bruto.
     * @param salarioBruto O salário bruto do funcionário
     * @return O valor do desconto do INSS
     */
    BigDecimal calcularINSS(BigDecimal salarioBruto);
}