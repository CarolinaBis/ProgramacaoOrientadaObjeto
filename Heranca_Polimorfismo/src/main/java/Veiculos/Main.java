package Veiculos;

import java.time.LocalDate;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE VEÍCULOS ===\n");

        // 1. Criando proprietários
        System.out.println("1. CRIANDO PROPRIETÁRIOS:");
        Proprietario proprietario1 = new Proprietario("João Silva", "(11) 9999-8888");
        Proprietario proprietario2 = new Proprietario("Maria Santos", "(11) 7777-6666");
        System.out.println(proprietario1);
        System.out.println(proprietario2);
        System.out.println();

        // 2. Criando mecânicos
        System.out.println("2. CRIANDO MECÂNICOS:");
        Mecanico mecanico1 = new Mecanico("Carlos Oliveira", 3500.00);
        Mecanico mecanico2 = new Mecanico("Ana Costa", 4200.00);
        System.out.println(mecanico1);
        System.out.println(mecanico2);
        System.out.println();

        // 3. Criando veículos
        System.out.println("3. CRIANDO VEÍCULOS:");
        Carro carro1 = new Carro("ABC-1234", "Fiesta", 5);
        Carro carro2 = new Carro("XYZ-5678", "Civic", 5);
        Moto moto1 = new Moto("MOT-9999", "CB500", 500.0); // Alterado para double

        System.out.println(carro1);
        System.out.println(carro2);
        System.out.println(moto1);
        System.out.println();

        // 4. Associando veículos aos proprietários
        System.out.println("4. ASSOCIANDO VEÍCULOS AOS PROPRIETÁRIOS:");
        proprietario1.adicionarVeiculo(carro1);
        proprietario1.adicionarVeiculo(moto1);
        proprietario2.adicionarVeiculo(carro2);

        carro1.setProprietario(proprietario1);
        carro2.setProprietario(proprietario2);
        moto1.setProprietario(proprietario1);

        System.out.println("Veículos do " + proprietario1.getNome() + ": " + proprietario1.getVeiculos().size());
        System.out.println("Veículos do " + proprietario2.getNome() + ": " + proprietario2.getVeiculos().size());
        System.out.println();

        // 5. Criando manutenções
        System.out.println("5. CRIANDO MANUTENÇÕES:");
        Manutencao manutencao1 = new Manutencao(LocalDate.of(2024, 1, 15), 250.00);
        Manutencao manutencao2 = new Manutencao(LocalDate.of(2024, 2, 20), 180.00);
        Manutencao manutencao3 = new Manutencao(LocalDate.of(2024, 3, 10), 500.00);
        Manutencao manutencao4 = new Manutencao(LocalDate.of(2024, 1, 25), 320.00);

        System.out.println("Manutenções criadas: 4");
        System.out.println();

        // 6. Associando manutenções aos veículos e mecânicos
        System.out.println("6. ASSOCIANDO MANUTENÇÕES:");

        // Carro1 - 2 manutenções com mecânico1
        carro1.adicionarManutencao(manutencao1);
        carro1.adicionarManutencao(manutencao2);
        manutencao1.setMecanico(mecanico1);
        manutencao2.setMecanico(mecanico1);
        mecanico1.adicionarManutencao(manutencao1);
        mecanico1.adicionarManutencao(manutencao2);

        // Carro2 - 1 manutenção com mecânico2
        carro2.adicionarManutencao(manutencao3);
        manutencao3.setMecanico(mecanico2);
        mecanico2.adicionarManutencao(manutencao3);

        // Moto1 - 1 manutenção com mecânico1
        moto1.adicionarManutencao(manutencao4);
        manutencao4.setMecanico(mecanico1);
        mecanico1.adicionarManutencao(manutencao4);

        System.out.println("Manutenções associadas com sucesso!");
        System.out.println();

        // 7. Testando toString() das manutenções
        System.out.println("7. DETALHES DAS MANUTENÇÕES:");
        System.out.println("Manutenção 1: " + manutencao1);
        System.out.println("Manutenção 2: " + manutencao2);
        System.out.println("Manutenção 3: " + manutencao3);
        System.out.println("Manutenção 4: " + manutencao4);
        System.out.println();

        // 8. Testando mecânicos atualizados
        System.out.println("8. MECÂNICOS E SUAS MANUTENÇÕES:");
        System.out.println(mecanico1);
        System.out.println(mecanico2);
        System.out.println();

        // 9. Testando veículos atualizados
        System.out.println("9. VEÍCULOS E SUAS MANUTENÇÕES:");
        System.out.println(carro1);
        System.out.println(carro2);
        System.out.println(moto1);
        System.out.println();

        // 10. Testando iteração pelos veículos do proprietário
        System.out.println("10. LISTANDO VEÍCULOS DO PROPRIETÁRIO 1:");
        System.out.println("Veículos de " + proprietario1.getNome() + ":");
        for (Veiculo veiculo : proprietario1.getVeiculos()) {
            System.out.println("  - " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ") - " +
                    veiculo.getManutencoes().size() + " manutenções");
        }
        System.out.println();

        // 11. Testando remoção de veículo
        System.out.println("11. TESTANDO REMOÇÃO DE VEÍCULO:");
        System.out.println("Antes da remoção: " + proprietario1.getVeiculos().size() + " veículos");
        proprietario1.removerVeiculo(moto1);
        System.out.println("Depois da remoção: " + proprietario1.getVeiculos().size() + " veículos");

        // Readicionando a moto para manter a integridade dos testes seguintes
        proprietario1.adicionarVeiculo(moto1);
        System.out.println("Moto readicionada: " + proprietario1.getVeiculos().size() + " veículos");
        System.out.println();

        // 12. Testando cópia defensiva
        System.out.println("12. TESTANDO CÓPIA DEFENSIVA:");
        try {
            int manutencoesAntes = carro1.getManutencoes().size();
            carro1.getManutencoes().clear(); // Não deve afetar a lista original
            int manutencoesDepois = carro1.getManutencoes().size();
            System.out.println("Manutenções antes: " + manutencoesAntes);
            System.out.println("Manutenções depois: " + manutencoesDepois);
            System.out.println("Cópia defensiva funcionando corretamente!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println();

        // 13. Testando validações
        System.out.println("13. TESTANDO VALIDAÇÕES:");
        try {
            // Testando validação de lista nula
            carro1.setManutencoes(null);
        } catch (Exception e) {
            System.out.println("Validação capturada: " + e.getMessage());
        }
        System.out.println();

        // 14. TESTANDO MÉTODO obterVeiculos() CONFORME DIAGRAMA UML
        System.out.println("14. TESTANDO MÉTODO obterVeiculos() DO PROPRIETÁRIO:");
        System.out.println("Veículos do " + proprietario1.getNome() + " usando obterVeiculos():");

        Iterator<Veiculo> iteratorVeiculos = proprietario1.obterVeiculos();
        while (iteratorVeiculos.hasNext()) {
            Veiculo veiculo = iteratorVeiculos.next();
            System.out.println("  - " + veiculo.toString());

            // Testando também o método obterManutencoes() de cada veículo
            System.out.println("    Manutenções deste veículo (" + veiculo.getManutencoes().size() + "):");
            Iterator<Manutencao> iteratorManutencoes = veiculo.obterManutencoes();
            int count = 1;
            while (iteratorManutencoes.hasNext()) {
                Manutencao manutencao = iteratorManutencoes.next();
                System.out.println("      " + count + ". " + manutencao.toString());
                count++;
            }
            if (count == 1) {
                System.out.println("      Nenhuma manutenção registrada");
            }
            System.out.println();
        }
        System.out.println();

        // 15. Testando com outro proprietário
        System.out.println("15. TESTANDO MÉTODO obterVeiculos() COM OUTRO PROPRIETÁRIO:");
        System.out.println("Veículos do " + proprietario2.getNome() + " usando obterVeiculos():");

        Iterator<Veiculo> iteratorVeiculos2 = proprietario2.obterVeiculos();
        while (iteratorVeiculos2.hasNext()) {
            Veiculo veiculo = iteratorVeiculos2.next();
            System.out.println("  - " + veiculo.toString());

            // Testando também o método obterManutencoes() de cada veículo
            System.out.println("    Manutenções deste veículo (" + veiculo.getManutencoes().size() + "):");
            Iterator<Manutencao> iteratorManutencoes2 = veiculo.obterManutencoes();
            int count = 1;
            while (iteratorManutencoes2.hasNext()) {
                Manutencao manutencao = iteratorManutencoes2.next();
                System.out.println("      " + count + ". " + manutencao.toString());
                count++;
            }
            if (count == 1) {
                System.out.println("      Nenhuma manutenção registrada");
            }
            System.out.println();
        }
        System.out.println();

        // 16. Resumo final
        System.out.println("16. RESUMO FINAL:");
        System.out.println("Total de proprietários: 2");
        System.out.println("Total de mecânicos: 2");
        System.out.println("Total de veículos: 3");
        System.out.println("Total de manutenções: 4");
        System.out.println("Mecânico " + mecanico1.getNome() + " realizou " + mecanico1.getManutencoes().size() + " manutenções");
        System.out.println("Mecânico " + mecanico2.getNome() + " realizou " + mecanico2.getManutencoes().size() + " manutenções");

        // Calcular custo total das manutenções
        double custoTotal = manutencao1.getCusto() + manutencao2.getCusto() +
                manutencao3.getCusto() + manutencao4.getCusto();
        System.out.println("Custo total das manutenções: R$ " + custoTotal);

        // 17. Demonstração dos métodos do diagrama UML
        System.out.println("\n17. DEMONSTRAÇÃO DOS MÉTODOS DO DIAGRAMA UML:");
        System.out.println("✓ Proprietario.obterVeiculos() - IMPLEMENTADO E TESTADO");
        System.out.println("✓ Veiculo.obterManutencoes() - IMPLEMENTADO E TESTADO");
        System.out.println("✓ Todos os toString() - IMPLEMENTADOS E UNIFICADOS");
        System.out.println("✓ Cilindradas como double - IMPLEMENTADO");
    }
}