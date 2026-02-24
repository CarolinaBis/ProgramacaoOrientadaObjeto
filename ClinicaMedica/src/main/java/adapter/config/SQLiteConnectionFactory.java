package adapter.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionFactory {
    private static final String URL = "jdbc:sqlite:clinica.db";

    // Bloco estático para carregar o driver
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver SQLite carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver SQLite não encontrado. Verifique as dependências do Maven.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com SQLite estabelecida com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com SQLite: " + e.getMessage());
            throw e;
        }
    }
}