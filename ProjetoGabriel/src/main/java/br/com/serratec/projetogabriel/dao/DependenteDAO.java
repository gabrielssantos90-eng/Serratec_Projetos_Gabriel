package br.com.serratec.projetogabriel.dao;

import br.com.serratec.projetogabriel.enums.Parentesco;
import br.com.serratec.projetogabriel.model.Dependente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações com dependentes no banco de dados.
 */
public class DependenteDAO {
    
    public void inserir(Dependente dependente, String cpfFuncionario) throws SQLException {
        String sql = "INSERT INTO dependente (nome, cpf, data_nascimento, parentesco, cpf_funcionario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Verificar se CPF já existe
            if (existeCpf(dependente.getCpf())) {
                throw new SQLException("CPF de dependente já cadastrado: " + dependente.getCpf());
            }
            
            stmt.setString(1, dependente.getNome());
            stmt.setString(2, dependente.getCpf());
            stmt.setDate(3, Date.valueOf(dependente.getDataNascimento()));
            stmt.setString(4, dependente.getParentesco().name());
            stmt.setString(5, cpfFuncionario);
            
            stmt.executeUpdate();
        }
    }
    
    public boolean existeCpf(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM dependente WHERE cpf = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        
        return false;
    }
    
    public List<Dependente> listarPorFuncionario(String cpfFuncionario) throws SQLException {
        List<Dependente> dependentes = new ArrayList<>();
        String sql = "SELECT * FROM dependente WHERE cpf_funcionario = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpfFuncionario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                try {
                    Dependente dependente = new Dependente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        Parentesco.valueOf(rs.getString("parentesco"))
                    );
                    dependentes.add(dependente);
                } catch (Exception e) {
                    System.err.println("Erro ao carregar dependente: " + e.getMessage());
                }
            }
        }
        
        return dependentes;
    }
}