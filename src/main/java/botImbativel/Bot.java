package botImbativel;

import components.Jogador;
import components.Posicao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Subclasse de Jogador que implementa o bot que nunca perde
 * @author filip
 */
public class Bot extends Jogador{
    
    /**
     * Define o nome do bot como "bot"
     */
    public Bot()
    {
        this.nome = "bot";
    }
    
    /**
     * Algoritmo recursivo que avalia todas as jogadas possiveis
     * @param tabuleiro tabuleiro em que se está jogando
     * @param depth profundidade da arvore de decisoes 
     * @param isMaximizing define se o algoritmo deve maximizar ou minimizar as jogadas
     * @return int - valor da melhor jogada
     */
    private int minimax(TabuleiroPlus tabuleiro, int depth, boolean isMaximizing)
    {
        if(tabuleiro.terminou())
            return tabuleiro.evaluate(depth);
        
        if(isMaximizing)
        {
            int maxEva = Integer.MIN_VALUE;
            for(TabuleiroPlus tab : tabuleiro.tabsPossiveis())
            {
                int eva = minimax(tab, depth+1, false);
                maxEva = Integer.max(maxEva, eva);
            }
            return maxEva;
        }
        else
        {
            int minEva = Integer.MAX_VALUE;
            for(TabuleiroPlus tab : tabuleiro.tabsPossiveis())
            {
                int eva = minimax(tab, depth +1, true);
                minEva = Integer.min(minEva, eva);
            }
            return minEva;
        }
    }
    
    /**
     * 
     * @param tabuleiro
     * @return lista com os valores avaliados de cada jogada 
     */
    private List<Integer> avaliaPosicoes(TabuleiroPlus tabuleiro)
    {
        List<Integer> valorPosicao = new ArrayList<>();
        
        
        for(TabuleiroPlus tab : tabuleiro.tabsPossiveis())
        {
            valorPosicao.add(minimax(tab, 0, false));
        }
        
        return valorPosicao;
    }
    
    /**
     * 
     * @param tabuleiro
     * @return Posicao - melhor proxima jogada para o bot 
     */
    private Posicao melhorJogada(TabuleiroPlus tabuleiro)
    {
        List<Integer> valorPosicao = this.avaliaPosicoes(tabuleiro);
        
        int indexMaior = valorPosicao.indexOf(Collections.max(valorPosicao));
        
        return tabuleiro.PosRest().get(indexMaior);
    }
    
    /**
     * Realiza a jogada do bot
     * @param tab 
     */
    public void jogada(TabuleiroPlus tab)
    {
        if(tab.PosRest().size() == 9)
        {
            Random rand = new Random();
            tab.set(tab.PosRest().get(rand.nextInt(9)), this.caracter);
        }
        else
        {
            Posicao melhorJog = this.melhorJogada(tab);
            System.out.println("Rodada do computador: " + melhorJog.getLin() + " " + melhorJog.getCol());
            tab.set(melhorJog, this.caracter);
        }
    }
}
