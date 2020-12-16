/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;  

/**
 *
 * @author rafael
 */
public class AGM {
    Grafoabstract Grafo;
    String rep = null;
    int vertInicial = 0;
    int[]X;
    int[]Y;
    int[]pi;
    int u=0;
    double[]chave;
    int numero=0;
    
    public AGM(Grafoabstract Grafo, String rep, int vert){
        this.Grafo = Grafo;
        this.rep = rep;
        this.vertInicial = vert;
        this.numero = Grafo.getVertices();
        this.X = new int [this.numero];
        this.Y = new int [this.numero];
        this.pi = new int [this.numero];
        this.chave = new double [this.numero];
    }
    
    public void AGMGrafo(){
        ArrayList<Double> A = new ArrayList();
        ArrayList<Integer> Avertice1 = new ArrayList();
        ArrayList<Integer> Avertice2 = new ArrayList();
        ArrayList<Integer> conjuntos = new ArrayList();
        if("kruskal".equals(rep)){
            
            int[]Z = null;
            
            for(int i = 0;i<Grafo.getVertices();i++){
                conjuntos.add(i);
            }
            
            for(int vertice1 = 0;vertice1<Grafo.getVertices();vertice1++){
                for(int vertice2 = vertice1+1;vertice2<Grafo.getVertices();vertice2++){
                    A.add(Grafo.getAresta(vertice1, vertice2));
                    Avertice1.add(vertice1);
                    Avertice2.add(vertice2);
                }
            }
            double guarda = 0;
            int guardavertice = 0;
            for (int l = 0; l < A.size(); ++l){
                for (int t = l + 1; t < A.size(); ++t){
                    if (A.get(l) > A.get(t)){
                        guarda = A.get(l);
                        A.add(l, A.get(t));
                        A.add(t, guarda);
                        
                        guardavertice = Avertice1.get(l);
                        Avertice1.add(l, Avertice1.get(t));
                        Avertice1.add(t, guardavertice);
                        
                        guardavertice = Avertice2.get(l);
                        Avertice2.add(l, Avertice2.get(t));
                        Avertice2.add(t, guardavertice);
                    }
                }
            }
            
            for(int k=0;k<A.size();k++){
                if(conjuntos.get(Avertice1.get(k)) != conjuntos.get(Avertice2.get(k))){
                    X[k] = Avertice1.get(k);
                    Y[k] = Avertice2.get(k);
                    //Z[k] = (Avertice1.get(k),Avertice2.get(k));
                    conjuntos.set(Avertice1.get(k),Avertice1.get(k));
                }
            }
  
        }else if("prim".equals(rep)){

            LinkedList<Integer> Q = new LinkedList();
            
            for(int i = 0;i<Grafo.getVertices();i++){
                this.chave[i] = Double.POSITIVE_INFINITY;
                pi[i] = -1;
            }
            this.chave[vertInicial] = 0;
            for(int j = 0;j<Grafo.getVertices();j++){
                Q.add(j);
            }
            int count = -1;
            while(!Q.isEmpty()){
                u = (int)extrairMinimo(Q);
                if(u!=vertInicial){
                    X[count] = u;
                    Y[count] = pi[u];
                }
                for (Integer adjacente : Grafo.getAdjacentes(u)){
                    if(Q.contains(adjacente) && Grafo.getAresta(u, adjacente)<chave[adjacente]){
                        chave[adjacente] = Grafo.getAresta(u, adjacente);
                        pi[adjacente] = u;
                    }
                }
                count++;
            }
        }
    }

    private Object extrairMinimo(LinkedList<Integer> Q) {
        Object menor=Double.POSITIVE_INFINITY;
        for(int k = 0;k<Q.size();k++){
            menor = Q.get(k);
        }
        for(int i = 0;i<chave.length;i++){
            if(Q.contains(i)){
                if(chave[(int)menor]>chave[i]){
                    menor = i;
                }   
            }
        }
        Q.remove(menor);
        return menor;
    }
    
    public int[] getX(){
        return X;
    }
    public int[] getY(){
        return Y;
    }
}
