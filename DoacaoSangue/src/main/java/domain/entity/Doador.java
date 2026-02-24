package domain.entity;

import java.time.LocalDate;

public class Doador {
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private TipoSanguineo tipoSanguineo;
    private double peso;
    private LocalDate dataUltimaDoacao;

    public Doador(int id, String nome, String cpf, LocalDate dataNascimento,
                  Sexo sexo, TipoSanguineo tipoSanguineo, double peso,
                  LocalDate dataUltimaDoacao) {
        this.setId(id);
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
        this.setSexo(sexo);
        this.setTipoSanguineo(tipoSanguineo);
        this.setPeso(peso);
        this.setDataUltimaDoacao(dataUltimaDoacao);
    }

    public Doador(String nome, String cpf, LocalDate dataNascimento,
                  Sexo sexo, TipoSanguineo tipoSanguineo, double peso) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setSexo(sexo);
        setTipoSanguineo(tipoSanguineo);
        setPeso(peso);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(Sexo sexo) {
        if (sexo == null) {
            throw new IllegalArgumentException("Sexo não pode ser nulo");
        }
        this.sexo = sexo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        if (tipoSanguineo == null) {
            throw new IllegalArgumentException("Tipo sanguíneo não pode ser nulo");
        }
        this.tipoSanguineo = tipoSanguineo;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        }
        this.peso = peso;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Sexo getSexo() { return sexo; }
    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public double getPeso() { return peso; }
    public LocalDate getDataUltimaDoacao() { return dataUltimaDoacao; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setDataUltimaDoacao(LocalDate dataUltimaDoacao) {
        this.dataUltimaDoacao = dataUltimaDoacao;
    }

    // ✅ REQUISITO: Método toString() conforme diagrama de classes
    @Override
    public String toString() {
        return String.format("Doador[id=%d, nome=%s, cpf=%s, tipo=%s, peso=%.1fkg]",
                id, nome, cpf, tipoSanguineo.getValor(), peso);
    }
}