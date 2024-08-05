package jogos;

import components.Jogador;
import components.Tabuleiro;
import java.util.Random;

/**
 * Implementa a classe abstrata jogo como um jogo entre dois jogadores
 * @author filipe
 */
public class JogoPVP extends Jogo {
    private static Jogador jog2 = new Jogador();
    
    /**
     * Preenche as informacoes do jogador 2 pedindo seu nome
     */
    protected static void preencheJog2()
    {
        System.out.print("Nome do jogador 2: ");
        String nome = teclado.nextLine().strip();
        jog2.setNome(nome);
        if(jog1.getCaracter()=='X')
            jog2.setCaracter('O');
        else
            jog2.setCaracter('X');
    }
    
    /**
     * Inicia o jogo entre 2 jogadores
     */
    public static void jogar()
    {   
        preencheJog1();
        preencheJog2();
        
        Random rand = new Random();
        boolean inverte = rand.nextBoolean();
        if(!inverte)
            System.out.println(jog1.getNome() + ", voce comeca.");
        else
            System.out.println(jog2.getNome() + ", voce comeca.");
        
        Tabuleiro tab = new Tabuleiro();        
        tab.imprime();

        //loop que faz a alternacia entre jogadas
        boolean ganhou;
        int i = 0;
        do {
            if ((i % 2 == 0 && !inverte) || (i % 2 != 0 && inverte)) {
                jog1.jogada(tab);
                ganhou = tab.ganhou(jog1.getCaracter());
                tab.imprime();
                if (ganhou) {
                    System.out.println("Parabens " + jog1.getNome() + " voce VENCEU!");
                }
            } else {
                jog2.jogada(tab);
                ganhou = tab.ganhou(jog2.getCaracter());
                tab.imprime();
                if (ganhou) {
                    System.out.println("Parabens " + jog2.getNome() + " voce VENCEU!");
                }
            }
            i++;
            if (i == 9 && !ganhou) {
                System.out.println("DEU VELHA! Ninguem ganhou. :(");
            }
        } while (!ganhou && i < 9);
        
        tab.imprimeJogadas();
        tab.limpa();

        //verfica se os jogadores querem continuar jogando
        char carac;
        do {
            System.out.print("Desejam continuar jogando? [S/N] --> ");
            carac = teclado.nextLine().strip().toUpperCase().charAt(0);
            if (!(carac == 'S' || carac == 'N')) {
                System.out.println("ERRO: caracter invalido. Por favor tente novamente.");
            }
        } while (!(carac == 'S' || carac == 'N'));
        if (carac == 'S')
            jogar();
    }
}
