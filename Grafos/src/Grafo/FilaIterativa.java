/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.LinkedList;

/**
 *
 * @author rafa_
 */
public class FilaIterativa {
    
    LinkedList<Integer> filaIterativa = new LinkedList<Integer>();
    
    void enfileira(int vert) {
        filaIterativa.addLast(vert);
    }
    public int desenfileira(){
        return filaIterativa.pollLast();
    }
    
    public int tamanho(){
        return filaIterativa.size();
    }
    
}
