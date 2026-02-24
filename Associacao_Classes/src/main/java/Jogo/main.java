package Jogo;

public class main {
        public static void main(String[] args) {
            // Criando times
            Time brasil = new Time("Brasil", 5);
            Time argentina = new Time("Argentina", 3);

            // Criando jogadores
            Jogador jogador1 = new Jogador("Neymar", "Atacante", 10);
            Jogador jogador2 = new Jogador("Messi", "Atacante", 10);
            Jogador jogador3 = new Jogador("Alisson", "Goleiro", 1);

            // Adicionando jogadores aos times
            brasil.adicionarJogador(jogador1);
            brasil.adicionarJogador(jogador3)    
            argentina.adicionarJogador(jogador2);

            // Criando estádio
            Estadio maracana = new Estadio("Maracanã", "Rio de Janeiro");

            // Criando partida
            Partida partida = new Partida("15/11/2024", "16:00", brasil, argentina, maracana);

            // Atualizando resultado
            partida.atualizarResultado(2, 1);

            // Exibindo informações da partida
            System.out.println("Partida: " + partida.getTime1().getPais() + " vs " + partida.getTime2().getPais());
            System.out.println("Data: " + partida.getData() + " às " + partida.getHora());
            System.out.println("Estádio: " + partida.getEstadio().getNome() + " - " + partida.getEstadio().getCidade());
            System.out.println("Placar: " + partida.getPlacarTime1() + " x " + partida.getPlacarTime2());

            System.out.println("\nJogadores do " + brasil.getPais() + ":");
            for (Jogador jogador : brasil.getJogadores()) {
                System.out.println("- " + jogador.getNome() + " (" + jogador.getPosicao() + ") - Camisa " + jogador.getNumeroCamisa());

        }
    }
}
