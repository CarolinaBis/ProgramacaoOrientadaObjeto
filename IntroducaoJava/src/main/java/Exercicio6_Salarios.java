/*6. Faça um programa que leia números reais positivos correspondentes
aos salários de N (fornecido pelo usuário) funcionários de uma empresa.
Em seguida, o programa deve calcular a média desses salários e reajustálos da seguinte forma:
- salários acima da média receberão reajuste de 5%
- salários abaixo da média receberão reajuste de 10%*/

import java.util.Scanner;

public class Exercicio6_Salarios {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite a quantidade de funcionarios da empresa: ");
        int n = entrada.nextInt();

        double[] vetorSalarios = new double[n];
        double soma = 0;

        for(int i = 0; i < n; i++){
            System.out.print("Digite o salario do funcionario " + (i + 1) + ": R$ ");
            vetorSalarios[i] = entrada.nextDouble();
            soma += vetorSalarios[i];
        }

        double media = soma / n;
        System.out.printf("\nSalario medio: R$ %.2f%n", media);
        System.out.println("\nNovos salarios apos reajuste:");
        System.out.println("-----------------------------");

        int numeroFuncionario = 1;
        for(double salario : vetorSalarios){
            double novoSalario;
            if(salario > media){
                novoSalario = salario * 1.05; // 5% de aumento
                System.out.printf("Funcionario %d: R$ %.2f -> R$ %.2f (acima da media +5%%)%n",
                        numeroFuncionario, salario, novoSalario);
            } else {
                novoSalario = salario * 1.10; // 10% de aumento
                System.out.printf("Funcionario %d: R$ %.2f -> R$ %.2f (abaixo da media +10%%)%n",
                        numeroFuncionario, salario, novoSalario);
            }
            numeroFuncionario++;
        }

    }
}
