/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.LinkedList;
import java.awt.*; 

/**
 *
 * @author rafael
 */
public class CaminhoMinimo {
    Grafoabstract Grafo;
    String rep = null;
    int vertInicial = 0;
    int[]S;
    double[]d;
    int[]pi;
    int[]vertices;
    int numero=0;
    int u=0;
    
    public CaminhoMinimo(Grafoabstract Grafo, String rep, int vert){
        this.Grafo = Grafo;
        this.rep = rep;
        this.vertInicial = vert;
        this.numero = Grafo.getVertices();
        this.d = new double [this.numero];
        this.pi = new int [this.numero];
        this.vertices = new int [this.numero];
        this.S = new int [Grafo.getVertices()];
    }
    
    public void CaminhoMinimoGrafo(){ 
        for(int i=0;i<Grafo.getVertices();i++){
            d[i] = Double.POSITIVE_INFINITY;
            pi[i] = (int)Double.POSITIVE_INFINITY;
        }
        d[vertInicial] = 0;
        
        for(int j=0;j<Grafo.getVertices()-1;j++){
            for(int k=0;k<Grafo.getVertices();k++){
                if(j!=k){
                    Relaxa(j,k,Grafo.getAresta(j, k));
                }
            }
        }
        retorna();
        
        
        
    }
    
    public boolean retorna(){
        for(int j=0;j<Grafo.getVertices()-1;j++){
            for(int k=0;k<Grafo.getVertices();k++){
                if(j!=k){
                    if(d[k]>d[j]+Grafo.getAresta(j, k)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void CaminhoMinimoGrafo2(){ 
        LinkedList<Integer> Q = new LinkedList();
        for(int i=0;i<Grafo.getVertices();i++){
            d[i] = Double.POSITIVE_INFINITY;
            pi[i] = (int)Double.POSITIVE_INFINITY;
        }
        d[vertInicial] = 0;
       
        for(int j =0;j<Grafo.getVertices();j++){
            Q.add(j);
        }
        int count=0;
        while(!Q.isEmpty()){
            u = (int)extrairMinimo(Q);
            S[count]=u;
            for (Integer adjacente : Grafo.getAdjacentes(u)){
                Relaxa(u,adjacente,Grafo.getAresta(u, adjacente));
            }
        }
        
    }
    
    private Object extrairMinimo(LinkedList<Integer> Q) {
        Object menor=Double.POSITIVE_INFINITY;
        for(int k = 0;k<Q.size();k++){
            menor = Q.get(k);
        }
        for(int i = 0;i<d.length;i++){
            if(Q.contains(i)){
                if(d[(int)menor]>d[i]){
                    menor = i;
                }   
            }
        }
        Q.remove(menor);
        return menor;
    }
    
    public void Relaxa(int u,int v, double w){
        if (d[v]>(d[u]+w)){
            d[v] = (d[u]+w);
            pi[v] = u;
        }
    }
    
    public int[] getpi(){
        return pi;
    }
    public int[] getvertice(){
        for(int i=0;i<Grafo.getVertices();i++){
            vertices[i]=i;
        }
        return vertices;
    }
    
}
