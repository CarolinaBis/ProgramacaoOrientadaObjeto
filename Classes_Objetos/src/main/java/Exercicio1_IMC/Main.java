/*Implementar a classe Paciente, com os atributos nome, peso e altura.*/

package Exercicio1_IMC;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o nome do paciente: ");
        String nome = entrada.nextLine();

        System.out.println("Digite o peso do paciente: ");
        double peso = entrada.nextDouble();

        System.out.println("Digite a altura do paciente: ");
        double altura = entrada.nextDouble();

        Paciente paciente = new Paciente(nome, peso, altura);

        double imc = paciente.calcularIMC();
        paciente.classificacaoIMC(imc);

    }
}
