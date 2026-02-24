package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.Paciente;
import domain.repository.PacienteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PacienteRepositoryImpl implements PacienteRepository {

    @Override
    public void save(Paciente paciente) {
        String sql = "INSERT INTO Paciente (cpf, nome, telefone) VALUES (?, ?, ?)";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getTelefone());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setId(rs.getInt(1)); // único uso do setter
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente", e);
        }
    }

    @Override
    public Optional<Paciente> findById(int id) {
        String sql = "SELECT * FROM Paciente WHERE id = ?";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    return Optional.of(new Paciente(id, cpf, nome, telefone));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Paciente> findAll() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                lista.add(new Paciente(id, cpf, nome, telefone));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os pacientes", e);
        }
        return lista.iterator();
    }

    @Override
    public Optional<Paciente> findByCPF(String cpf) {
        String sql = "SELECT * FROM Paciente WHERE cpf = ?";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    return Optional.of(new Paciente(id, cpf, nome, telefone));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por CPF", e);
        }
        return Optional.empty();
    }
}