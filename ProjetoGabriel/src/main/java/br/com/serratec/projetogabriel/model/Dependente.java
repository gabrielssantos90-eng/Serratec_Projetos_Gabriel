package br.com.serratec.projetogabriel.model;

import br.com.serratec.projetogabriel.enums.Parentesco;
import br.com.serratec.projetogabriel.exception.DependenteException;
import java.time.LocalDate;
import java.time.Period;

/**
 * Classe que representa um dependente de funcionário.
 * Herda de Pessoa e adiciona funcionalidades específicas de dependente.
 */
public class Dependente extends Pessoa {
    
    private Parentesco parentesco;
    
    public Dependente() {
        super();
    }
    
    public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) 
            throws DependenteException {
        super(nome, cpf, dataNascimento);
        validarIdade(dataNascimento);
        this.parentesco = parentesco;
    }
    
    /**
     * Valida se o dependente é menor de 18 anos.
     */
    private void validarIdade(LocalDate dataNascimento) throws DependenteException {
        if (dataNascimento == null) {
            throw new DependenteException("Data de nascimento não pode ser nula");
        }
        
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade >= 18) {
            throw new DependenteException("Dependente deve ser menor que 18 anos. Idade atual: " + idade + " anos");
        }
    }
    
    /**
     * Retorna o valor de dedução no IR para este dependente.
     */
    public double getValorDeducaoIR() {
        return 189.59;
    }
    
    public Parentesco getParentesco() {
        return parentesco;
    }
    
    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
    
    @Override
    public String toString() {
        return "Dependente{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", dataNascimento=" + getDataNascimento() +
                ", parentesco=" + parentesco +
                '}';
    }
}