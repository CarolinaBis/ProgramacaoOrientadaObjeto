import domain.entity.Doador;
import domain.entity.Sexo;
import domain.entity.TipoSanguineo;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Teste 1: Cálculo de idade
        System.out.println("=== TESTE DE CÁLCULOS MANUAIS ===");

        // Criar um doador nascido em 15/05/1990
        Doador doador = new Doador(1, "João Silva", "123.456.789-00",
                LocalDate.of(1990, 5, 15), Sexo.M, TipoSanguineo.A_POSITIVO, 70.5,
                LocalDate.of(2024, 1, 15));

        // Data de referência: 26/03/2025
        LocalDate dataRef = LocalDate.of(2025, 3, 26);

        // Cálculo manual da idade
        int anoNasc = doador.getDataNascimento().getYear();      // 1990
        int mesNasc = doador.getDataNascimento().getMonthValue(); // 5
        int diaNasc = doador.getDataNascimento().getDayOfMonth(); // 15

        int anoRef = dataRef.getYear();      // 2025
        int mesRef = dataRef.getMonthValue(); // 3
        int diaRef = dataRef.getDayOfMonth(); // 26

        int idade = anoRef - anoNasc;  // 2025 - 1990 = 35

        // Ajuste: ainda não fez aniversário em 2025?
        // Março (3) < Maio (5) → SIM, ainda não fez aniversário
        if (mesRef < mesNasc || (mesRef == mesNasc && diaRef < diaNasc)) {
            idade--;  // idade = 34
        }

        System.out.println("Idade calculada: " + idade + " anos");
        System.out.println("Doador nascido em: " + doador.getDataNascimento());
        System.out.println("Data referência: " + dataRef);

        // Teste 2: Cálculo de diferença em dias
        LocalDate ultimaDoacao = LocalDate.of(2024, 1, 15);
        LocalDate novaDoacao = LocalDate.of(2025, 3, 26);

        long diasUltima = converterParaDias(ultimaDoacao);
        long diasNova = converterParaDias(novaDoacao);
        long diferenca = diasNova - diasUltima;

        System.out.println("\nDiferença em dias: " + diferenca + " dias");
        System.out.println("Última doação: " + ultimaDoacao);
        System.out.println("Nova doação: " + novaDoacao);

        // Teste 3: Verificação de aptidão
        boolean aptoIdade = (idade >= 16 && idade <= 69);
        boolean aptoPeso = (doador.getPeso() >= 50);
        boolean aptoIntervalo = (diferenca >= 60);  // Homem, mínimo 60 dias

        System.out.println("\nVerificação de aptidão:");
        System.out.println("Idade OK (16-69): " + aptoIdade);
        System.out.println("Peso OK (≥50kg): " + aptoPeso);
        System.out.println("Intervalo OK (≥60 dias): " + aptoIntervalo);
        System.out.println("Totalmente apto: " + (aptoIdade && aptoPeso && aptoIntervalo));
    }

    private static long converterParaDias(LocalDate data) {
        int anos = data.getYear();
        int meses = data.getMonthValue();
        int dias = data.getDayOfMonth();

        long totalDias = (anos * 365L) + (meses * 30L) + dias;

        switch (meses) {
            case 2: totalDias -= 2; break;
            case 4: case 6: case 9: case 11: totalDias -= 1; break;
        }

        return totalDias;
    }
}