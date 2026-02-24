import adapter.config.AppConfig;
import adapter.config.DatabaseInitializer;
import domain.entity.*;

import domain.exception.EntityAlreadyExistsException;
import usecase.ConsultaUseCase;
import usecase.EspecialidadeUseCase;
import usecase.MedicoUseCase;
import usecase.PacienteUseCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Inicializa o banco
        DatabaseInitializer.init();

        // Cria os use cases
        EspecialidadeUseCase especialidadeUseCase = AppConfig.createEspecialidadeUseCase();
        PacienteUseCase pacienteUseCase = AppConfig.createPacienteUseCase();
        MedicoUseCase medicoUseCase = AppConfig.createMedicoUseCase();
        ConsultaUseCase consultaUseCase = AppConfig.createConsultaUseCase();

        try {
            Especialidade especialidade1 = new Especialidade("Cardiologia");
            especialidadeUseCase.save(especialidade1);

            Especialidade especialidade2 = new Especialidade("Ortopedia");
            especialidadeUseCase.save(especialidade2);

            Paciente paciente = new Paciente("123.456.789-00", "João", "11999990000");
            pacienteUseCase.save(paciente);

            Medico medico1 = new Medico("987.654.321-00", "Dra. Maria", "CRM123456", especialidade1);
            medicoUseCase.save(medico1);

            Medico medico2= new Medico("777.777.777-77", "Dr. Joaquim", "CRM777777", especialidade2);
            medicoUseCase.save(medico2);

            LocalDateTime dataHoraConsulta = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0);
            Consulta consulta = new Consulta(dataHoraConsulta, paciente, medico1);
            consultaUseCase.save(consulta);
            System.out.println("Consulta agendada com sucesso.");

            //Consulta conflito = new Consulta(dataHoraConsulta, paciente, medico1);
            //consultaUseCase.save(conflito);

            // Remarca a consulta
            LocalDateTime novaDataHora = dataHoraConsulta.plusHours(1);
            consultaUseCase.remarcarConsulta(consulta, novaDataHora);
            System.out.println("Consulta remarcada com sucesso.");

            // Confirma a consulta
            consultaUseCase.confirmarConsulta(consulta);
            System.out.println("Consulta confirmada com sucesso.");

            consultaUseCase.cancelarConsulta(consulta);

            // Lista consultas do médico
            System.out.println("\nConsultas do médico " + medico1.getNome() + ":");
            Iterator<Consulta> consultasMedico = consultaUseCase.findByMedico(medico1);
            while (consultasMedico.hasNext()) {
                Consulta c = consultasMedico.next();
                System.out.println("Consulta ID: " + c.getId()
                        + "\nData/Hora: " + c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
                        + "\nPaciente: " + c.getPaciente().getNome()
                        + "\nStatus: " + c.getStatus().toString());
            }

            // Lista consultas do paciente
            System.out.println("\nConsultas do paciente " + paciente.getNome() + ":");
            Iterator<Consulta> consultasPaciente = consultaUseCase.findByPaciente(paciente);
            while (consultasPaciente.hasNext()) {
                Consulta c = consultasPaciente.next();
                System.out.println("Consulta ID: " + c.getId()
                        + "\nData/Hora: " + c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
                        + "\nPaciente: " + c.getPaciente().getNome()
                        + "\nStatus: " + c.getStatus().toString());
            }

        } catch (EntityAlreadyExistsException e) {
            System.out.println("ERRO: "+e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("ERRO: "+e.getMessage());
        }catch (RuntimeException e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }
}