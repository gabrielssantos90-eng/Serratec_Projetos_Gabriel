package br.com.serratec.projetogabriel.service;

import br.com.serratec.projetogabriel.model.FolhaPagamento;
import br.com.serratec.projetogabriel.model.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo processamento da folha de pagamento.
 */
public class FolhaPagamentoService {
    
    private final INSSCalculavel inssCalculadora;
    private final IRCalculavel irCalculadora;
    
    public FolhaPagamentoService() {
        this.inssCalculadora = new INSSCalculadoraService();
        this.irCalculadora = new IRCalculadoraService();
    }
    
    /**
     * Processa a folha de pagamento para uma lista de funcionários.
     */
    public List<FolhaPagamento> processarFolhaPagamento(List<Funcionario> funcionarios, LocalDate dataPagamento) {
        List<FolhaPagamento> folhasPagamento = new ArrayList<>();
        
        for (Funcionario funcionario : funcionarios) {
            // Calcular INSS
            BigDecimal descontoINSS = inssCalculadora.calcularINSS(funcionario.getSalarioBruto());
            funcionario.setDescontoInss(descontoINSS);
            
            // Calcular valor de deduções por dependentes
            BigDecimal valorDependentes = funcionario.getValorTotalDeducoesDependentes();
            
            // Calcular IR
            BigDecimal descontoIR = irCalculadora.calcularIR(
                funcionario.getSalarioBruto(), 
                descontoINSS, 
                valorDependentes
            );
            funcionario.setDescontoIR(descontoIR);
            
            // Calcular salário líquido
            BigDecimal salarioLiquido = funcionario.calcularSalarioLiquido();
            
            // Criar registro de folha de pagamento
            FolhaPagamento folha = new FolhaPagamento(
                funcionario, 
                dataPagamento, 
                descontoINSS, 
                descontoIR, 
                salarioLiquido
            );
            
            folhasPagamento.add(folha);
        }
        
        return folhasPagamento;
    }
}