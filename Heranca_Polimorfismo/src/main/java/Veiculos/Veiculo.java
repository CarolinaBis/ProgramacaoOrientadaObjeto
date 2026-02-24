package Veiculos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Veiculo {
    private String placa;
    private String modelo;
    private List<Manutencao> manutencoes = new ArrayList<>();
    private Proprietario proprietario;

    public Veiculo(String placa, String modelo) {
        this.setPlaca(placa);
        this.setModelo(modelo);
    }

    public void adicionarManutencao(Manutencao manutencao) {
        if (manutencao != null && !this.getManutencoes().contains(manutencao)) {
            this.getManutencoes().add(manutencao);
            if (manutencao.getVeiculo() != this) {
                manutencao.setVeiculo(this);
            }
        }
    }

    public void removerManutencao(Manutencao manutencao) {
        if (this.getManutencoes().remove(manutencao)) {
            if (manutencao.getVeiculo() == this) {
                manutencao.setVeiculo(null);
            }
        }
    }

    @Override
    public String toString() {
        return "Veiculo{placa='" + getPlaca() + "', modelo='" + getModelo() +
                "', proprietario=" + (getProprietario() != null ? getProprietario().getNome() : "Nenhum") +
                ", manutencoes=" + getManutencoes().size() + "}";
    }

    public Iterator<Manutencao> obterManutencoes() {
        return this.getManutencoes().iterator();
    }

    public Iterator<Manutencao> iterator() {
        return this.getManutencoes().iterator();
    }

    public List<Manutencao> getManutencoes() {
        return new ArrayList<>(manutencoes);
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        if (manutencoes == null) {
            throw new IllegalArgumentException("Lista de manutenções não pode ser nula");
        }
        this.manutencoes = new ArrayList<>(manutencoes);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}