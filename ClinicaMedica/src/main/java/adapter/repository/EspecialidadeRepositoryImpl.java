package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.Especialidade;
import domain.repository.EspecialidadeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class EspecialidadeRepositoryImpl implements EspecialidadeRepository {

    @Override
    public void save(Especialidade especialidade) {
        String sql = "INSERT INTO Especialidade (descricao) VALUES (?)";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, especialidade.getDescricao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    especialidade.setId(rs.getInt(1)); // ÚNICO USO DE SETTER
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar especialidade", e);
        }
    }

    @Override
    public Optional<Especialidade> findById(int id) {
        String sql = "SELECT * FROM Especialidade WHERE id = ?";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String descricao = rs.getString("descricao");
                    return Optional.of(new Especialidade(id, descricao));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar especialidade por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Especialidade> findAll() {
        List<Especialidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM Especialidade";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                lista.add(new Especialidade(id, descricao));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as especialidades", e);
        }
        return lista.iterator();
    }

    @Override
    public void update(Especialidade especialidade) {
        String sql = "UPDATE Especialidade SET descricao = ? WHERE id = ?";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getDescricao());
            stmt.setInt(2, especialidade.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar especialidade", e);
        }
    }

    @Override
    public void delete(Especialidade especialidade) {
        String sql = "DELETE FROM Especialidade WHERE id = ?";
        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especialidade.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir especialidade", e);
        }
    }
}