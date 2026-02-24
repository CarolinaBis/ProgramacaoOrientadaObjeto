package adapter.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS doador (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       nome TEXT NOT NULL,
                       cpf TEXT NOT NULL UNIQUE,
                       data_nascimento TEXT NOT NULL,
                       sexo TEXT CHECK (sexo IN ('M', 'F')) NOT NULL,
                       tipo_sanguineo TEXT NOT NULL CHECK (tipo_sanguineo IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
                       peso REAL NOT NULL,
                       data_ultima_doacao TEXT
                       );
            """);

            stmt.execute("""
               CREATE TABLE IF NOT EXISTS doacao (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      doador_id INTEGER NOT NULL,
                      data_doacao TEXT NOT NULL,               
                      qtd_doadada_ml REAL NOT NULL CHECK (qtd_doadada_ml > 0),
                      FOREIGN KEY (doador_id) REFERENCES doador(id)
                      );
            """);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

