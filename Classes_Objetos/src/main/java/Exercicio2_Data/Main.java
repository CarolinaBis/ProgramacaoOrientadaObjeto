/*Implemente a classe Data com os atributos inteiro, dia, mes e ano.*/

package Exercicio2_Data;

public class Main {
    public static void main(String[] args) {
        // Criando algumas datas
        Data data1 = new Data(15, 5, 2023);
        Data data2 = new Data(20, 5, 2023);
        Data data3 = new Data(10, 4, 2023);

        System.out.println("Data 1:");
        data1.formatarData();

        System.out.println("Data 2:");
        data2.formatarData();

        System.out.println("Data 3:");
        data3.formatarData();

        // Comparando datas usando método de instância
        System.out.println("\nComparação data1 com data2: " + data1.compararDatas(data2));
        System.out.println("Comparação data1 com data3: " + data1.compararDatas(data3));

        // Comparando datas usando método estático
        System.out.println("Comparação estática data1 e data2: " + Data.compararDatas(data1, data2));

        // Calculando diferença entre anos
        System.out.println("Diferença em anos entre data1 e data3: " + data1.calcularDiferenca(data3));

        // Testando validação
        Data dataInvalida = new Data(32, 13, 2023); // Data inválida
        dataInvalida.formatarData();

    }
}
