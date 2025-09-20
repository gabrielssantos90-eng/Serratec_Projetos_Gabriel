package br.com.serratec.projetogabriel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um funcionário da empresa.
 * Herda de Pessoa e adiciona funcionalidades específicas de funcionário.
 */
public class Funcionario extends Pessoa {
    
    private BigDecimal salarioBruto;
    private BigDecimal descontoInss;
    private BigDecimal descontoIR;
    private List<Dependente> dependentes;
    
    public Funcionario() {
        super();
        this.dependentes = new ArrayList<>();
    }
    
    public Funcionario(String nome, String cpf, LocalDate dataNascimento, BigDecimal salarioBruto) {
        super(nome, cpf, dataNascimento);
        this.salarioBruto = salarioBruto;
        this.dependentes = new ArrayList<>();
        this.descontoInss = BigDecimal.ZERO;
        this.descontoIR = BigDecimal.ZERO;
    }
    
    /**
     * Adiciona um dependente à lista de dependentes do funcionário.
     */
    public void adicionarDependente(Dependente dependente) {
        if (dependente != null) {
            this.dependentes.add(dependente);
        }
    }
    
    /**
     * Calcula o salário líquido baseado no salário bruto e descontos.
     */
    public BigDecimal calcularSalarioLiquido() {
        return salarioBruto.subtract(descontoInss).subtract(descontoIR);
    }
    
    /**
     * Retorna o número de dependentes.
     */
    public int getQuantidadeDependentes() {
        return dependentes.size();
    }
    
    /**
     * Calcula o valor total de dedução dos dependentes no IR.
     */
    public BigDecimal getValorTotalDeducoesDependentes() {
        return BigDecimal.valueOf(dependentes.size() * 189.59);
    }
    
    // Getters and Setters
    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }
    
    public void setSalarioBruto(BigDecimal salarioBruto) {
        this.salarioBruto = salarioBruto;
    }
    
    public BigDecimal getDescontoInss() {
        return descontoInss;
    }
    
    public void setDescontoInss(BigDecimal descontoInss) {
        this.descontoInss = descontoInss;
    }
    
    public BigDecimal getDescontoIR() {
        return descontoIR;
    }
    
    public void setDescontoIR(BigDecimal descontoIR) {
        this.descontoIR = descontoIR;
    }
    
    public List<Dependente> getDependentes() {
        return new ArrayList<>(dependentes);
    }
    
    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes != null ? new ArrayList<>(dependentes) : new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", dataNascimento=" + getDataNascimento() +
                ", salarioBruto=" + salarioBruto +
                ", descontoInss=" + descontoInss +
                ", descontoIR=" + descontoIR +
                ", quantidadeDependentes=" + dependentes.size() +
                '}';
    }
}