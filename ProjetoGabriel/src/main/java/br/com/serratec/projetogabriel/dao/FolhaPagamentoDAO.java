package br.com.serratec.projetogabriel.dao;

import br.com.serratec.projetogabriel.model.FolhaPagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações com folha de pagamento no banco de dados.
 */
public class FolhaPagamentoDAO {
    
    public void inserir(FolhaPagamento folhaPagamento) throws SQLException {
        String sql = "INSERT INTO folha_pagamento (cpf_funcionario, data_pagamento, desconto_inss, desconto_ir, salario_liquido) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, folhaPagamento.getFuncionario().getCpf());
            stmt.setDate(2, Date.valueOf(folhaPagamento.getDataPagamento()));
            stmt.setBigDecimal(3, folhaPagamento.getDescontoINSS());
            stmt.setBigDecimal(4, folhaPagamento.getDescontoIR());
            stmt.setBigDecimal(5, folhaPagamento.getSalarioLiquido());
            
            stmt.executeUpdate();
            
            // Obter o código gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                folhaPagamento.setCodigo(rs.getLong(1));
            }
        }
    }
    
    public List<FolhaPagamento> listarTodos() throws SQLException {
        List<FolhaPagamento> folhasPagamento = new ArrayList<>();
        String sql = "SELECT fp.*, f.nome, f.cpf, f.data_nascimento, f.salario_bruto " +
                    "FROM folha_pagamento fp " +
                    "JOIN funcionario f ON fp.cpf_funcionario = f.cpf " +
                    "ORDER BY fp.data_pagamento DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                FolhaPagamento folha = new FolhaPagamento();
                folha.setCodigo(rs.getLong("codigo"));
                folha.setDataPagamento(rs.getDate("data_pagamento").toLocalDate());
                folha.setDescontoINSS(rs.getBigDecimal("desconto_inss"));
                folha.setDescontoIR(rs.getBigDecimal("desconto_ir"));
                folha.setSalarioLiquido(rs.getBigDecimal("salario_liquido"));
                
                // Criar funcionário simplificado
                br.com.serratec.projetogabriel.model.Funcionario funcionario = 
                    new br.com.serratec.projetogabriel.model.Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                funcionario.setSalarioBruto(rs.getBigDecimal("salario_bruto"));
                
                folha.setFuncionario(funcionario);
                folhasPagamento.add(folha);
            }
        }
        
        return folhasPagamento;
    }
}