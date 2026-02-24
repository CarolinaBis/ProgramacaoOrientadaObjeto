package domain.entity;

public class Medico extends Pessoa {
    private String inscricaoCRM;
    private Especialidade especialidade;

    public Medico(int id, String cpf, String nome, String inscricaoCRM, Especialidade especialidade) {
        super(id, cpf, nome);
        validarDados(inscricaoCRM, especialidade);
        this.inscricaoCRM = inscricaoCRM;
        this.especialidade = especialidade;
    }

    public Medico(String cpf, String nome, String inscricaoCRM, Especialidade especialidade) {
        super(cpf, nome);
        validarDados(inscricaoCRM, especialidade);
        this.inscricaoCRM = inscricaoCRM;
        this.especialidade = especialidade;
    }

    private void validarDados(String crm, Especialidade esp) {
        validarCRM(crm);
        validarEspecialidade(esp);
    }

    private  void validarCRM(String crm){
        if (crm == null || crm.isBlank())
            throw new IllegalArgumentException("CRM não pode ser nulo ou vazio.");

    }

    private void validarEspecialidade(Especialidade esp){
        if (esp == null)
            throw new IllegalArgumentException("Especialidade não pode ser nula.");

    }

    @Override
    public void exibirInfo() {
        System.out.println("\nID: " + getId() +
                "\nNome: " + getNome() +
                "\nCPF: " + getCpf() +
                "\nCRM: " + inscricaoCRM +
                "\nEspecialidade: " + especialidade.getDescricao());
    }

    public String getInscricaoCRM() {
        return inscricaoCRM;
    }

    public void setInscricaoCRM(String inscricaoCRM) {
        validarCRM(inscricaoCRM);
        this.inscricaoCRM = inscricaoCRM;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        validarEspecialidade(especialidade);
        this.especialidade = especialidade;
    }
}