package jogos;

import components.Jogador;
import java.util.Scanner;

/**
 * Classe abstrata usada para definir alguns 
 * metodos basicos comuns entre os tipos de jogos
 * @author filipe
 */
abstract class Jogo {
    final static Scanner teclado = new Scanner(System.in);
    protected static Jogador jog1 = new Jogador();
    
    /**
     * Preenche as informacoes do jogador 1
     */
    protected static void preencheJog1()
    {
        System.out.print("Nome do jogador 1: ");
        String nome = teclado.nextLine().strip();
        jog1.setNome(nome);
        char carac;
        do{
        System.out.print(jog1.getNome() + ", qual simbolo voce deseja utilizar, X ou O? --> ");
        carac = teclado.nextLine().strip().toUpperCase().charAt(0);
        if(!(carac=='X' || carac=='O'))
            System.out.println("ERRO: caracter invalido. Por favor tente novamente.");
        }while(!(carac=='X' || carac=='O'));
        jog1.setCaracter(carac);
    }
}
