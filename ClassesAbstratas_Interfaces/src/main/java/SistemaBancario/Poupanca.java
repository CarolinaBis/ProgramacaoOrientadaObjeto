package SistemaBancario;

public class Poupanca extends Conta {
    private double rendimentoAnual;

    public Poupanca(String numero, double saldo, String descricao, double rendimentoAnual) {
        super(numero, saldo, descricao);
        this.rendimentoAnual = rendimentoAnual;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Conta Poupança: " + getDescricao());
        System.out.println("Número: " + getNumero());
        System.out.println("Saldo: R$ " + getSaldo());
        System.out.println("Rendimento Anual: " + rendimentoAnual + "%");
    }

    public void aplicarRendimento() {
        double rendimentoMensal = getSaldo() * (rendimentoAnual / 12 / 100);
        setSaldo(getSaldo() + rendimentoMensal);
        System.out.println("Rendimento aplicado: R$ " + rendimentoMensal);
    }

    public double getRendimentoAnual() {
        return rendimentoAnual;
    }

    public void setRendimentoAnual(double rendimentoAnual) {
        this.rendimentoAnual = rendimentoAnual;
    }
}