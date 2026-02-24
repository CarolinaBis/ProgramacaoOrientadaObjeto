package domain.usecase;

import domain.entity.Doador;
import domain.entity.TipoSanguineo;
import domain.exception.EntityAlreadyExistsException;
import domain.repository.DoadorRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.Optional;

public class DoadorUseCase {
    private final DoadorRepository repository;

    public DoadorUseCase(DoadorRepository repository) {
        this.repository = repository;
    }


    public void adicionarDoador(Doador doador) {
        Optional<Doador> existente = repository.findByCpf(doador.getCpf());
        if (existente.isPresent()) {
            throw new EntityAlreadyExistsException("CPF já cadastrado: " + doador.getCpf());
        }

        if (!estaAptoParaDoar(doador, LocalDate.now())) {
            throw new IllegalArgumentException("Doador não atende aos requisitos mínimos para doação (idade 16-69 anos, peso ≥ 50kg)");
        }

        repository.save(doador);
    }


    public void atualizarDoador(Doador doador) {
        Optional<Doador> existente = repository.findById(doador.getId());
        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Doador não encontrado com ID: " + doador.getId());
        }
        Optional<Doador> doadorComCpf = repository.findByCpf(doador.getCpf());
        if (doadorComCpf.isPresent() && doadorComCpf.get().getId() != doador.getId()) {
            throw new EntityAlreadyExistsException("CPF já cadastrado por outro doador: " + doador.getCpf());
        }

        repository.update(doador);
    }

    // ✅ REQUISITO: Buscar todos os doadores
    public Iterator<Doador> buscarTodosDoadores() {
        return repository.findAll();
    }

    // ✅ REQUISITO: Buscar doadores por Tipo Sanguíneo
    public Iterator<Doador> buscarDoadoresPorTipoSanguineo(TipoSanguineo tipoSanguineo) {
        return repository.findByTipoSanguineo(tipoSanguineo);
    }

    // ✅ REQUISITO 7: Buscar doadores aptos
    public Iterator<Doador> buscarDoadoresAptos() {
        return repository.findAptos();
    }

    // Métodos auxiliares
    public Optional<Doador> buscarDoadorPorId(int id) {
        return repository.findById(id);
    }

    public Optional<Doador> buscarDoadorPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    // ✅ MÉTODO PRIVADO: Verifica aptidão para doação (REQUISITO 3.1 e 3.2)
    private boolean estaAptoParaDoar(Doador doador, LocalDate dataReferencia) {
        // REQUISITO 3.1: Idade entre 16 e 69 anos
        int idade = Period.between(doador.getDataNascimento(), dataReferencia).getYears();
        if (idade < 16 || idade > 69) {
            return false;
        }

        // REQUISITO 3.2: Peso mínimo 50kg
        if (doador.getPeso() < 50) {
            return false;
        }

        return true;
    }
}