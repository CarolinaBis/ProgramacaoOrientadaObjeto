package SistemaBancario;

public class Corrente extends Conta implements ITributavel{
    private double limiteDisponivel;

    public Corrente(String numero, double saldo, String descricao, double limiteDisponivel) {
        super(numero, saldo, descricao);
        this.limiteDisponivel = limiteDisponivel;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Conta Corrente: " + descricao);
        System.out.println("Número: " + getNumero());
        System.out.println("Saldo: R$ " + getSaldo());
        System.out.println("Limite: R$ " + limiteDisponivel);

    }

    @Override
    public void calcularTributo() {
        System.out.println("Tributo: " + limiteDisponivel * 0.05);
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }
}
