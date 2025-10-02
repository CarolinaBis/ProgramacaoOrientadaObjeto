/*7. Faça um programa que leia um conjunto de valores que correspondem
às idades de pessoas de uma comunidade. Quando o valor fornecido for
um número negativo, significa que não existem mais idades para serem
lidas. Após a leitura, o programa deve informar:
I) A média das idades das pessoas
II) A quantidade de pessoas maiores de idade (21 anos)
III) A porcentagem de pessoas idosas (acima de 65 anos)*/

import java.util.Scanner;

public class Exercicio7_Idades {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int soma = 0;
        int quantidadePessoas = 0;
        int pessoasMaior = 0;
        int idosos = 0;
        int idade;

        System.out.println("Digite as idades (negativo para encerrar):");
        System.out.println("------------------------------------------");

        do {
            System.out.print("Idade da pessoa " + (quantidadePessoas + 1) + ": ");
            idade = entrada.nextInt();

            if(idade >= 0) {
                soma += idade;
                quantidadePessoas++;

                if(idade >= 21){
                    pessoasMaior++;
                }

                if(idade > 65){
                    idosos++;
                }
            }

        } while(idade >= 0);

        if(quantidadePessoas > 0) {
            double media = soma / quantidadePessoas;
            double porcentagemIdosos = (idosos / quantidadePessoas) * 100;

            System.out.printf("Media das idades: %.2f anos%n", media);
            System.out.println("Quantidade de pessoas maiores de idade (21+): " + pessoasMaior);
            System.out.printf("Porcentagem de pessoas idosas (65+): %.2f%%%n", porcentagemIdosos);
            System.out.println("Total de pessoas registradas: " + quantidadePessoas);
        } else {
            System.out.println("Nenhuma idade valida foi informada.");
        }

    }
}
