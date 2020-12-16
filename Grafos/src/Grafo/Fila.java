/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafa_
 */
public class Fila {
    LinkedList<Integer> fila = new LinkedList<Integer>();
    int vertice;
    
    void enfileira(int vert) {
        fila.add(vert);
    }
    public int desenfileira(){
        return fila.poll();
    }
    
    public int tamanho(){
        return fila.size();
    }
    
    
}
