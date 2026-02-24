package domain.repository;

import domain.entity.Paciente;

import java.util.Optional;

public interface PacienteRepository extends Repository<Paciente>{
    Optional<Paciente> findByCPF(String cpf);
}