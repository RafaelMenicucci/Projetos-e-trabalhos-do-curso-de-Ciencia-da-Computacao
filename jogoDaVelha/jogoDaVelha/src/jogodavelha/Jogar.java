/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 2016.1.08.016
 */
public class Jogar {

    Random gerador;
    int[] linhaColuna = new int[2];
    int cont = 0;
    int linha = 0;
    int coluna = 0;
    int jogador = 2;
    String[][] jogo = new String[3][3];
    String[] teste = new String[9];
    int velha=0;

    public Jogar(String[][] jogo) {
        gerador = new Random();
        this.jogo = jogo;
        for (int i = 0; i < teste.length; i++) {
            teste[i] = "";
        }
    }

    String[][] Executa() {
        linha = gerador.nextInt(3);
        coluna = gerador.nextInt(3);

        jogo[linha][coluna] = "X";
        teste[linha * 3 + coluna] = "X";

        while (!acabou()) {
            System.out.println("------------");
            for (int i = 0; i < jogo.length; i++) {
                for (int j = 0; j < jogo.length; j++) {
                    System.out.print(" | " + jogo[i][j]);
                }
                System.out.print(" |");
                System.out.println("");
                System.out.print("------------");
                System.out.println("");
            }
            System.out.println("");
            if (jogador == 2) {
                System.out.println("Sua vez: ");
                Scanner jogada = new Scanner(System.in);
                String recebe = jogada.nextLine();
                String[] split = recebe.split(",");
                linha = Integer.parseInt(split[0]);
                coluna = Integer.parseInt(split[1]);
                while (jogo[linha][coluna] != "") {
                    System.out.println("Posição Ocupada, escolha outra: ");
                    jogada = new Scanner(System.in);
                    recebe = jogada.nextLine();
                    split = recebe.split(",");
                    linha = Integer.parseInt(split[0]);
                    coluna = Integer.parseInt(split[1]);

                }
                jogo[linha][coluna] = "O";
                teste[linha * 3 + coluna] = "O";
                jogador = 1;
                velha++;
            } else {
                Pensa p = new Pensa(teste);
                linhaColuna=p.get();
                linha = linhaColuna[0];
                coluna = linhaColuna[1];
                while (jogo[linha][coluna] != "") {
                    linhaColuna=p.get();
                    linha = linhaColuna[0];
                    coluna = linhaColuna[1];
                }
                jogo[linha][coluna] = "X";
                teste[linha * 3 + coluna] = "X";
                jogador = 2;
                velha++;
            }
        }
        return jogo;
    }

    private boolean acabou() {
        int i = 0;

        for (int j = 0; j < 3; j++) {
            if (teste[j * 3].equals(teste[j * 3 + 1]) && teste[j * 3].equals(teste[j * 3 + 2]) && !"".equals(teste[j * 3] + teste[j * 3 + 1])) {
                return true;
            }
            if (teste[j].equals(teste[j + 3]) && teste[j].equals(teste[j + 6]) && !"".equals(teste[j] + teste[j + 3])) {
                return true;
            }
        }
        if (teste[0].equals(teste[4]) && teste[0].equals(teste[8]) && !"".equals(teste[0] + teste[4])) {
            return true;
        }
        if (teste[2].equals(teste[4]) && teste[2].equals(teste[6]) && !"".equals(teste[2] + teste[4])) {
            return true;
        }
        for (int j = 0; j < jogo.length; j++) {
            for (int k = 0; k < jogo.length; k++) {
                if ("".equals(jogo[j][k])) {
                    cont++;
                }
            }
        }
        if (cont == 0) {
            return true;
        }
        cont = 0;
        return false;
    }

    int getJogador() {
        if(velha==8){
            return 3;
        }
        if (jogador == 2) {
            return 1;
        } else {
            return 2;
        }
    }
}
