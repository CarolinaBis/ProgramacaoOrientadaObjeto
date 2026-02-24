package Veiculos;

import java.util.ArrayList;
import java.util.List;

public class Mecanico extends Pessoa{
    private double salario;
    List<Manutencao> manutencoes = new ArrayList<>();

    public Mecanico(String nome, double salario) {
        super(nome);
        this.setSalario(salario);
    }

    public void adicionarManutencao(Manutencao manutencao){
        this.manutencoes.add(manutencao);
    }

    public void removerManutencao(Manutencao manutencao){
        this.manutencoes.remove(manutencao);
    }

    @Override
    public String toString(){
        return "Mecanico{Nome=" + getNome() +
                ", Salário=" + getSalario() +
                ", Manutenções=" + manutencoes.size() + "}";
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }
}
