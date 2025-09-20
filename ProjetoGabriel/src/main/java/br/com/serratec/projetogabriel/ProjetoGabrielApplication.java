package br.com.serratec.projetogabriel;

import br.com.serratec.projetogabriel.model.FolhaPagamento;
import br.com.serratec.projetogabriel.model.Funcionario;
import br.com.serratec.projetogabriel.service.ArquivoProcessadorService;
import br.com.serratec.projetogabriel.service.FolhaPagamentoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal da aplicação de cálculo de folha de pagamento.
 */
public class ProjetoGabrielApplication {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("=== SISTEMA DE CÁLCULO DE FOLHA DE PAGAMENTO ===");
            System.out.println();
            
            // Solicitar arquivo de entrada
            System.out.print("Digite o nome do arquivo de entrada (ex: funcionarios.csv): ");
            String arquivoEntrada = scanner.nextLine().trim();
            
            // Solicitar arquivo de saída
            System.out.print("Digite o nome do arquivo de saída (ex: folha_pagamento.csv): ");
            String arquivoSaida = scanner.nextLine().trim();
            
            // Processar arquivo de entrada
            System.out.println();
            System.out.println("Processando arquivo de entrada...");
            
            ArquivoProcessadorService arquivoService = new ArquivoProcessadorService();
            List<Funcionario> funcionarios = arquivoService.lerArquivo(arquivoEntrada);
            
            System.out.println("Funcionários carregados: " + funcionarios.size());
            
            // Processar folha de pagamento
            System.out.println("Calculando folha de pagamento...");
            
            FolhaPagamentoService folhaService = new FolhaPagamentoService();
            List<FolhaPagamento> folhasPagamento = folhaService.processarFolhaPagamento(
                funcionarios, 
                LocalDate.now()
            );
            
            // Exibir resumo dos cálculos
            System.out.println();
            System.out.println("=== RESUMO DOS CÁLCULOS ===");
            for (Funcionario func : funcionarios) {
                System.out.printf("Funcionário: %s (CPF: %s)%n", func.getNome(), func.getCpf());
                System.out.printf("  Salário Bruto: R$ %.2f%n", func.getSalarioBruto());
                System.out.printf("  Dependentes: %d%n", func.getQuantidadeDependentes());
                System.out.printf("  Desconto INSS: R$ %.2f%n", func.getDescontoInss());
                System.out.printf("  Desconto IR: R$ %.2f%n", func.getDescontoIR());
                System.out.printf("  Salário Líquido: R$ %.2f%n", func.calcularSalarioLiquido());
                System.out.println();
            }
            
            // Gerar arquivo de saída
            System.out.println("Gerando arquivo de saída...");
            arquivoService.escreverArquivo(funcionarios, arquivoSaida);
            
            System.out.println("Processo concluído com sucesso!");
            System.out.println("Arquivo de saída gerado: " + arquivoSaida);
            
        } catch (Exception e) {
            System.err.println("Erro durante o processamento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}