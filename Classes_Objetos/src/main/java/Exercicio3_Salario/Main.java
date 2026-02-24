/* Implemente a classe Funcionario com os atributos nome e salario.*/

package Exercicio3_Salario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Funcionario[] funcionarios = new Funcionario[5];
        double soma = 0;
        double media = 0;

        for(int i = 0; i < 5; i++){
            System.out.println("Digite o nome do funcionário: ");
            String nome = entrada.nextLine();
            System.out.println("Digite o salário do funcionário: ");
            double salario = entrada.nextDouble();
            entrada.nextLine(); // Limpa o buffer do teclado

            soma += salario;
            funcionarios[i] = new Funcionario(nome, salario);
        }

        media = soma / 5;

        /* Calcule a média dos salários.*/
        System.out.println("\nMédia dos salários: R$ " + media);
        System.out.println("\nFuncionários com salário acima da média:");

        // Mostrar quem ganha acima da média
        for(Funcionario indiceFuncionario : funcionarios) {
            if (indiceFuncionario.getSalario() > media) {
                System.out.println(indiceFuncionario.getNome());
            }
        }

        /*Reajuste o salário dos funcionários. Para aqueles que estão abaixo da média
        salarial, dê um reajuste de 10%. Para quem está acima ou igual a média, o reajuste é
        de apenas 5%*/
        System.out.println("\nREAJUSTE SALARIAL");
        // Segundo: aplicar os reajustes
        for(Funcionario indiceFuncionario : funcionarios) {
            double salarioAntigo = indiceFuncionario.getSalario();

            if (salarioAntigo < media) {
                indiceFuncionario.atualizarSalario(10);
                System.out.printf("%s: R$ %.2f → R$ %.2f (+10%%)\n",
                        indiceFuncionario.getNome(), salarioAntigo, indiceFuncionario.getSalario());
            } else {
                indiceFuncionario.atualizarSalario(5);
                System.out.printf("%s: R$ %.2f → R$ %.2f (+5%%)\n",
                        indiceFuncionario.getNome(), salarioAntigo, indiceFuncionario.getSalario());
            }
        }

    }
}