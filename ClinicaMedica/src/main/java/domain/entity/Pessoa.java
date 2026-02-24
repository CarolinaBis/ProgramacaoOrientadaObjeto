package domain.entity;

public abstract class Pessoa implements ExibeDados {
    private int id;
    private String cpf;
    private String nome;

    public Pessoa(int id, String cpf, String nome) {
        this(cpf,nome);
        this.id = id;
    }

    public Pessoa(String cpf, String nome) {
        validarDados(cpf, nome);
        this.cpf = cpf;
        this.nome = nome;
    }

    private void validarDados(String cpf, String nome) {
        validarCPF(cpf);
        validarNome(nome);
    }

    private void validarCPF(String cpf){
        if (cpf == null || cpf.isBlank())
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
    }

    private void validarNome(String nome){
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validarCPF(cpf);
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }
}