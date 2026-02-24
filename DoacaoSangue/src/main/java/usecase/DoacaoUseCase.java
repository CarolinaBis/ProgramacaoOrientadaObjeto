package domain.usecase;

import domain.entity.Doacao;
import domain.entity.Doador;
import domain.entity.Sexo;
import domain.repository.DoacaoRepository;
import domain.repository.DoadorRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Optional;

public class DoacaoUseCase {
    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository = null;

    public DoacaoUseCase(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
        this.doadorRepository = doadorRepository;
    }

    // ✅ REQUISITO: Registrar doação
    public void registrarDoacao(Doacao doacao) {
        // ✅ REQUISITO 5: Validações básicas já feitas nos setters da Entity

        // Verificar se data não é futura
        if (doacao.getDataDoacao().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data da doação não pode ser futura");
        }

        // Buscar doador
        Optional<Doador> doadorOpt = doadorRepository.findById(doacao.getDoadorId());
        if (doadorOpt.isEmpty()) {
            throw new IllegalArgumentException("Doador não encontrado com ID: " + doacao.getDoadorId());
        }

        Doador doador = doadorOpt.get();

        // ✅ REQUISITO 3 COMPLETO: Verificar aptidão para doação
        if (!estaAptoParaDoar(doador, doacao.getDataDoacao())) {
            throw new IllegalArgumentException("Doador não está apto para doar na data " + doacao.getDataDoacao());
        }

        // ✅ REQUISITO 6: A atualização da data da última doação é feita no repositório
        doacaoRepository.save(doacao);
    }

    public Iterator<Doacao> buscarTodasDoacoes() {
        return doacaoRepository.findAll();
    }

    public Iterator<Doacao> buscarDoacoesPorDoador(int doadorId) {
        Optional<Doador> doador = doadorRepository.findById(doadorId);
        if (doador.isEmpty()) {
            throw new IllegalArgumentException("Doador não encontrado");
        }
        return doacaoRepository.findByDoador(doador.get());
    }

    // ✅ MÉTODO PRIVADO: Verifica todas as regras de aptidão (REQUISITO 3)
    private boolean estaAptoParaDoar(Doador doador, LocalDate dataDoacao) {
        // REQUISITO 3.1: Idade entre 16 e 69 anos
        int idade = Period.between(doador.getDataNascimento(), dataDoacao).getYears();
        if (idade < 16 || idade > 69) {
            return false;
        }

        // REQUISITO 3.2: Peso mínimo 50kg
        if (doador.getPeso() < 50) {
            return false;
        }

        // REQUISITO 3.3: Intervalo mínimo entre doações
        if (doador.getDataUltimaDoacao() != null) {
            long diasDesdeUltimaDoacao = ChronoUnit.DAYS.between(
                    doador.getDataUltimaDoacao(), dataDoacao);

            if (doador.getSexo() == Sexo.M && diasDesdeUltimaDoacao < 60) {
                return false;
            }
            if (doador.getSexo() == Sexo.F && diasDesdeUltimaDoacao < 90) {
                return false;
            }
        }

        return true;
    }
}