package domain.repository;

import domain.entity.Consulta;
import domain.entity.Medico;
import domain.entity.Paciente;

import java.util.Iterator;

public interface ConsultaRepository extends Repository<Consulta>{
    void update(Consulta consulta);
    Iterator<Consulta> findByPaciente(Paciente paciente);
    Iterator<Consulta> findByMedico(Medico medico);

}