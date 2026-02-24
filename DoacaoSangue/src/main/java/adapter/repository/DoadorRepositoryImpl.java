package adapter.repository;

import adapter.config.SQLiteConnectionFactory;
import domain.entity.Doador;
import domain.entity.Sexo;
import domain.entity.TipoSanguineo;
import domain.exception.EntityAlreadyExistsException;
import domain.repository.DoadorRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class DoadorRepositoryImpl implements DoadorRepository {

    @Override
    public void save(Doador doador) {
        String sql = """
                    INSERT INTO doador (nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, doador.getNome());
            stmt.setString(2, doador.getCpf());
            stmt.setDate(3, Date.valueOf(doador.getDataNascimento()));
            stmt.setString(4, doador.getSexo().toString());
            stmt.setString(5, doador.getTipoSanguineo().toString());
            stmt.setDouble(6, doador.getPeso());

            if (doador.getDataUltimaDoacao() != null) {
                stmt.setDate(7, Date.valueOf(doador.getDataUltimaDoacao()));
            } else {
                stmt.setNull(7, Types.DATE);
            }

            stmt.executeUpdate();

            // ✅ REQUISITO 1: Recupera ID autoincremento gerado pelo BD
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                doador.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            // ✅ REQUISITO 2: Tratamento de CPF único
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                throw new EntityAlreadyExistsException("CPF já cadastrado: " + doador.getCpf());
            }
            throw new RuntimeException("Erro ao salvar doador: " + e.getMessage());
        }
    }

    @Override
    public void update(Doador doador) {
        String sql = """
                    UPDATE doador 
                    SET nome = ?, cpf = ?, data_nascimento = ?, sexo = ?, tipo_sanguineo = ?, peso = ?, data_ultima_doacao = ?
                    WHERE id = ?
                """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doador.getNome());
            stmt.setString(2, doador.getCpf());
            stmt.setDate(3, Date.valueOf(doador.getDataNascimento()));
            stmt.setString(4, doador.getSexo().toString());
            stmt.setString(5, doador.getTipoSanguineo().toString());
            stmt.setDouble(6, doador.getPeso());

            if (doador.getDataUltimaDoacao() != null) {
                stmt.setDate(7, Date.valueOf(doador.getDataUltimaDoacao()));
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setInt(8, doador.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                throw new EntityAlreadyExistsException("CPF já cadastrado: " + doador.getCpf());
            }
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage());
        }
    }

    @Override
    public Optional<Doador> findById(int id) {
        String sql = """
                    SELECT id, nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao
                    FROM doador WHERE id = ?
                """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(resultSetToDoador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doador por id: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Doador> findByCpf(String cpf) {
        String sql = """
                    SELECT id, nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao
                    FROM doador WHERE cpf = ?
                """;

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(resultSetToDoador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doador por CPF: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Iterator<Doador> findAll() {
        String sql = """
                    SELECT id, nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao
                    FROM doador
                """;

        List<Doador> doadores = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                doadores.add(resultSetToDoador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os doadores: " + e.getMessage());
        }

        return doadores.iterator();
    }

    @Override
    public Iterator<Doador> findByTipoSanguineo(TipoSanguineo tipoSanguineo) {
        String sql = """
                    SELECT id, nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao
                    FROM doador WHERE tipo_sanguineo = ?
                """;

        List<Doador> doadores = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoSanguineo.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                doadores.add(resultSetToDoador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doadores por tipo sanguíneo: " + e.getMessage());
        }

        return doadores.iterator();
    }

    @Override
    public Iterator<Doador> findAptos() {
        // ✅ REQUISITO 7: Busca doadores aptos (apenas intervalo mínimo)
        // Usando JULIANDAY para calcular diferença em dias
        String sql = """
                    SELECT id, nome, cpf, data_nascimento, sexo, tipo_sanguineo, peso, data_ultima_doacao
                    FROM doador
                    WHERE (data_ultima_doacao IS NULL) OR 
                          (sexo = 'M' AND julianday('now') - julianday(data_ultima_doacao) >= 60) OR
                          (sexo = 'F' AND julianday('now') - julianday(data_ultima_doacao) >= 90)
                """;

        List<Doador> doadores = new ArrayList<>();

        try (Connection conn = SQLiteConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                doadores.add(resultSetToDoador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar doadores aptos: " + e.getMessage());
        }

        return doadores.iterator();
    }

    private Doador resultSetToDoador(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String cpf = rs.getString("cpf");
        LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
        Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
        TipoSanguineo tipoSanguineo = TipoSanguineo.valueOf(rs.getString("tipo_sanguineo"));
        double peso = rs.getDouble("peso");
        Date dataUltimaDoacaoDate = rs.getDate("data_ultima_doacao");
        LocalDate dataUltimaDoacao = dataUltimaDoacaoDate != null ?
                dataUltimaDoacaoDate.toLocalDate() : null;

        // ✅ Ordem correta de parâmetros conforme construtor
        return new Doador(id, nome, cpf, dataNascimento, sexo, tipoSanguineo, peso, dataUltimaDoacao);
    }
}