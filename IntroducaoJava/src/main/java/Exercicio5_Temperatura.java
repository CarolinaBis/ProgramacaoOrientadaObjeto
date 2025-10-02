/*5. Faça um programa que leia números referentes à temperatura diária de
uma cidade durante uma semana. Na sequência, escreva quantos dias
dessa semana a temperatura esteve acima da média.*/

import java.util.Scanner;

public class Exercicio5_Temperatura {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        double[] vetorTemperaturas = new double[7];
        double soma = 0;
        int contador = 0;

        for (int i = 0; i < 7; i++) {
            System.out.print("Digite a temperatura do " + (i + 1) + "º dia: ");
            vetorTemperaturas[i] = entrada.nextDouble();
            soma += vetorTemperaturas[i];
        }

        double media = soma / 7;

        for (double temperaturaDia : vetorTemperaturas) {
            if (temperaturaDia > media)
                contador++;
        }

        System.out.printf("Temperatura media da semana: %.2f%n", media);
        System.out.println("Dias com temperatura acima da media: " + contador);

    }
}