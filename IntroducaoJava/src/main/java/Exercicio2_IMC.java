/*2. Faça um programa em Java para calcular o índice de massa corporal de uma
pessoa de acordo com a fórmula e apresentar a sua classificação*/

import java.util.Scanner;

public class Exercicio2_IMC {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o peso (kg): ");
        double peso = entrada.nextDouble();

        System.out.println("Digite a altura (m): ");
        double altura = entrada.nextDouble();

        double imc = peso / Math.pow(altura, 2);

        System.out.printf("Seu IMC é: %.2f%n", imc);
        System.out.print("Classificação: ");

        if (imc < 18.5) {
            System.out.println("Abaixo do peso");
        } else if (imc < 25) {
            System.out.println("Peso normal");
        } else if (imc < 30) {
            System.out.println("Sobrepeso");
        } else if (imc < 35) {
            System.out.println("Obesidade grau I");
        } else if (imc < 40) {
            System.out.println("Obesidade grau II");
        } else {
            System.out.println("Obesidade grau III");
        }
    }
}