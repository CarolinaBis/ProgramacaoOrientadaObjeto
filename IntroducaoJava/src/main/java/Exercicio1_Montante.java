/*1. Faça um programa em Java que calcule o montante acumulado em um
investimento. O usuário deverá fornecer como entradas: capital aplicado, taxa de
juros anual (formato decimal) e o tempo da aplicação (em anos).*/

import java.util.Scanner;

public class Exercicio1_Montante {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o capital aplicado: ");
        double capital = entrada.nextDouble();

        System.out.println("Digite a taxa de juros anual: ");
        double juros = entrada.nextDouble();

        System.out.println("Digite o tempo de aplicacao em anos: ");
        int tempo = entrada.nextInt();

        double montante = capital * Math.pow(1 + juros, tempo);

        System.out.printf("O montante acumulado após %d anos é: R$ %.2f%n", tempo, montante);

        entrada.close();
    }
}