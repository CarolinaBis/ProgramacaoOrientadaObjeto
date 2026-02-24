package Exercicio2_Data;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.setDia(dia);
        this.setMes(mes);
        this.setAno(ano);
    }

    /*I) Permitir a exibição da data no formato dia/mes/ano*/
    void formatarData(){
        System.out.println(dia+"/"+mes+"/"+ano);
    }

    /*II) Possuir um método que recebe como parâmetro um objeto do tipo Data.Esse método deve comparar a data contida no objeto que invocou o método
    com a data contida no objeto passado para o método*/
    int compararDatas(Data data){
        if (this.ano>data.ano)
            return 1;
        else if (this.ano<data.ano)
            return -1;
        else if (this.mes>data.mes)
            return 1;
        else if (this.mes<data.mes)
            return -1;
        else if (this.dia>data.dia)
            return 1;
        else if (this.dia<data.dia)
            return -1;
        else return 0;

    }

    /*III) Possuir um método static que recebe como parâmetro dois objetos do tipo
    Data. Esse método deve comparar as datas*/
    public static int compararDatas(Data data1, Data data2){
        if (data1.ano>data2.ano)
            return 1;
        else if (data1.ano<data2.ano)
            return -1;
        else if (data1.mes>data2.mes)
            return 1;
        else if (data1.mes<data2.mes)
            return -1;
        else if (data1.dia>data2.dia)
            return 1;
        else if (data1.dia<data2.dia)
            return -1;
        else return 0;
    }

    /*IV) Calcular a diferença entre o ano da data contida em um objeto Data e o
    ano passado como parâmetro para o método*/
    int calcularDiferenca(Data outraData) {
        return Math.abs(this.ano - outraData.getAno());
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}



