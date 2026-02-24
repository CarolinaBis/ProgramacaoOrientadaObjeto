/* Implemente a classe Funcionario com os atributos nome e salario.  Implemente */

package Exercicio3_Salario;

public class Funcionario {
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {
        this.setNome(nome);
        this.setSalario(salario);
    }

    /*Implemente também o método atualizarSalario(double porcentagem)*/
    public void atualizarSalario(double porcentagem){
        this.salario += this.salario * (porcentagem/100);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}

