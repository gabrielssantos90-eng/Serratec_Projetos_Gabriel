package br.com.serratec.projetogabriel.dao;

import br.com.serratec.projetogabriel.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações com funcionários no banco de dados.
 */
public class FuncionarioDAO {
    
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cpf, data_nascimento, salario_bruto, desconto_inss, desconto_ir) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Verificar se CPF já existe
            if (existeCpf(funcionario.getCpf())) {
                throw new SQLException("CPF já cadastrado: " + funcionario.getCpf());
            }
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setBigDecimal(4, funcionario.getSalarioBruto());
            stmt.setBigDecimal(5, funcionario.getDescontoInss());
            stmt.setBigDecimal(6, funcionario.getDescontoIR());
            
            stmt.executeUpdate();
        }
    }
    
    public boolean existeCpf(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM funcionario WHERE cpf = ?";
        
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
    
    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                funcionario.setSalarioBruto(rs.getBigDecimal("salario_bruto"));
                funcionario.setDescontoInss(rs.getBigDecimal("desconto_inss"));
                funcionario.setDescontoIR(rs.getBigDecimal("desconto_ir"));
                
                funcionarios.add(funcionario);
            }
        }
        
        return funcionarios;
    }
}