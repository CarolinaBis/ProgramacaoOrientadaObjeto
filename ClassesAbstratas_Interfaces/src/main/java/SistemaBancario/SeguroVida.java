package SistemaBancario;

public class SeguroVida extends Produto implements ITributavel, IPrint {
    private double premio;
    private Cliente cliente;

    public SeguroVida(String descricao, double premio, Cliente cliente) {
        super(descricao);
        this.premio = premio;
        this.cliente = cliente;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Seguro de Vida: " + getDescricao());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Prêmio: R$ " + premio);
    }

    @Override
    public void calcularTributo() {
        double tributo = premio * 0.07;
        System.out.println("Tributo: R$ " + tributo);
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    public Cliente getCliente() { // ✅ Retornar Cliente, não double
        return cliente;
    }

    public void setCliente(Cliente cliente) { // ✅ Receber Cliente, não double
        this.cliente = cliente;
    }
}