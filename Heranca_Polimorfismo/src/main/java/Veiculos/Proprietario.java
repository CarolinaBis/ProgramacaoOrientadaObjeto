package Veiculos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Proprietario extends Pessoa{
    private String telefone;
    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public String toString(){
        return "Proprietario{Nome: " + getNome() + ", Telefone: " + telefone + ", Veículos: " + veiculos.size() + "}";
    }

    public Proprietario(String nome, String telefone) {
        super(nome);
        this.setTelefone(telefone);
    }

    public void adicionarVeiculo(Veiculo veiculo){
        this.veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo){
        this.veiculos.remove(veiculo);
    }

    // MÉTODO ADICIONADO PARA ATENDER AO DIAGRAMA UML
    public Iterator<Veiculo> obterVeiculos(){
        return this.veiculos.iterator();
    }

    public Iterator<Veiculo> iterator(){
        return this.veiculos.iterator();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}