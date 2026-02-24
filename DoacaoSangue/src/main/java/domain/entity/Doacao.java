package domain.entity;

import java.time.LocalDate;

public class Doacao {
    private int id;
    private int doadorId;
    private LocalDate dataDoacao;
    private double qtdDoadaMl;

    public Doacao(int id, int doadorId, LocalDate dataDoacao, double qtdDoadaMl) {
        this.setId(id);
        this.setDoadorId(doadorId);
        this.setDataDoacao(dataDoacao);
        this.setQtdDoadaMl(qtdDoadaMl);
    }

    public Doacao(int doadorId, double qtdDoadaMl, LocalDate dataDoacao) {
        this(0, doadorId, dataDoacao, qtdDoadaMl);
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        if (dataDoacao == null) {
            throw new IllegalArgumentException("Data da doação não pode ser nula");
        }
        this.dataDoacao = dataDoacao;
    }

    public void setQtdDoadaMl(double qtdDoadaMl) {
        if (qtdDoadaMl <= 0) {
            throw new IllegalArgumentException("Quantidade doada deve ser maior que zero");
        }
        this.qtdDoadaMl = qtdDoadaMl;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDoadorId() { return doadorId; }
    public void setDoadorId(int doadorId) { this.doadorId = doadorId; }
    public LocalDate getDataDoacao() { return dataDoacao; }
    public double getQtdDoadaMl() { return qtdDoadaMl; }
}