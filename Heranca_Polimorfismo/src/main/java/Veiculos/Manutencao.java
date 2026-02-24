package Veiculos;

import java.time.LocalDate;

public class Manutencao {
    private LocalDate data;
    private double custo;
    private Mecanico mecanico;
    private Veiculo veiculo;

    public Manutencao(LocalDate data, double custo) {
        this.setData(data);
        this.setCusto(custo);
    }

    @Override
    public String toString(){
        return "Manutencao{Data=" + data +
                ", Custo=" + custo +
                ", Mecânico=" + (mecanico != null ? mecanico.getNome() : "Nenhum") +
                ", Veículo=" + (veiculo != null ? veiculo.getPlaca() : "Nenhum") + "}";
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
