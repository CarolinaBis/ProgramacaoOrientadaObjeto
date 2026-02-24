package domain.repository;

import domain.entity.Especialidade;

public interface EspecialidadeRepository extends Repository<Especialidade>{
    void update(Especialidade especialidade);
    void delete(Especialidade especialidade);
}