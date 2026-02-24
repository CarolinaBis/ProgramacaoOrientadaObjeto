package usecase;

import domain.entity.Paciente;
import domain.exception.EntityAlreadyExistsException;
import domain.repository.PacienteRepository;

import java.util.Iterator;
import java.util.Optional;

public class PacienteUseCase {
    private final PacienteRepository pacienteRepository;

    public PacienteUseCase(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void save(Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByCPF(paciente.getCpf());
        if (pacienteOptional.isPresent())
            throw new EntityAlreadyExistsException("CPF já cadastrado para outro paciente.");

        pacienteRepository.save(paciente);
    }

    public Optional<Paciente> findById(int id) {
        return pacienteRepository.findById(id);
    }

    public Iterator<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findByCPF(String cpf) {
        return pacienteRepository.findByCPF(cpf);
    }
}