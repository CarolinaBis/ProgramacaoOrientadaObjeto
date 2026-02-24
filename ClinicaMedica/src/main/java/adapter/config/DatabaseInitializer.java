package adapter.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        System.out.println("=== INICIALIZANDO BANCO DE DADOS ===");

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("✓ Conexão estabelecida com sucesso!");

            // Criar tabela Especialidade
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Especialidade (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT NOT NULL UNIQUE
                );
            """);
            System.out.println("✓ Tabela Especialidade criada/verificada");

            // Criar tabela Paciente
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Paciente (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cpf TEXT NOT NULL UNIQUE,
                    nome TEXT NOT NULL,
                    telefone TEXT
                );
            """);
            System.out.println("✓ Tabela Paciente criada/verificada");

            // Criar tabela Medico
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Medico (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cpf TEXT NOT NULL UNIQUE,
                    nome TEXT NOT NULL,
                    inscricaoCRM TEXT NOT NULL,
                    especialidade_id INTEGER,
                    FOREIGN KEY (especialidade_id) REFERENCES Especialidade(id)
                );
            """);
            System.out.println("✓ Tabela Medico criada/verificada");

            // Criar tabela Consulta
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Consulta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    dataHora TEXT NOT NULL,
                    status TEXT NOT NULL,
                    paciente_id INTEGER,
                    medico_id INTEGER,
                    FOREIGN KEY (paciente_id) REFERENCES Paciente(id),
                    FOREIGN KEY (medico_id) REFERENCES Medico(id)
                );
            """);
            System.out.println("✓ Tabela Consulta criada/verificada");

            System.out.println("=== BANCO DE DADOS INICIALIZADO COM SUCESSO ===");

        } catch (SQLException e) {
            System.err.println("✗ Erro SQL: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar banco de dados: " + e.getMessage(), e);
        }
    }
}