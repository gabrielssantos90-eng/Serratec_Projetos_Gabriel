package br.com.serratec.projetogabriel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados.
 */
public class DatabaseConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/folha_pagamento";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão com banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}