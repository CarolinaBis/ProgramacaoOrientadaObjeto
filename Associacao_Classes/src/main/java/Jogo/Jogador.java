package Jogo;

public class Jogador {
    private String nome;
    private String posicao;
    private int numeroCamisa;
    private Time time;

    public Jogador(String nome, String posicao, Time time, int numeroCamisa) {
        this.setNome(nome);
        this.setPosicao(posicao);
        this.setTime(time);
        this.setNumeroCamisa(numeroCamisa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
