package br.com.serratec.projetogabriel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe que representa um registro de folha de pagamento.
 */
public class FolhaPagamento {
    
    private Long codigo;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private BigDecimal descontoINSS;
    private BigDecimal descontoIR;
    private BigDecimal salarioLiquido;
    
    public FolhaPagamento() {}
    
    public FolhaPagamento(Funcionario funcionario, LocalDate dataPagamento, 
                         BigDecimal descontoINSS, BigDecimal descontoIR, BigDecimal salarioLiquido) {
        this.funcionario = funcionario;
        this.dataPagamento = dataPagamento;
        this.descontoINSS = descontoINSS;
        this.descontoIR = descontoIR;
        this.salarioLiquido = salarioLiquido;
    }
    
    // Getters and Setters
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    public BigDecimal getDescontoINSS() {
        return descontoINSS;
    }
    
    public void setDescontoINSS(BigDecimal descontoINSS) {
        this.descontoINSS = descontoINSS;
    }
    
    public BigDecimal getDescontoIR() {
        return descontoIR;
    }
    
    public void setDescontoIR(BigDecimal descontoIR) {
        this.descontoIR = descontoIR;
    }
    
    public BigDecimal getSalarioLiquido() {
        return salarioLiquido;
    }
    
    public void setSalarioLiquido(BigDecimal salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
    
    @Override
    public String toString() {
        return "FolhaPagamento{" +
                "codigo=" + codigo +
                ", funcionario=" + funcionario.getNome() +
                ", dataPagamento=" + dataPagamento +
                ", descontoINSS=" + descontoINSS +
                ", descontoIR=" + descontoIR +
                ", salarioLiquido=" + salarioLiquido +
                '}';
    }
}