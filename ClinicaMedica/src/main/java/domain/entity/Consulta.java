package domain.entity;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private final Paciente paciente;
    private final Medico medico;

    public Consulta(int id, LocalDateTime dataHora, StatusConsulta status, Paciente paciente, Medico medico) {
        validarDados(dataHora, paciente, medico);
        this.id = id;
        this.dataHora = dataHora;
        this.status =  status;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Consulta(LocalDateTime dataHora, Paciente paciente, Medico medico) {
        validarDados(dataHora, paciente, medico);
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        this.status = StatusConsulta.AGENDADA;
    }

    private void validarDados(LocalDateTime dataHora, Paciente paciente, Medico medico) {
        if (dataHora == null)
            throw new IllegalArgumentException("Data e hora da consulta não podem ser nulas.");

        if (paciente == null)
            throw new IllegalArgumentException("Paciente não pode ser nulo.");

        if (medico == null)
            throw new IllegalArgumentException("Médico não pode ser nulo.");

    }

    public void cancelarConsulta() {
        this.status = StatusConsulta.CANCELADA;
    }

    public void remarcarConsulta(LocalDateTime novaDataHora) {
        this.dataHora = novaDataHora;
        this.status = StatusConsulta.AGENDADA; // opcional: reinicia status após remarcação
    }

    public void confirmarConsulta() {
        this.status = StatusConsulta.CONFIRMADA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }
}