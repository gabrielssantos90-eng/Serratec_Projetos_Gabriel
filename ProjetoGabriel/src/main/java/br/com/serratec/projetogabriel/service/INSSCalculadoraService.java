package br.com.serratec.projetogabriel.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe responsável pelo cálculo do INSS conforme tabela progressiva.
 */
public class INSSCalculadoraService implements INSSCalculavel {
    
    // Faixas salariais e alíquotas para cálculo do INSS
    private static final BigDecimal FAIXA1_LIMITE = new BigDecimal("1412.00");
    private static final BigDecimal FAIXA2_LIMITE = new BigDecimal("2666.68");
    private static final BigDecimal FAIXA3_LIMITE = new BigDecimal("4000.03");
    private static final BigDecimal FAIXA4_LIMITE = new BigDecimal("7786.02");
    private static final BigDecimal TETO_INSS = new BigDecimal("8157.41");
    
    private static final BigDecimal ALIQUOTA1 = new BigDecimal("0.075"); // 7.5%
    private static final BigDecimal ALIQUOTA2 = new BigDecimal("0.09");  // 9%
    private static final BigDecimal ALIQUOTA3 = new BigDecimal("0.12");  // 12%
    private static final BigDecimal ALIQUOTA4 = new BigDecimal("0.14");  // 14%
    
    @Override
    public BigDecimal calcularINSS(BigDecimal salarioBruto) {
        if (salarioBruto == null || salarioBruto.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal salarioCalculo = salarioBruto.min(TETO_INSS);
        BigDecimal inss = BigDecimal.ZERO;
        BigDecimal salarioRestante = salarioCalculo;
        
        // Faixa 1: até R$ 1.412,00 - 7.5%
        if (salarioRestante.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorFaixa = salarioRestante.min(FAIXA1_LIMITE);
            inss = inss.add(valorFaixa.multiply(ALIQUOTA1));
            salarioRestante = salarioRestante.subtract(valorFaixa);
        }
        
        // Faixa 2: de R$ 1.412,01 até R$ 2.666,68 - 9%
        if (salarioRestante.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorFaixa = salarioRestante.min(FAIXA2_LIMITE.subtract(FAIXA1_LIMITE));
            inss = inss.add(valorFaixa.multiply(ALIQUOTA2));
            salarioRestante = salarioRestante.subtract(valorFaixa);
        }
        
        // Faixa 3: de R$ 2.666,69 até R$ 4.000,03 - 12%
        if (salarioRestante.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorFaixa = salarioRestante.min(FAIXA3_LIMITE.subtract(FAIXA2_LIMITE));
            inss = inss.add(valorFaixa.multiply(ALIQUOTA3));
            salarioRestante = salarioRestante.subtract(valorFaixa);
        }
        
        // Faixa 4: de R$ 4.000,04 até R$ 7.786,02 - 14%
        if (salarioRestante.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorFaixa = salarioRestante.min(FAIXA4_LIMITE.subtract(FAIXA3_LIMITE));
            inss = inss.add(valorFaixa.multiply(ALIQUOTA4));
        }
        
        return inss.setScale(2, RoundingMode.HALF_UP);
    }
}