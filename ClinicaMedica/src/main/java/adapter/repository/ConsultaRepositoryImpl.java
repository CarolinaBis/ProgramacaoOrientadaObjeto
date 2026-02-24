package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.*;
import domain.repository.ConsultaRepository;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ConsultaRepositoryImpl implements ConsultaRepository {

    @Override
    public void save(Consulta consulta) {
        String sql = """
            INSERT INTO Consulta (dataHora, status, paciente_id, medico_id)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, consulta.getDataHora().toString());
            stmt.setString(2, consulta.getStatus().toString());
            stmt.setInt(3, consulta.getPaciente().getId());
            stmt.setInt(4, consulta.getMedico().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro ao inserir consulta, nenhuma linha afetada.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                consulta.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Falha ao obter ID gerado para a consulta.");
            }
            generatedKeys.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta: "+ e.getMessage());
        }
    }

    @Override
    public Optional<Consulta> findById(int id) {
        String sql = """
            SELECT 
                c.id AS consulta_id, c.dataHora, c.status,
                p.id AS paciente_id, p.cpf AS paciente_cpf, p.nome AS paciente_nome, p.telefone AS paciente_telefone,
                m.id AS medico_id, m.cpf AS medico_cpf, m.nome AS medico_nome, m.inscricaoCRM,
                e.id AS especialidade_id, e.descricao AS especialidade_descricao
            FROM Consulta c
            JOIN Paciente p ON c.paciente_id = p.id
            JOIN Medico m ON c.medico_id = m.id
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE c.id = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                var paciente = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("paciente_cpf"),
                        rs.getString("paciente_nome"),
                        rs.getString("paciente_telefone")
                );

                var especialidade = new Especialidade(
                        rs.getInt("especialidade_id"),
                        rs.getString("especialidade_descricao")
                );

                var medico = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("medico_cpf"),
                        rs.getString("medico_nome"),
                        rs.getString("inscricaoCRM"),
                        especialidade
                );

                Consulta consulta = new Consulta(
                        rs.getInt("consulta_id"),
                        LocalDateTime.parse(rs.getString("dataHora")),
                        StatusConsulta.valueOf(rs.getString("status")),
                        paciente,
                        medico
                );
                rs.close();
                return Optional.of(consulta);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar consulta por ID: "+ e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Iterator<Consulta> findByPaciente(Paciente paciente) {
        String sql = """
            SELECT 
                c.id AS consulta_id, c.dataHora, c.status,
                p.id AS paciente_id, p.cpf AS paciente_cpf, p.nome AS paciente_nome, p.telefone AS paciente_telefone,
                m.id AS medico_id, m.cpf AS medico_cpf, m.nome AS medico_nome, m.inscricaoCRM,
                e.id AS especialidade_id, e.descricao AS especialidade_descricao
            FROM Consulta c
            JOIN Paciente p ON c.paciente_id = p.id
            JOIN Medico m ON c.medico_id = m.id
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE p.id = ?
        """;

        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, paciente.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var pac = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("paciente_cpf"),
                        rs.getString("paciente_nome"),
                        rs.getString("paciente_telefone")
                );

                var especialidade = new Especialidade(
                        rs.getInt("especialidade_id"),
                        rs.getString("especialidade_descricao")
                );

                var medico = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("medico_cpf"),
                        rs.getString("medico_nome"),
                        rs.getString("inscricaoCRM"),
                        especialidade
                );

                Consulta consulta = new Consulta(
                        rs.getInt("consulta_id"),
                        LocalDateTime.parse(rs.getString("dataHora")),
                        StatusConsulta.valueOf(rs.getString("status")),
                        pac,
                        medico
                );

                consultas.add(consulta);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar consulta por paciente: "+ e.getMessage());
        }

        return consultas.iterator();
    }

    @Override
    public Iterator<Consulta> findByMedico(Medico medico) {
        String sql = """
            SELECT 
                c.id AS consulta_id, c.dataHora, c.status,
                p.id AS paciente_id, p.cpf AS paciente_cpf, p.nome AS paciente_nome, p.telefone AS paciente_telefone,
                m.id AS medico_id, m.cpf AS medico_cpf, m.nome AS medico_nome, m.inscricaoCRM,
                e.id AS especialidade_id, e.descricao AS especialidade_descricao
            FROM Consulta c
            JOIN Paciente p ON c.paciente_id = p.id
            JOIN Medico m ON c.medico_id = m.id
            JOIN Especialidade e ON m.especialidade_id = e.id
            WHERE m.id = ?
        """;

        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, medico.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var pac = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("paciente_cpf"),
                        rs.getString("paciente_nome"),
                        rs.getString("paciente_telefone")
                );

                var especialidade = new Especialidade(
                        rs.getInt("especialidade_id"),
                        rs.getString("especialidade_descricao")
                );

                var med = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("medico_cpf"),
                        rs.getString("medico_nome"),
                        rs.getString("inscricaoCRM"),
                        especialidade
                );

                Consulta consulta = new Consulta(
                        rs.getInt("consulta_id"),
                        LocalDateTime.parse(rs.getString("dataHora")),
                        StatusConsulta.valueOf(rs.getString("status")),
                        pac,
                        med
                );
                consultas.add(consulta);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar consulta por médico: "+ e.getMessage());
        }

        return consultas.iterator();
    }

    @Override
    public Iterator<Consulta> findAll() {
        String sql = """
            SELECT 
                c.id AS consulta_id, c.dataHora, c.status,
                p.id AS paciente_id, p.cpf AS paciente_cpf, p.nome AS paciente_nome, p.telefone AS paciente_telefone,
                m.id AS medico_id, m.cpf AS medico_cpf, m.nome AS medico_nome, m.inscricaoCRM,
                e.id AS especialidade_id, e.descricao AS especialidade_descricao
            FROM Consulta c
            JOIN Paciente p ON c.paciente_id = p.id
            JOIN Medico m ON c.medico_id = m.id
            JOIN Especialidade e ON m.especialidade_id = e.id
        """;

        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                var pac = new Paciente(
                        rs.getInt("paciente_id"),
                        rs.getString("paciente_cpf"),
                        rs.getString("paciente_nome"),
                        rs.getString("paciente_telefone")
                );

                var especialidade = new Especialidade(
                        rs.getInt("especialidade_id"),
                        rs.getString("especialidade_descricao")
                );

                var medico = new Medico(
                        rs.getInt("medico_id"),
                        rs.getString("medico_cpf"),
                        rs.getString("medico_nome"),
                        rs.getString("inscricaoCRM"),
                        especialidade
                );

                Consulta consulta = new Consulta(
                        rs.getInt("consulta_id"),
                        LocalDateTime.parse(rs.getString("dataHora")),
                        StatusConsulta.valueOf(rs.getString("status")),
                        pac,
                        medico
                );

                consultas.add(consulta);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar consultas: "+ e.getMessage());
        }

        return consultas.iterator();
    }

    @Override
    public void update(Consulta consulta) {
        String sql = """
            UPDATE Consulta
            SET dataHora = ?, status = ?, paciente_id = ?, medico_id = ?
            WHERE id = ?
        """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getDataHora().toString());
            stmt.setString(2, consulta.getStatus().toString());
            stmt.setInt(3, consulta.getPaciente().getId());
            stmt.setInt(4, consulta.getMedico().getId());
            stmt.setInt(5, consulta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta: "+ e.getMessage());
        }
    }
}