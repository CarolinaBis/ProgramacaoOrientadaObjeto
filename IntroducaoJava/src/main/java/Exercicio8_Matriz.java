/*8. Faça um programa que leia uma matriz 4X4 de números inteiros e
mostre:*/
import java.util.Scanner;

public class Exercicio8_Matriz {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int[][] matriz = new int[4][4];

        // Leitura da matriz
        for(int linha = 0; linha < 4; linha++) {
            for(int coluna = 0; coluna < 4; coluna++) {
                System.out.print("Digite o valor para [" + linha + "][" + coluna + "]: ");
                matriz[linha][coluna] = entrada.nextInt();
            }
        }

        System.out.println("\n=== RESULTADOS ===");

        // I) Soma da diagonal principal
        int somaDiagonal = 0;
        for(int i = 0; i < 4; i++) {
            somaDiagonal += matriz[i][i];
        }
        System.out.println("I) Soma da diagonal principal: " + somaDiagonal);

        // II) Quantidade de números pares por linha
        System.out.println("\nII) Quantidade de números pares por linha:");
        for(int linha = 0; linha < 4; linha++) {
            int contadorPares = 0;
            for(int coluna = 0; coluna < 4; coluna++) {
                if(matriz[linha][coluna] % 2 == 0) {
                    contadorPares++;
                }
            }
            System.out.println("   Linha " + linha + ": " + contadorPares + " números pares");
        }

        // III) Média dos números por coluna
        System.out.println("\nIII) Média dos números por coluna:");
        for(int coluna = 0; coluna < 4; coluna++) {
            double somaColuna = 0;
            for(int linha = 0; linha < 4; linha++) {
                somaColuna += matriz[linha][coluna];
            }
            double mediaColuna = somaColuna / 4.0;
            System.out.println("   Coluna " + coluna + ": " + mediaColuna);
        }

        entrada.close();
    }
}