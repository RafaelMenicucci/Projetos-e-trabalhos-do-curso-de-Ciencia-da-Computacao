/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class Sudoku {


    public static void main(String[] args) throws IOException {
        long tempoInicio = System.currentTimeMillis();
        String arq = null;
        int tamanho = args.length;
        int[][] matrizSudoku  = new int[9][9];

        for (int i = 0; i < tamanho - 1; i++) {
            if ("-arq".equals(args[i])) {
                arq = args[i + 1];
            }
        }

        for (int i = 0; i < 95; i++) {
            Grafoabstract MA = new Grafo_MA(81);

            addAresta add = new addAresta();
            matrizSudoku = add.add(MA, arq,i);
            int[][] resultados = new int[9][9];
            PSR p = new PSR(MA,matrizSudoku);
            System.out.println("SUDOKU: "+i);
            resultados = p.resolva(0,matrizSudoku);
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    System.out.print(resultados[j][k]+" ");
                }
                System.out.println("");
            }
            
         
            System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
            tempoInicio = System.currentTimeMillis();
            System.out.println("");
        }

    }

}
