package Veiculos;

public class Moto extends Veiculo {
    private double cilindradas;

    public Moto(String placa, String modelo, double cilindradas) {
        super(placa, modelo);
        this.cilindradas = cilindradas;
    }

    @Override
    public String toString() {
        return "Moto{placa='" + getPlaca() + "', modelo='" + getModelo() +
                "', cilindradas=" + cilindradas + "}";
    }

    public double getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(double cilindradas) {
        this.cilindradas = cilindradas;
    }
}