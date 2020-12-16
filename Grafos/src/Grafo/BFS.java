/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.Queue;


/**
 *
 * @author rafa_
 */
public class BFS extends Fila{
    Grafoabstract Grafo;
    int numero=0;
    int vert=0;
    int[]cor;
    int[]d;
    int[]pi;
    int u=0;

    public BFS(Grafoabstract Grafo, int verticeInicial) {
        this.Grafo = Grafo;
        this.vert = verticeInicial;
        numero = Grafo.getVertices();
        this.cor = new int [this.numero];
        this.d = new int [this.numero];
        this.pi = new int [this.numero];
    }
    
    public void BFSgrafo() {
        for (int i = 0; i < Grafo.getVertices()-vert; i++) {
            this.cor[i] = 1;
            this.d[i] = Integer.MAX_VALUE;
            this.pi[i] = 0;
        }
        cor[vert] = 2;
        d[vert] = 0;
        pi[vert] = Integer.MIN_VALUE;
        
        Fila Q = new Fila();
        Q.enfileira(vert);
        
        while(Q.tamanho() != 0){
            u = Q.desenfileira();
            for (Integer adjacente : Grafo.getAdjacentes(u)) {
                if(cor[adjacente] == 1){
                    cor[adjacente] = 2;
                    d[adjacente] = d[u]+1;
                    pi[adjacente] = u;
                    Q.enfileira(adjacente);
                }
            }
            cor[u] = 3;
        }
        
    }
    public int[] getPi(){
        return pi;
    }
    public int[] getD(){
        return d;
    }
}
