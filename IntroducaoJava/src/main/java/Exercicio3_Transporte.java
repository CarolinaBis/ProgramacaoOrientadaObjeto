/*3. Um motorista de aplicativo estipula o preço de suas viagens de acordo
com a quantidade de km percorridos.
Para viagens de até X km, é cobrado um valor R$ V1 por km. Acima de X
km, é cobrado o valor R$ V2.
Faça um programa que leia X, V1, V2 e a quantidade de km percorridos (P)
e imprima o valor total com duas casas decimais.*/

import java.util.Scanner;

public class Exercicio3_Transporte {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite a quantidade de Km percorridos (P): ");
        double p = entrada.nextDouble();

        System.out.print("Digite o limite de Km para tarifa diferenciada (X): ");
        double x = entrada.nextDouble();

        System.out.print("Digite o valor por Km ate " + x + " km (V1): R$ ");
        double v1 = entrada.nextDouble();

        System.out.print("Digite o valor por Km acima de " + x + " km (V2): R$ ");
        double v2 = entrada.nextDouble();

        double valorTotal;

        if(p <= x){
            valorTotal = p * v1;
            System.out.println("\nTarifa aplicada: V1 (ate " + x + " km)");
        } else {
            valorTotal = p * v2;
            System.out.println("\nTarifa aplicada: V2 (acima de " + x + " km)");
        }

        System.out.println("Km percorridos: " + p + " km");
        System.out.printf("Valor total da viagem: %.2f R$ " ,valorTotal);

    }
}