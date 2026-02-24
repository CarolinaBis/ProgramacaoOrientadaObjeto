package usecase;

import domain.entity.Especialidade;
import domain.entity.Medico;
import domain.exception.EntityAlreadyExistsException;
import domain.repository.MedicoRepository;

import java.util.Iterator;
import java.util.Optional;

public class MedicoUseCase {
    private final MedicoRepository medicoRepository;

    public MedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void save(Medico medico) {
        Optional<Medico> porCpf = medicoRepository.findByCPF(medico.getCpf());
        if (porCpf.isPresent())
            throw new EntityAlreadyExistsException("CPF já cadastrado para outro médico.");

        Optional<Medico> porCRM = medicoRepository.findByCRM(medico.getInscricaoCRM());
        if (porCRM.isPresent())
            throw new EntityAlreadyExistsException("CRM já cadastrado para outro médico.");

        medicoRepository.save(medico);
    }

    public Optional<Medico> findById(int id) {
        return medicoRepository.findById(id);
    } 

    public Iterator<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> findByCPF(String cpf) {
        return medicoRepository.findByCPF(cpf);
    }

    public Optional<Medico> findByCRM(String crm) {
        return medicoRepository.findByCRM(crm);
    }

    public Iterator<Medico> findByEspecialidade(Especialidade especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }
}