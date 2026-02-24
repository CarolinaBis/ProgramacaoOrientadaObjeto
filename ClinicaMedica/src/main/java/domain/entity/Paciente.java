package domain.entity;

public class Paciente extends Pessoa{
    private String telefone;

    public Paciente(String cpf, String nome, String telefone) {
        super(cpf, nome);
        this.telefone = telefone;
    }

    public Paciente(int id, String cpf, String nome, String telefone) {
        super(id, cpf, nome);
        this.telefone = telefone;
    }

    @Override
    public void exibirInfo() {
        System.out.println("\nID: " + getId() +
                "\nNome: " + getNome() +
                "\nCPF: " +getCpf() +
                "\nTelefone: " + telefone);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}