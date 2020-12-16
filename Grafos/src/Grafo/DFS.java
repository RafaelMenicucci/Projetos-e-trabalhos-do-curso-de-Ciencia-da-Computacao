/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author rafa_
 */
public class DFS {
    int numero=0;
    int[]cor;
    int[]d;
    int[]f;
    int tempo=0;
    Grafoabstract Grafo;
    public DFS(Grafoabstract Grafo) {
        this.Grafo = Grafo;
        this.numero = Grafo.getVertices();
        this.cor = new int [this.numero];
        this.d = new int [this.numero];
        this.f = new int [this.numero];
    }
   

    public void DFSgrafo() {
        for (int i = 0; i < Grafo.getVertices(); i++) {
            this.cor[i] = 1;
        }
        for (int i = 0; i < Grafo.getVertices(); i++) {
            if (cor[i] == 1) {
                DFS_Visit(i);
            }
        }
    }

    public void DFS_Visit(int vertice) {
        cor[vertice] = 2;
        tempo = tempo + 1;
        d[vertice] = tempo;
        for (Integer adjacente : Grafo.getAdjacentes(vertice)) {
            if (cor[adjacente] == 1) {
                DFS_Visit(adjacente);
            }
        }
        cor[vertice] = 3;
        tempo = tempo + 1;
        f[vertice] = tempo;
    }
    public int[] getD(){
        return d;
    }
    public int[]getF(){
        return f;
    }
}
