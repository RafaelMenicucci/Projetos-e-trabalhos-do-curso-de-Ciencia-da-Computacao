/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author rafael
 */
public class Ford_Fukersson {
    Grafoabstract Grafo;
    String rep = null;
    int origem = 0;
    int destino = 0;
    int[]d;
    int[]pi;
    double[]c;
    double[] [] f;
    double[]cResidual;
    int[]vertices;
    int numero=0;
    
    public Ford_Fukersson(Grafoabstract Grafo, String rep, int origem, int destino){
        this.Grafo = Grafo;
        this.rep = rep;
        this.origem = origem;
        this.destino = destino;
        this.numero = Grafo.getVertices();
        this.c = new double [this.numero];
        this.f = new double[numero][numero];
        for(int i = 0 ; i<this.f.length; i++){
            for(int j = 0; j<this.f[i].length; j++){
                this.f[i][j] = 0;
            }
        }
        this.cResidual = new double [this.numero];
        this.vertices = new int [this.numero];
        this.pi = new int [this.numero];
        this.d = new int [this.numero];
    }
    
    public void Ford_FukerssonGrafo(){
        BFS bfs = new BFS(Grafo, origem);
        pi = bfs.getPi();
        d = bfs.getD();
        
        
    }
}
