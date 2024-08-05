package components;

import java.util.Scanner;

/**
 * Classe jogador que guarda seu nome, seu caracter 'X' ou 'O' e realiza as jogadas
 * @author filipe
 */
public class Jogador {
    protected String nome;
    protected char caracter;
    
    /**
     * Define o nome do jogador
     * @param str nome do jogador
     */
    public void setNome(String str)
    {
        nome = str;
    }
    
    /**
     * 
     * @return String - nome do jogador 
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * Define o caracter usado pelo jogador
     * @param c 'X' ou 'O' (caso <b>c</b> seja diferente de 'X' ou 'O', define o caracter como 'X')
     */
    public void setCaracter(char c)
    {
        if(c=='X' || c=='O')
            this.caracter = c;
        else
            this.caracter = 'X';
    }
    
    /**
     * 
     * @return char - caracter do jogador ('X' ou 'O') 
     */
    public char getCaracter()
    {
        return caracter;
    }
    
    /**
     * Realiza a jogada de um jgador, pedindo a posicao (lin col) 
     * e fazendo a verificacao
     * @param tab tabuleiro em que se esta jogando
     */
    public void jogada(Tabuleiro tab)
    {
        Scanner teclado = new Scanner(System.in);
        System.out.println(nome + ", eh a sua vez de jogar.");
        
        Posicao pos = new Posicao();
        
        //verifica se a posicao ja foi preenchida
        do{
            //verifica se o input eh valido
            do{
            System.out.print("Posicao (lin col): ");
            pos.setLin(teclado.nextInt());
            pos.setCol(teclado.nextInt());
            if(pos.getLin()<0 || pos.getLin()>2 || pos.getCol()<0 || pos.getCol()>2)
                System.out.println("ERRO: posicao invalida. Tente novamente.");
            }while(pos.getLin()<0 || pos.getLin()>2 || pos.getCol()<0 || pos.getCol()>2);
        
            if(tab.posicaoPreenchida(pos))
                System.out.println("ERRO: posicao ja preenchida. Tente novamente.");
        }while(tab.posicaoPreenchida(pos));
        
        tab.set(pos, caracter);
    }
}
