package adapter.config;

import adapter.repository.DoacaoRepositoryImpl;
import adapter.repository.DoadorRepositoryImpl;
import domain.repository.DoacaoRepository;
import domain.repository.DoadorRepository;
import domain.usecase.DoacaoUseCase;
import domain.usecase.DoadorUseCase;

public class AppConfig {
    public static DoadorUseCase createDoadorUseCase() {
        DoadorRepository repository = new DoadorRepositoryImpl();
        return new DoadorUseCase(repository);
    }

    public static DoacaoUseCase createDoacaoUseCase() {
        DoacaoRepository doacaoRepository = new DoacaoRepositoryImpl();
        return new DoacaoUseCase(doacaoRepository);
    }
}