package domain.entity;

public class Especialidade {
    private int id;
    private String descricao;

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição da especialidade não pode ser nula ou vazia.");
        }
    }

    public Especialidade(int id, String descricao) {
        this(descricao);
        this.id = id;
    }

    public Especialidade(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }
}