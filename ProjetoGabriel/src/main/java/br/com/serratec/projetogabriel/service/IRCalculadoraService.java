package br.com.serratec.projetogabriel.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe responsável pelo cálculo do Imposto de Renda conforme tabela progressiva.
 */
public class IRCalculadoraService implements IRCalculavel {
    
    // Faixas e alíquotas para cálculo do IR
    private static final BigDecimal FAIXA_ISENCAO = new BigDecimal("2259.00");
    private static final BigDecimal FAIXA2_LIMITE = new BigDecimal("2826.65");
    private static final BigDecimal FAIXA3_LIMITE = new BigDecimal("3751.05");
    private static final BigDecimal FAIXA4_LIMITE = new BigDecimal("4664.68");
    
    private static final BigDecimal ALIQUOTA2 = new BigDecimal("0.075"); // 7.5%
    private static final BigDecimal ALIQUOTA3 = new BigDecimal("0.15");  // 15%
    private static final BigDecimal ALIQUOTA4 = new BigDecimal("0.225"); // 22.5%
    private static final BigDecimal ALIQUOTA5 = new BigDecimal("0.275"); // 27.5%
    
    private static final BigDecimal DEDUCAO2 = new BigDecimal("169.44");
    private static final BigDecimal DEDUCAO3 = new BigDecimal("381.44");
    private static final BigDecimal DEDUCAO4 = new BigDecimal("662.77");
    private static final BigDecimal DEDUCAO5 = new BigDecimal("896.00");
    
    @Override
    public BigDecimal calcularIR(BigDecimal salarioBruto, BigDecimal descontoINSS, BigDecimal valorDependentes) {
        if (salarioBruto == null || salarioBruto.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        
        // Base de cálculo = Salário Bruto - INSS - Valor por Dependentes
        BigDecimal baseCalculo = salarioBruto.subtract(descontoINSS != null ? descontoINSS : BigDecimal.ZERO)
                                           .subtract(valorDependentes != null ? valorDependentes : BigDecimal.ZERO);
        
        if (baseCalculo.compareTo(FAIXA_ISENCAO) <= 0) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal ir;
        BigDecimal deducao;
        
        if (baseCalculo.compareTo(FAIXA2_LIMITE) <= 0) {
            // Faixa 2: de R$ 2.259,21 até R$ 2.826,65 - 7.5%
            ir = baseCalculo.multiply(ALIQUOTA2);
            deducao = DEDUCAO2;
        } else if (baseCalculo.compareTo(FAIXA3_LIMITE) <= 0) {
            // Faixa 3: de R$ 2.826,66 até R$ 3.751,05 - 15%
            ir = baseCalculo.multiply(ALIQUOTA3);
            deducao = DEDUCAO3;
        } else if (baseCalculo.compareTo(FAIXA4_LIMITE) <= 0) {
            // Faixa 4: de R$ 3.751,06 até R$ 4.664,68 - 22.5%
            ir = baseCalculo.multiply(ALIQUOTA4);
            deducao = DEDUCAO4;
        } else {
            // Faixa 5: acima de R$ 4.664,68 - 27.5%
            ir = baseCalculo.multiply(ALIQUOTA5);
            deducao = DEDUCAO5;
        }
        
        BigDecimal irFinal = ir.subtract(deducao);
        return irFinal.compareTo(BigDecimal.ZERO) > 0 ? irFinal.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }
}