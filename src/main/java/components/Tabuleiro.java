package components;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe tabuleiro, aramzena a matriz do tabuleiro 
 * e realiza diversas operacoes sobre ela
 * @author filipe
 */
public class Tabuleiro {
    protected char[][] tab;
    protected List<Posicao> posPreench = new ArrayList<>();
    
    /**
     * Inicializa a matriz 3x3 do tabuleiro com espacos em branco ' '
     */
    public Tabuleiro()
    {
        tab = new char[3][3]; 
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                tab[i][j] = ' ';
    }
    
    /**
     * Define uma posicao (lin, col) do tabuleiro como o caracter <b>c</b>
     * @param pos
     * @param c caracter 'X' ou 'O'
     */
    public void set(Posicao pos, char c)
    {
        if(pos.getLin()<=2 && pos.getLin()>=0 && pos.getCol()<=2 && pos.getCol()>=0 
                && (c=='X' || c=='O')){
            tab[pos.getLin()][pos.getCol()] = c;
            posPreench.add(pos);
        }
    }
    
    /**
     * Retorna o caracter de uma posicao do tabuleiro
     * @param pos
     * @return char - caracter 'X' ou 'O'
     */
    protected char get(Posicao pos)
    {
        return tab[pos.getLin()][pos.getCol()];
    }
    
    /**
     * Limpa o tabuleiro, definindo todas suas posicoes como ' '
     */
    public void limpa()
    {
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                tab[i][j] = ' ';
        posPreench.clear();
    }
    
    /**
     * Imprime o tabuleiro
     */
    public void imprime()
    {
        System.out.println();
        System.out.println("  0 1 2 ");
        for(int i=0; i<3; i++){
            System.out.print(i + " ");
            for(int j=0; j<3; j++){
                System.out.print(tab[i][j]);
                if(j<2)
                    System.out.print("|");
            }
            if(i<2)
                System.out.println("\n  -----");
            else
                System.out.println(""); 
        }
    }
    
    /**
     * Verifica se uma linha foi preenchida por um jogador
     * @param i linha
     * @param c caracter 'X' ou 'O'
     * @return boolean - <i>true</i> se a linha estiver preenchida 
     * e <i>false</i> se não estiver 
     */
    private boolean verificaL(int i, char c)
    {
        return tab[i][0]==c && tab[i][1]==c && tab[i][2]==c;
    }
    
    /**
     * Verifica se uma coluna foi preenchida por um jogador
     * @param j coluna
     * @param c caracter 'X' ou 'O'
     * @return boolean - <i>true</i> se a coluna estiver preenchida 
     * e <i>false</i> se não estiver 
     */
    private boolean verificaC(int j, char c)
    {
        return tab[0][j]==c && tab[1][j]==c && tab[2][j]==c;
    }
    
    /**
     * Verifica se alguma das diagonais foi preenchida por um jogador
     * @param c caracter 'X' ou 'O'
     * @return boolean - <i>true</i> se alguma das diagonais estiver preenchida 
     * e <i>false</i> se não estiver 
     */
    private boolean verificaD(char c)
    {
        return (tab[0][0]==c && tab[1][1]==c && tab[2][2]==c) 
                || (tab[0][2]==c && tab[1][1]==c && tab[2][0]==c);
    }
    
    /**
     * Verifica se um jogador ganhou, usando os metodos privados verificaL, verificaC e verificaD
     * @see verificaL
     * @see verificaC
     * @see verificaD
     * @param c caracter do jogador que se quer verificar
     * @return boolean - <i>true</i> se o jogador <i>jog</i> ganhou
     * e <i>false</i> se não
     */
    public boolean ganhou(char c)
    {
        return (verificaL(0, c) || 
                verificaL(1, c) ||
                verificaL(2, c) ||
                verificaC(0, c) ||
                verificaC(1, c) ||
                verificaC(2, c) ||
                verificaD(c));
    }
    
    /**
     * Informa se uma posicao ja foi preenchida ou não
     * @param pos
     * @return <i>true</i> se <i>pos</i> já preenchida, <i>false</i> se não
     */
    public boolean posicaoPreenchida(Posicao pos)
    {
        return tab[pos.getLin()][pos.getCol()] != ' ';
    }
    
    /**
     * Imprime as jogadas que foram feitas ao longo do jogo
     */
    public void imprimeJogadas()
    {
        System.out.println("\nAs jogadas realizadas foram: ");
        for(Posicao pos : posPreench)
        {
            System.out.println("(" + pos.getLin() + ", " + pos.getCol() + ")");
        }
    }
}
