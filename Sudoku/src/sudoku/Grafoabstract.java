/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.List;

/**
 *
 * @author a16016
 */
public abstract class Grafoabstract {

    Representacao representacao = null;
    
    public abstract  void criarGrafo(int numerodeVertices);
    
    public abstract void addAresta(int origem, int destino, double peso);
    
    public abstract void setAresta(int origem, int destino, double peso);
    
    public abstract double getAresta(int origem, int destino);
    
    public abstract void removeAresta(int origem, int destino);
    
    public abstract int getNumAresta();
    
    public abstract List<Integer> getAdjacentes(int vertice);
    
    public abstract Representacao getRepsentacaoComputacional();
    
    public abstract int getVertices();
    
}
