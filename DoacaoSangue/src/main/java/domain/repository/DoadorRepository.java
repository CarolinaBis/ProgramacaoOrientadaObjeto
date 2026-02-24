package domain.repository;

import domain.entity.Doador;
import domain.entity.TipoSanguineo;
import java.util.Iterator;
import java.util.Optional;

public interface DoadorRepository {
    // ✅ REQUISITO 1: ID autoincremento gerado pelo BD
    void save(Doador doador);
    void update(Doador doador);

    // ✅ REQUISITO 8: Retorno com Iterator
    Iterator<Doador> findAll();
    Iterator<Doador> findByTipoSanguineo(TipoSanguineo tipoSanguineo);

    // ✅ REQUISITO 7: Buscar doadores aptos (apenas intervalo mínimo)
    Iterator<Doador> findAptos();

    // ✅ REQUISITO 8: Retorno com Optional
    Optional<Doador> findById(int id);

    // ✅ REQUISITO 2: Necessário para verificar CPF único
    Optional<Doador> findByCpf(String cpf);
}