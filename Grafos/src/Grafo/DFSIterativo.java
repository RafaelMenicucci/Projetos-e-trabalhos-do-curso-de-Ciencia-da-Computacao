/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.List;


/**
 *
 * @author rafa_
 */
public class DFSIterativo {
    int numero=0;
    int[]cor;
    int[]d;
    int[]f;
    int tempo=0;
    Grafoabstract Grafo;
    FilaIterativa Q; 
    public DFSIterativo(Grafoabstract Grafo) {
        this.Grafo = Grafo;
        this.numero = Grafo.getVertices();
        this.cor = new int [this.numero];
        this.d = new int [this.numero];
        this.f = new int [this.numero];
        Q = new FilaIterativa();
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
        /*int cont=1;
        while(Q.tamanho() < Grafo.getVertices()){
            cor[vertice] = 2;
            tempo = tempo + 1;
            d[vertice] = tempo;
            for (Integer adjacente : Grafo.getAdjacentes(vertice)) {
                if (cor[adjacente] == 1) {
                    Q.enfileira(adjacente);
                }
                break;
            }
        }
        for (Integer adjacente : Grafo.getAdjacentes(vertice-cont)) {
                while ((Grafo.getAdjacentes(vertice)!= null)&&cor[adjacente]==1){
                    Q.enfileira(adjacente);
                    cor[adjacente] = 2;
                    tempo = tempo + 1;
                    d[adjacente] = tempo;
                }
            cont++;
        }
        cor[vertice] = 3;
        tempo = tempo + 1;
        f[vertice] = tempo;*/
        
        /*for (Integer adjacente : Grafo.getAdjacentes(vertice)) {
            if (cor[adjacente] == 1) {
                Q.enfileira(adjacente);
            }
        }
        
        while(Q.tamanho() != 0){
            int x = Q.desenfileira();
            cor[x] = 2;
            tempo = tempo + 1;
            d[x] = tempo;
            for (Integer adjacente : Grafo.getAdjacentes(x)) {
                if (cor[adjacente] == 1) {
                    Q.enfileira(adjacente);
                }
            }
            cor[x] = 3;
            tempo = tempo + 1;
            f[x] = tempo;
        }*/
        
        /*cor[vertice] = 2;
        tempo = tempo + 1;
        d[vertice] = tempo;
        
        List<Integer> adj = Grafo.getAdjacentes(vertice);
        if (cor[adj.get(0)] == 1) {
            Integer v = adj.get(0);
            Q.enfileira(v);
        }
        while(Q.tamanho() != 0){
            int x=Q.desenfileira();
            cor[x] = 2;
            tempo = tempo + 1;
            d[x] = tempo;
            for (Integer adjacente : Grafo.getAdjacentes(x)) {
                if (cor[adjacente] == 1) {
                    Q.enfileira(adjacente);
                }
                break;
            }
            cor[x] = 3;
            tempo = tempo + 1;
            f[x] = tempo;
        }
        cor[vertice] = 3;
        tempo = tempo + 1;
        f[vertice] = tempo;*/
    }
    public int[] getD(){
        return d;
    }
    public int[]getF(){
        return f;
    }
}
