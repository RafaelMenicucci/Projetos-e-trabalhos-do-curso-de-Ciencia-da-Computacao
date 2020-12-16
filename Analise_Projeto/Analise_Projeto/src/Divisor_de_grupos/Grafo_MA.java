/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisor_de_grupos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author a16016
 */
public final class Grafo_MA extends Grafoabstract {
    
    double[][] mat = null;
    int vert=0;
    
    public Grafo_MA(int vertices){
        super.representacao = Representacao.MATRIZ_ADJACENCIA;
        this.criarGrafo(vertices);
        this.vert=vertices;
    }

    @Override
    public void criarGrafo(int numeroDeVertices) {
        this.mat = new double[numeroDeVertices][numeroDeVertices];
        for(int i = 0 ; i<this.mat.length; i++){
            for(int j = 0; j<this.mat[i].length; j++){
                this.mat[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        this.mat[origem][destino] = peso;
    }

    @Override
    public void setAresta(int origem, int destino, double peso) {
        if(this.mat[origem][destino] != Double.POSITIVE_INFINITY){
            this.mat[origem][destino] = peso;
        }else{
            System.out.println("Não existe a aresta desejada");
        }
    }

    @Override
    public double getAresta(int origem, int destino) {
        return (this.mat[origem][destino]);
    }

    @Override
    public void removeAresta(int origem, int destino) {
        this.mat[origem][destino] = Double.POSITIVE_INFINITY;
    }
    
    @Override
    public int getNumAresta() {
        int i = this.mat.length;
        int j = this.mat[0].length;
        int y;
        int k;
        int count=0;
        for(y=0;y<i;y++){
            for(k=0;k<j;k++){
                if(mat[y][k] != Double.POSITIVE_INFINITY){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Representacao getRepsentacaoComputacional() {
        return Representacao.MATRIZ_ADJACENCIA;
    }

    @Override
    public List<Integer> getAdjacentes(int vertice) {
        LinkedList<Integer> adj = new LinkedList<Integer>();
        int count=0;
        int j = this.mat[vertice].length;
        for(int i = 0;i<j;i++){
            if(mat[vertice][i] != Double.POSITIVE_INFINITY){
                adj.add(i);
                count++;
            }
        }
        return adj;
    }

    @Override
    public int getVertices() {
        return this.vert;
    }
    

}
