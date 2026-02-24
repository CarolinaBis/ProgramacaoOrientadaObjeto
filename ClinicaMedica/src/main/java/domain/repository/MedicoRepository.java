package domain.repository;

import domain.entity.Especialidade;
import domain.entity.Medico;

import java.util.Iterator;
import java.util.Optional;

public interface MedicoRepository extends Repository<Medico>{
    Optional<Medico> findByCPF(String cpf);
    Optional<Medico> findByCRM(String crm);
    Iterator<Medico> findByEspecialidade(Especialidade especialidade);
}