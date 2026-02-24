package adapter.config;

import adapter.repository.ConsultaRepositoryImpl;
import adapter.repository.EspecialidadeRepositoryImpl;
import adapter.repository.MedicoRepositoryImpl;
import adapter.repository.PacienteRepositoryImpl;

import usecase.ConsultaUseCase;
import usecase.EspecialidadeUseCase;
import usecase.MedicoUseCase;
import usecase.PacienteUseCase;

public class AppConfig {

    public static EspecialidadeUseCase createEspecialidadeUseCase() {
        return new EspecialidadeUseCase(new EspecialidadeRepositoryImpl());
    }

    public static PacienteUseCase createPacienteUseCase() {
        return new PacienteUseCase(new PacienteRepositoryImpl());
    }

    public static MedicoUseCase createMedicoUseCase() {
        return new MedicoUseCase(new MedicoRepositoryImpl());
    }

    public static ConsultaUseCase createConsultaUseCase() {
        return new ConsultaUseCase(new ConsultaRepositoryImpl());
    }
}