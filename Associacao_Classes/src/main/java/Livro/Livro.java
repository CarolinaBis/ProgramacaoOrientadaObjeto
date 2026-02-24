package Livro;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private int anoPuiblicacao;
    private double preco;
    private List<Autor> autores = new ArrayList<>();

    public Livro(String titulo, int anoPuiblicacao, double preco) {
        this.setTitulo(titulo);
        this.setAnoPuiblicacao(anoPuiblicacao);
        this.setPreco(preco);
    }

    public void adicionarAutor(Autor autor){
        this.autores.add(autor);

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPuiblicacao() {
        return anoPuiblicacao;
    }

    public void setAnoPuiblicacao(int anoPuiblicacao) {
        this.anoPuiblicacao = anoPuiblicacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
