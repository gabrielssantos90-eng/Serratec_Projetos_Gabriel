package br.com.serratec.projetogabriel.service;

import br.com.serratec.projetogabriel.enums.Parentesco;
import br.com.serratec.projetogabriel.exception.DependenteException;
import br.com.serratec.projetogabriel.model.Dependente;
import br.com.serratec.projetogabriel.model.Funcionario;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe responsável pelo processamento de arquivos CSV.
 */
public class ArquivoProcessadorService implements ArquivoProcessavel {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private Set<String> cpfsFuncionarios = new HashSet<>();
    private Set<String> cpfsDependentes = new HashSet<>();
    
    @Override
    public List<Funcionario> lerArquivo(String caminhoArquivo) throws Exception {
        List<Funcionario> funcionarios = new ArrayList<>();
        cpfsFuncionarios.clear();
        cpfsDependentes.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            Funcionario funcionarioAtual = null;
            
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                
                if (linha.isEmpty()) {
                    // Linha em branco separa grupos
                    continue;
                }
                
                String[] campos = linha.split(";");
                
                if (campos.length == 4) {
                    // Verificar se é funcionário (tem salário) ou dependente (tem parentesco)
                    try {
                        // Tenta converter o último campo como BigDecimal (salário)
                        new BigDecimal(campos[3].trim());
                        // Se conseguiu, é um funcionário
                        funcionarioAtual = processarFuncionario(campos);
                        funcionarios.add(funcionarioAtual);
                    } catch (NumberFormatException e) {
                        // Se não conseguiu, é um dependente
                        if (funcionarioAtual != null) {
                            Dependente dependente = processarDependente(campos);
                            funcionarioAtual.adicionarDependente(dependente);
                        }
                    }
                }
            }
        }
        
        return funcionarios;
    }
    
    private Funcionario processarFuncionario(String[] campos) throws Exception {
        String nome = campos[0].trim();
        String cpf = campos[1].trim();
        String dataStr = campos[2].trim();
        String salarioStr = campos[3].trim();
        
        // Validar CPF único
        if (cpfsFuncionarios.contains(cpf)) {
            throw new IllegalArgumentException("CPF de funcionário duplicado: " + cpf);
        }
        cpfsFuncionarios.add(cpf);
        
        LocalDate dataNascimento = LocalDate.parse(dataStr, DATE_FORMATTER);
        BigDecimal salarioBruto = new BigDecimal(salarioStr);
        
        return new Funcionario(nome, cpf, dataNascimento, salarioBruto);
    }
    
    private Dependente processarDependente(String[] campos) throws Exception {
        String nome = campos[0].trim();
        String cpf = campos[1].trim();
        String dataStr = campos[2].trim();
        String parentescoStr = campos[3].trim();
        
        // Validar CPF único
        if (cpfsDependentes.contains(cpf)) {
            throw new DependenteException("CPF de dependente duplicado: " + cpf);
        }
        cpfsDependentes.add(cpf);
        
        LocalDate dataNascimento = LocalDate.parse(dataStr, DATE_FORMATTER);
        Parentesco parentesco = Parentesco.valueOf(parentescoStr);
        
        return new Dependente(nome, cpf, dataNascimento, parentesco);
    }
    
    @Override
    public void escreverArquivo(List<Funcionario> funcionarios, String caminhoArquivo) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Funcionario funcionario : funcionarios) {
                writer.printf("%s;%s;%.2f;%.2f;%.2f%n",
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getDescontoInss(),
                    funcionario.getDescontoIR(),
                    funcionario.calcularSalarioLiquido()
                );
            }
        }
    }
}