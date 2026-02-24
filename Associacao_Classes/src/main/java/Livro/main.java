package Livro;

public class main {
        public static void main(String[] args) {
            // Criando autores
            Autor autor1 = new Autor("Clarice Lispector", "(11) 9999-8888");
            Autor autor2 = new Autor("Machado de Assis", "(21) 7777-6666");

            // Criando livro
            Livro livro = new Livro("Dom Casmurro", 1899, 39.90);

            // Adicionando autores ao livro
            livro.adicionarAutor(autor1);
            livro.adicionarAutor(autor2);

            // Mostrando os nomes dos autores do livro
            System.out.println("Autores do livro \"" + livro.getTitulo() + "\":");
            for (Autor autor : livro.getAutores()) {
                System.out.println("- " + autor.getNome());

        }
    }
}
