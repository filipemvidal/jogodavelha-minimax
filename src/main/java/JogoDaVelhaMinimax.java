import jogos.*;
import java.util.Scanner;

public class JogoDaVelhaMinimax {

    public static void main(String[] args) 
    {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("-------------");
        System.out.println("JOGO DA VELHA");
        System.out.println("-------------");
        System.out.println("Escolha o modo de jogo: ");
        System.out.println("[1] Jogador VS Jogador ");
        System.out.println("[2] Jogador VS Maquina ");
        
        char carac;
        //faz a validacao do caracter
        do{
            System.out.print("--> ");
            carac = teclado.nextLine().strip().toUpperCase().charAt(0);
            if(!(carac=='1' || carac=='2'))
                System.out.println("ERRO: caracter invalido. Por favor tente novamente.");
        }while(!(carac=='1' || carac=='2'));
        
        JogoPVE jogopve = new JogoPVE();
        
        switch(carac){
            case '1' -> JogoPVP.jogar();
            case '2' -> jogopve.jogar();
        }
    }
}
