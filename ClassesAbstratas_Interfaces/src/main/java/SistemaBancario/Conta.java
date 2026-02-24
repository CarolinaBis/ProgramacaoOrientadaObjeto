package SistemaBancario;

public abstract class Conta extends Produto implements IPrint {
    private String numero;
    private double saldo;

    public Conta(String numero, double saldo, String descricao) {
        super(descricao);
        this.setNumero(numero);
        this.setSaldo(saldo);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
