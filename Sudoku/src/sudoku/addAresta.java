/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;

/**
 *
 * @author rafa_
 */
public class addAresta {

    Grafoabstract Grafo;
    String rep = null;
    LinkedList<Float> desenho = new LinkedList();
    private int[][] matrizSudoku  = new int[9][9];
    

    public int[][] add(Grafoabstract Grafo, String nome,int contador) throws IOException {
        this.Grafo = Grafo;
        String nomeARQ;
        this.rep = rep;
        ArrayList<Float> vertices = new ArrayList();
        int cont = 0;
        int linhamatriz = 0;

        int maior = 0;
        nomeARQ = nome;

        File CSV = new File(nomeARQ);

        BufferedReader conteudoCSV = null;
        String linha = "";
        String csvSeparadorCampo = " ";
        try {

            conteudoCSV = new BufferedReader(new FileReader(CSV));
            contador = contador*10;
            if(contador==0){
                conteudoCSV.readLine();
            }else{
                for(int x=0;x<contador+1;x++){
                    conteudoCSV.readLine();
                }
            }
            
            

            while ((linha = conteudoCSV.readLine()) != null) {
                if (linha.equals("")) {
                    addArestas(matrizSudoku, Grafo);
                    break;
                }
                String[] grafo = linha.split(csvSeparadorCampo);

                for (int i = 0; i < grafo.length; i++) {
                    matrizSudoku[linhamatriz][i] = Integer.parseInt(grafo[i]);
                }
                linhamatriz++;

            }

        } catch (FileNotFoundException e) {

        }
        return this.matrizSudoku;

    }

    public void addArestas(int[][] matrizSudoku, Grafoabstract Grafo) {
        int posicao = 0;
        for (int i = 0; i < matrizSudoku.length; i++) {
            for (int j = 0; j < matrizSudoku[0].length; j++) {
                for (int k = 0; k < matrizSudoku.length; k++) {
                    Grafo.addAresta(posicao, i * 9 + k, 1);
                }
                for (int k = 0; k < matrizSudoku.length; k++) {
                    Grafo.addAresta(posicao, j + 9 * k, 1);
                }
                if (i < 3) {
                    if (j < 3) {
                        for (int r = 0; r < 3; r++) {
                            for (int s = 0; s < 3; s++) {
                                Grafo.addAresta(posicao,r*9+s, 1);
                            }
                        }
                    } else if (j < 6) {
                        for (int r = 0; r < 3; r++) {
                            for (int s = 3; s < 6; s++) {
                                Grafo.addAresta(posicao,r*9+s , 1);
                            }
                        }
                    } else {
                        for (int r = 0; r < 3; r++) {
                            for (int s = 6; s < 9; s++) {
                                Grafo.addAresta(posicao,r*9+s , 1);
                            }
                        }
                    }
                } else if (i < 6) {
                    if (j < 3) {
                        for (int r = 3; r < 6; r++) {
                            for (int s = 0; s < 3; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    } else if (j < 6) {
                        for (int r = 3; r < 6; r++) {
                            for (int s = 3; s < 6; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    } else {
                        for (int r = 3; r < 6; r++) {
                            for (int s = 6; s < 9; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    }
                } else {
                    if (j < 3) {
                        for (int r = 6; r < 9; r++) {
                            for (int s = 0; s < 3; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    } else if (j < 6) {
                        for (int r = 6; r < 9; r++) {
                            for (int s = 3; s < 6; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    } else {
                        for (int r = 6; r < 9; r++) {
                            for (int s = 6; s < 9; s++) {
                                Grafo.addAresta(posicao, r*9+s, 1);
                            }
                        }
                    }
                }
                posicao++;
            }
        }
    }

}
