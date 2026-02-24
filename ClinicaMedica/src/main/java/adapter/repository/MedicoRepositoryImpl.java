package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.Especialidade;
import domain.entity.Medico;
import domain.repository.MedicoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MedicoRepositoryImpl implements MedicoRepository {

    @Override
    public void save(Medico medico) {
        String sql = "INSERT INTO Medico (cpf, nome, inscricaoCRM, especialidade_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medico.getCpf());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getInscricaoCRM());
            stmt.setInt(4, medico.getEspecialidade().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    medico.setId(rs.getInt(1)); // único uso do setter
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar médico", e);
        }
    }

    @Override
    public Optional<Medico> findById(int id) {
        String sql = """
            SELECT m.id, m.cpf, m.nome, m.inscricaoCRM,
                   e.id as esp_id, e.descricao
            FROM Medico m
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE m.id = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int medicoId = rs.getInt("id");
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String crm = rs.getString("inscricaoCRM");

                    int espId = rs.getInt("esp_id");
                    String espDescricao = rs.getString("descricao");

                    Especialidade especialidade = new Especialidade(espId, espDescricao);
                    Medico medico = new Medico(medicoId, cpf, nome, crm, especialidade);
                    return Optional.of(medico);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Medico> findAll() {
        List<Medico> lista = new ArrayList<>();
        String sql = """
            SELECT m.id, m.cpf, m.nome, m.inscricaoCRM,
                   e.id as esp_id, e.descricao
            FROM Medico m
            JOIN Especialidade e ON m.especialidade_id = e.id
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int medicoId = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String crm = rs.getString("inscricaoCRM");

                int espId = rs.getInt("esp_id");
                String espDescricao = rs.getString("descricao");

                Especialidade especialidade = new Especialidade(espId, espDescricao);
                Medico medico = new Medico(medicoId, cpf, nome, crm, especialidade);
                lista.add(medico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os médicos", e);
        }

        return lista.iterator();
    }

    @Override
    public Optional<Medico> findByCPF(String cpf) {
        String sql = """
            SELECT m.id, m.cpf, m.nome, m.inscricaoCRM,
                   e.id as esp_id, e.descricao
            FROM Medico m
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE m.cpf = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int medicoId = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String crm = rs.getString("inscricaoCRM");

                    int espId = rs.getInt("esp_id");
                    String espDescricao = rs.getString("descricao");

                    Especialidade especialidade = new Especialidade(espId, espDescricao);
                    Medico medico = new Medico(medicoId, cpf, nome, crm, especialidade);
                    return Optional.of(medico);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico por CPF", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Medico> findByCRM(String crm) {
        String sql = """
            SELECT m.id, m.cpf, m.nome, m.inscricaoCRM,
                   e.id as esp_id, e.descricao
            FROM Medico m
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE m.inscricaoCRM = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int medicoId = rs.getInt("id");
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");

                    int espId = rs.getInt("esp_id");
                    String espDescricao = rs.getString("descricao");

                    Especialidade especialidade = new Especialidade(espId, espDescricao);
                    Medico medico = new Medico(medicoId, cpf, nome, crm, especialidade);
                    return Optional.of(medico);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico por CRM", e);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Medico> findByEspecialidade(Especialidade especialidade) {
        List<Medico> lista = new ArrayList<>();
        String sql = """
            SELECT m.id, m.cpf, m.nome, m.inscricaoCRM
            FROM Medico m
            WHERE m.especialidade_id = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especialidade.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int medicoId = rs.getInt("id");
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String crm = rs.getString("inscricaoCRM");

                    Medico medico = new Medico(medicoId, cpf, nome, crm, especialidade);
                    lista.add(medico);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médicos por especialidade", e);
        }

        return lista.iterator();
    }
}