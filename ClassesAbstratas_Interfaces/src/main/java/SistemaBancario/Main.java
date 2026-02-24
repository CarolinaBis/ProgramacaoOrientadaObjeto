package SistemaBancario;

public class Main {
    public static void main(String[] args) {
        // Criar cliente primeiro
        Cliente cliente = new Cliente("João Silva", "123.456.789-00");

        // Criando objetos
        SeguroVida seguro = new SeguroVida("Seguro Familiar", 500.0, cliente);
        Corrente corrente = new Corrente("12345", 1000.0, "Conta Corrente Principal", 2000.0);
        Poupanca poupanca = new Poupanca("67890", 5000.0, "Poupança", 6.5);

        // Usando polimorfismo com IPrint
        IPrint[] imprimiveis = {seguro, corrente, poupanca};

        System.out.println("=== EXIBINDO INFORMAÇÕES ===");
        for (IPrint item : imprimiveis) {
            item.exibirInfo();
            System.out.println("----------------------");
        }

        // Usando polimorfismo com ITributavel
        System.out.println("=== CALCULANDO TRIBUTOS ===");
        ITributavel[] tributaveis = {seguro, corrente};

        for (ITributavel item : tributaveis) {
            item.calcularTributo();
        }

        System.out.println("=== APLICANDO RENDIMENTOS ===");
        poupanca.aplicarRendimento();

    }
}