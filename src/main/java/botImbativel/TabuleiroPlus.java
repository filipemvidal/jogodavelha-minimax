package botImbativel;

import components.Posicao;
import components.Tabuleiro;
import java.util.List;
import java.util.ArrayList;
import jogos.JogoPVE;

/**
 * Sublclasse de Tabuleira que implementa os metodos necessarios para o bot imbativel
 * @author filip
 */
public class TabuleiroPlus extends Tabuleiro{
    private char caracJog;
    private char caracBot;
    
    /**
     * 
     * @param c1 caracter do jogador
     * @param c2 caracter do bot
     */
    public TabuleiroPlus(char c1, char c2)
    {
        caracJog = c1;
        caracBot = c2;
    }
    
    /**
     * Construtor de copia
     * @param outro 
     */
    public TabuleiroPlus(TabuleiroPlus outro)
    {
        for(int i=0; i<3; i++)
            System.arraycopy(outro.tab[i], 0, this.tab[i], 0, 3);
        this.posPreench = new ArrayList<>(outro.posPreench);
        this.caracBot = outro.caracBot;
        this.caracJog = outro.caracJog;
    }
    
    /**
     * Faz a avaliacao do tabuleiro
     * @param depth profundidade da arvore de decisoes
     * @return valor positivo se o bot ganhou, valor negativo se o jogador ganhou, 0 se empatou
     */
    protected int evaluate(int depth)
    {
        if(ganhou(caracBot))
            return 10 - depth;
        else if(ganhou(caracJog))
            return -10 + depth;
        else
            return 0;
    }
    
    /**
     * 
     * @return lista com as posicoes disponiveis 
     */
    protected List<Posicao> PosRest()
    {        
        List<Posicao> posicoes = new ArrayList<>();
        
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                Posicao pos = new Posicao();
                pos.set(i, j);
                if(get(pos) == ' ')
                {
                    posicoes.add(pos);
                }
                    
            }
        }
        return posicoes;
    }
    
    /**
     * 
     * @param pos nova´posicao a ser preenchida
     * @return novo tabuleiro com a posicao <i>pos</i> preenchida
     */
    private TabuleiroPlus novoTab(Posicao pos)
    {
        TabuleiroPlus novo = new TabuleiroPlus(this);
        int tam = posPreench.size();
        
        boolean inverte = JogoPVE.getInverte();
        
        if((tam % 2 == 0 && !inverte) || (tam % 2 != 0 && inverte))
            novo.set(pos, caracJog);
        else
            novo.set(pos, caracBot);
            
        return novo;
    }
    
    /**
     * 
     * @return lista com todos os proximos tabuleiros posiveis na proxima jogada 
     */
    protected List<TabuleiroPlus> tabsPossiveis()
    {
        List<TabuleiroPlus> tabs = new ArrayList<>();
        for(Posicao pos : PosRest())
        {
            tabs.add(novoTab(pos));
        }
        
        return tabs;
    }
    
    /**
     * 
     * @return <i>true</i> se o jogo terminou, <i>false</i> se não 
     */
    protected boolean terminou()
    {
        return posPreench.size() == 9 || this.ganhou(caracJog) || this.ganhou(caracBot);
    }
}
