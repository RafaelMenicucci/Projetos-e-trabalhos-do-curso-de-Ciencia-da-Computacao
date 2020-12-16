/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

/**
 *
 * @author 2016.1.08.016
 */
public class JogoDaVelha {

    public static void main(String[] args) {
        String [][] jogo = new String[3][3];
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                jogo[i][j]="";
            }
        }
        
        Jogar jogar = new Jogar(jogo);
        jogo = jogar.Executa();
        System.out.println("JOGO FINALIZADO");
        if(jogar.getJogador()==1){
            System.out.println("O computador Ganhou");
        }else if(jogar.getJogador()==2){
            System.out.println("Você ganhou");
        }
        if(jogar.getJogador()==3){
            System.out.println("Jogo deu velha.");
        }
        System.out.println("------------");
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j <jogo.length; j++) {
                System.out.print(" | "+jogo[i][j]);
            }
            System.out.print(" |");
            System.out.println("");
            System.out.print("------------");
            System.out.println("");
        }
        
    }
    
}
