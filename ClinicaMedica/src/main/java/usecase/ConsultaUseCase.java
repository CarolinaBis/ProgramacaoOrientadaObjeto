package usecase;

import domain.entity.Consulta;
import domain.entity.Medico;
import domain.entity.Paciente;
import domain.entity.StatusConsulta;
import domain.repository.ConsultaRepository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

public class ConsultaUseCase {
    private final ConsultaRepository consultaRepository;
    private static final int DURACAO_CONSULTA = 30;
    // Poderia usar o tipo Duration também:
    //private static final Duration DURACAO_CONSULTA = Duration.ofMinutes(30);

    public ConsultaUseCase(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void save(Consulta consulta) {
        // Regra 7: Não pode marcar consulta no passado
        if (consulta.getDataHora().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Não é possível marcar consulta com data/hora anterior à data/hora atual.");

        // Regra 6: Verificar conflito de horário (considera duração padrão 30min)
        Iterator<Consulta> consultasMedico = consultaRepository.findByMedico(consulta.getMedico());
        while (consultasMedico.hasNext()) {
            Consulta c = consultasMedico.next();
            if (c.getStatus() != StatusConsulta.CANCELADA && conflitoDeHorario(consulta.getDataHora(), c.getDataHora()))
                throw new IllegalArgumentException("Conflito de horário com outra consulta agendada para o médico.");
        }

        consultaRepository.save(consulta);
    }

    public Optional<Consulta> findById(int id) {
        return consultaRepository.findById(id);
    }

    public Iterator<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public void update(Consulta consulta) {
        consultaRepository.update(consulta);
    }

    public Iterator<Consulta> findByPaciente(Paciente paciente) {
        return consultaRepository.findByPaciente(paciente);
    }

    public Iterator<Consulta> findByMedico(Medico medico) {
        return consultaRepository.findByMedico(medico);
    }

    public void confirmarConsulta(Consulta consulta) {
        if (consulta.getStatus() == StatusConsulta.CANCELADA)
            throw new IllegalStateException("Não é possível confirmar uma consulta cancelada.");

        consulta.confirmarConsulta();
        consultaRepository.update(consulta);
    }


    public void cancelarConsulta(Consulta consulta) {
        // Regra 8: Só cancelar antes do horário de início
        if (!consulta.getDataHora().isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Não é possível cancelar uma consulta após seu horário de início.");

        if (consulta.getStatus() == StatusConsulta.CANCELADA)
            throw new IllegalStateException("Consulta já está cancelada.");

        consulta.cancelarConsulta();
        consultaRepository.update(consulta);
    }

    public void remarcarConsulta(Consulta consulta, LocalDateTime novaDataHora) {
        if (novaDataHora == null)
            throw new IllegalArgumentException("Nova data e hora não podem ser nulas.");

        if (novaDataHora.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Nova data e hora não podem estar no passado.");

        // Regra: conflito de horário
        Iterator<Consulta> consultasMedico = consultaRepository.findByMedico(consulta.getMedico());
        while (consultasMedico.hasNext()) {
            Consulta outra = consultasMedico.next();
            if (outra.getId() != consulta.getId() &&
                    outra.getStatus() != StatusConsulta.CANCELADA &&
                    conflitoDeHorario(novaDataHora, outra.getDataHora()))
                throw new IllegalArgumentException("Conflito de horário com outra consulta.");

        }

        consulta.remarcarConsulta(novaDataHora);
        consultaRepository.update(consulta);
    }

    // Método interno que verifica se há conflito entre duas consultas
    // Retorna true para conflito e false caso não ocorra
    private boolean conflitoDeHorario(LocalDateTime inicioNovaConsulta, LocalDateTime inicioConsultaExistente) {
        LocalDateTime fimNovaConsulta = inicioNovaConsulta.plusMinutes(DURACAO_CONSULTA);
        LocalDateTime fimConsultaExistente = inicioConsultaExistente.plusMinutes(DURACAO_CONSULTA);

        return inicioNovaConsulta.isBefore(fimConsultaExistente) && inicioConsultaExistente.isBefore(fimNovaConsulta);
    }
}