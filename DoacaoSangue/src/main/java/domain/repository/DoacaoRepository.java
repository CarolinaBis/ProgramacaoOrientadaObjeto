package domain.repository;

import domain.entity.Doacao;
import domain.entity.Doador;
import java.util.Iterator;

public interface DoacaoRepository {
    void save(Doacao doacao);
    Iterator<Doacao> findAll();
    Iterator<Doacao> findByDoador(Doador doador);
}