/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class PSR {

    Grafoabstract Grafo;
    int[][] erro = new int[9][9];
    int[][] matrizSudoku = new int[9][9];
    int[][] recebe = new int[9][9];

    public PSR(Grafoabstract MA, int[][] matrizSudoku) {
        Grafo = MA;
        this.matrizSudoku = matrizSudoku;

        for (int i = 0; i < erro.length; i++) {
            for (int j = 0; j < erro[i].length; j++) {
                erro[i][j] = 0;
            }
        }
    }

    public LinkedList<Integer> criaDominio(int vertice, int[][] matrizSudoku) {

        LinkedList<Integer> dom = new LinkedList();

        for (int k = 1; k < 10; k++) {
            dom.add(k);
        }
        for (Integer adjacente : Grafo.getAdjacentes(vertice)) {
            if (dom.contains((Object) matrizSudoku[adjacente / 9][adjacente % 9])) {
                dom.remove((Object) matrizSudoku[adjacente / 9][adjacente % 9]);
            }
        }

        return dom;
    }

    public int[][] resolva(int vertice, int[][] matrizSudoku) {

        if (vertice == 81) {
            return matrizSudoku;
        }

        if (matrizSudoku[vertice / 9][vertice % 9] != 0) {
            recebe = resolva(vertice + 1, matrizSudoku);
            if (recebe == erro) {
                return erro;
            } else {
                return recebe;
            }
        } else {
            LinkedList<Integer> dom = criaDominio(vertice, matrizSudoku);

            for (int i = 0; i < dom.size(); i++) {
                matrizSudoku[vertice / 9][vertice % 9] = dom.get(i);
                recebe = resolva(vertice + 1, matrizSudoku);
                if (recebe != erro) {
                    return recebe;
                } else {
                    matrizSudoku[vertice / 9][vertice % 9] = 0;
                }
            }
            return erro;
        }

    }

}
