package Veiculos;

public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.setNome(nome);
    }

    @Override
    public String toString(){
        return "Pessoa{Nome=" + nome + "}";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
