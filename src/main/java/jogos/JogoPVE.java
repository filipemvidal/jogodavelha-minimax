package jogos;

import botImbativel.Bot;
import botImbativel.TabuleiroPlus;
import java.util.Random;

/**
 * Implementa a classe abstrata jogo como um jogo contra um bot imbativel
 * @author filip
 */
public class JogoPVE extends Jogo{
    private static boolean inverte;
    
    /**
     * Aleatoriza quem vai começar o jogo
     */
    public JogoPVE()
    {
        Random rand = new Random();
        inverte = rand.nextBoolean(); 
    }
    
    /**
     * 
     * @return <i>true</i> se o bot comeca, <i>false</i> se o jogador comeca
     */
    public static boolean getInverte()
    {
        return inverte;
    }
    
    /**
     * Inicia o jogo contra o bot
     */
    public void jogar()
    {
        Bot bot = new Bot();
        preencheJog1();
        if(jog1.getCaracter() == 'X')
            bot.setCaracter('O');
        else
            bot.setCaracter('X');
        
        if(!inverte)
            System.out.println("Voce comeca.");
        else
            System.out.println("O computador comeca.");

        TabuleiroPlus tab = new TabuleiroPlus(jog1.getCaracter(), bot.getCaracter());
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
                bot.jogada(tab);
                ganhou = tab.ganhou(bot.getCaracter());
                tab.imprime();
                if (ganhou) {
                    System.out.println("Voce PERDEU!");
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
            System.out.print("Deseja continuar jogando? [S/N] --> ");
            carac = teclado.nextLine().strip().toUpperCase().charAt(0);
            if (!(carac == 'S' || carac == 'N')) {
                System.out.println("ERRO: caracter invalido. Por favor tente novamente.");
            }
        } while (!(carac == 'S' || carac == 'N'));
        if (carac == 'S')
            jogar();
    }
}
