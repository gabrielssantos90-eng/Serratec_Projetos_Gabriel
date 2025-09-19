
package Calculo;

import java.io.*;
import java.util.*;

public class CalculadoraFolha {
    static class Funcionario {
        String nome;
        String cpf;
        double salarioBruto;
        int dependentes = 0;

        double calcularINSS() {
            if (salarioBruto <= 1412.00) return salarioBruto * 0.075;
            else if (salarioBruto <= 2666.68) return salarioBruto * 0.09;
            else if (salarioBruto <= 4000.00) return salarioBruto * 0.12;
            else return salarioBruto * 0.14;
        }

        double calcularIR() {
            double base = salarioBruto - calcularINSS() - (dependentes * 189.59);
            if (base <= 2112.00) return 0;
            else if (base <= 2826.65) return base * 0.075 - 158.40;
            else if (base <= 3751.05) return base * 0.15 - 370.40;
            else if (base <= 4664.68) return base * 0.225 - 651.73;
            else return base * 0.275 - 884.96;
        }

        double salarioLiquido() {
            return salarioBruto - calcularINSS() - calcularIR();
        }
    }

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario funcionarioAtual = null;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Pcgamer\\Desktop\\Serratec\\POO\\teste.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; // pula linhas em branco
                String[] partes = linha.split(";");
                if (partes.length == 4 && partes[3].matches("\\d+\\.\\d{2}")) {
                    // Linha de funcionário
                    funcionarioAtual = new Funcionario();
                    funcionarioAtual.nome = partes[0];
                    funcionarioAtual.cpf = partes[1];
                    funcionarioAtual.salarioBruto = Double.parseDouble(partes[3]);
                    funcionarios.add(funcionarioAtual);
                } else if (partes.length == 4 && funcionarioAtual != null) {
                    // Linha de dependente
                    funcionarioAtual.dependentes++;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Pcgamer\\Desktop\\Serratec\\POO\\saida.txt"))) {
                bw.write("Nome_do_funcionario;CPF_do_Funcionario;Desconto_INSS;Desconto_IR;SalarioLiquido\n");
                for (Funcionario f : funcionarios) {
                    bw.write(String.format("%s;%s;%.2f;%.2f;%.2f\n",
                            f.nome, f.cpf, f.calcularINSS(), f.calcularIR(), f.salarioLiquido()));
                }
            }

            System.out.println("Arquivo de saída gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
