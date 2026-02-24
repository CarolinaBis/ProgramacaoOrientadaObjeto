package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.Doacao;
import domain.entity.Doador;
import domain.repository.DoacaoRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoacaoRepositoryImpl implements DoacaoRepository {

    @Override
    public void save(Doacao doacao) {
        String sqlInsertDoacao = """
                    INSERT INTO doacao (doador_id, data_doacao, qtd_doadada_ml)
                    VALUES (?, ?, ?)
                """;

        String sqlUpdateDoador = """
                    UPDATE doador SET data_ultima_doacao = ? WHERE id = ?
                """;

        Connection conn = null;
        try {
            conn = SQLiteConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            // ✅ Inserir doação
            try (PreparedStatement stmtDoacao = conn.prepareStatement(sqlInsertDoacao, Statement.RETURN_GENERATED_KEYS)) {
                stmtDoacao.setInt(1, doacao.getDoadorId());
                stmtDoacao.setDate(2, Date.valueOf(doacao.getDataDoacao()));
                stmtDoacao.setDouble(3, doacao.getQtdDoadaMl());

                stmtDoacao.executeUpdate();

                // ✅ REQUISITO 1: Recupera ID autoincremento gerado pelo BD
                ResultSet generatedKeys = stmtDoacao.getGeneratedKeys();
                if (generatedKeys.next()) {
                    doacao.setId(generatedKeys.getInt(1));
                }
            }

            // ✅ REQUISITO 6: Atualizar a data da última doação do doador
            try (PreparedStatement stmtDoador = conn.prepareStatement(sqlUpdateDoador)) {
                stmtDoador.setDate(1, Date.valueOf(doacao.getDataDoacao()));
                stmtDoador.setInt(2, doacao.getDoadorId());
                stmtDoador.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
            throw new RuntimeException("Erro ao salvar doação: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Iterator<Doacao> findAll() {
        String sql = """
                    SELECT id, doador_id, data_doacao, qtd_doadada_ml
                    FROM doacao
                """;

        List<Doacao> doacoes = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // ✅ Ordem correta: id, doadorId, dataDoacao, qtdDoadaMl
                Doacao doacao = new Doacao(
                        rs.getInt("id"),
                        rs.getInt("doador_id"),
                        rs.getDate("data_doacao").toLocalDate(),
                        rs.getDouble("qtd_doadada_ml")
                );
                doacoes.add(doacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar doações: " + e.getMessage());
        }

        return doacoes.iterator();
    }

    @Override
    public Iterator<Doacao> findByDoador(Doador doador) {
        String sql = """
                    SELECT id, doador_id, data_doacao, qtd_doadada_ml
                    FROM doacao
                    WHERE doador_id = ?
                """;

        List<Doacao> doacoes = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doador.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // ✅ Ordem correta: id, doadorId, dataDoacao, qtdDoadaMl
                Doacao doacao = new Doacao(
                        rs.getInt("id"),
                        rs.getInt("doador_id"),
                        rs.getDate("data_doacao").toLocalDate(),
                        rs.getDouble("qtd_doadada_ml")
                );
                doacoes.add(doacao);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar doações por doador: " + e.getMessage());
        }

        return doacoes.iterator();
    }
}