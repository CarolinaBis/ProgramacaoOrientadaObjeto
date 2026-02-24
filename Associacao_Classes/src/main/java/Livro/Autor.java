package Livro;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private String nome;
    private String telefone;
    private List<Livro> livros = new ArrayList<>();

    public Autor(String nome, String telefone) {
        this.setNome(nome);
        this.setTelefone(telefone);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
