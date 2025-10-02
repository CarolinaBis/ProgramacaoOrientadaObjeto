/*4. Escreva um programa em Java que receba 3 números inteiros representando as
medidas dos lados de um triângulo.
O programa deverá verificar se os números lidos formam um triângulo.
Dado um triângulo com lados a, b e c, esse triângulo somente existirá se:
a + b > c
a + c > b
b + c > a
Se os números lidos formarem um triângulo, o programa deverá mostrar qual seu tipo:
Equilátero (3 lados iguais), Isósceles (2 lados iguais) ou Escaleno (3 lados diferentes)*/

import java.util.Scanner;

public class Exercicio4_Triangulo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=== ANALISE DE TRIANGULO ===");
        System.out.println("-----------------------------");

        System.out.print("Digite o lado A do triangulo: ");
        double a = entrada.nextDouble();

        System.out.print("Digite o lado B do triangulo: ");
        double b = entrada.nextDouble();

        System.out.print("Digite o lado C do triangulo: ");
        double c = entrada.nextDouble();

        System.out.println("\nResultado da analise:");
        System.out.println("-----------------------------");

        if(a + b > c && a + c > b && b + c > a){
            System.out.println("Os lados formam um triangulo");

            if (a == b && b == c){
                System.out.println("Equilatero");
            } else if (a != b && b != c && a != c){
                System.out.println("Escaleno");
            } else {
                System.out.println("Isosceles");
            }
        } else {
            System.out.println("Os lados não formam um triangulo");
        }

    }
}
