package usecase;

import domain.entity.Especialidade;
import domain.repository.EspecialidadeRepository;

import java.util.Iterator;
import java.util.Optional;

public class EspecialidadeUseCase {
    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public void save(Especialidade especialidade) {
        especialidadeRepository.save(especialidade);
    }

    public Optional<Especialidade> findById(int id) {
        return especialidadeRepository.findById(id);
    }

    public Iterator<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }

    public void update(Especialidade especialidade) {
        especialidadeRepository.update(especialidade);
    }

    public void delete(Especialidade especialidade) {
        especialidadeRepository.delete(especialidade);
    }
}