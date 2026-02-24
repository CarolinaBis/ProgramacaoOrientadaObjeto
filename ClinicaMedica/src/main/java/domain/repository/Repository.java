package domain.repository;

import java.util.Iterator;
import java.util.Optional;

public interface Repository <T>{
    void save(T entity);
    Optional<T> findById(int id);
    Iterator<T> findAll();
}