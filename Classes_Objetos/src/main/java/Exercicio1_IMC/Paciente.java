package Exercicio1_IMC;

public class Paciente {
    private String nome;
    private double peso;
    private double altura;

    public Paciente(String nome, double peso, double altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    /*I) Crie o método calcularIMC(), o qual deve calcular e retonar o índice de massa corporal*/

    double calcularIMC(){
        double imc = peso/Math.pow(altura,2);

        return imc;
    }

    /*II) Crie um método para mostrar a classificação do paciente em relação ao seu IMC*/
    void classificacaoIMC(double imc){
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
