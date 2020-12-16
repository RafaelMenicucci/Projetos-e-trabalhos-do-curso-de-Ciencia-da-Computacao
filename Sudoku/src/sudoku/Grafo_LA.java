/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author rafa_
 */

public final class Grafo_LA extends Grafoabstract {

    LinkedList<Listaadj>[] lista = null;
    

    
    public Grafo_LA(int vertices){
        super.representacao = Representacao.LISTA_ADJACENCIA;
        this.criarGrafo(vertices);
    }
    
    @Override
    public void criarGrafo(int numeroDeVertices) {
        this.lista = new LinkedList[numeroDeVertices];
        for(int i = 0; i < numeroDeVertices; i++) {
            this.lista[i] = new LinkedList();
        }
    }

    @Override
    public void setAresta(int origem, int destino, double peso) {
        for(int i = 0; i < this.lista[origem].size(); i++) {
            if(destino == this.lista[origem].get(i).getDestino()) {
                this.lista[origem].get(i).setPeso(peso);
            }
        }
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        this.lista[origem].add(new Listaadj(destino, peso));
    }

    @Override
    public double getAresta(int origem, int destino) {
        for(int i = 0; i < this.lista[origem].size(); i++) {
            if(destino == this.lista[origem].get(i).getDestino()) {
                return this.lista[origem].get(i).getPeso();
            }
        }
        return 0;
    }

    @Override
    public void removeAresta(int origem, int destino) {
        for(int i = 0; i < this.lista[origem].size(); i++) {
            if(destino == this.lista[origem].get(i).getDestino()) {
                this.lista[origem].remove(i);
            }
        }
    }

    @Override
    public int getNumAresta() {
        int count = 0;
        for(int i = 0; i < this.lista.length; i++) {
            for(int j = 0; j < this.lista[i].size(); j++) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public List<Integer> getAdjacentes(int i) {
        LinkedList<Integer> lista1 = new LinkedList<>();
        for(int j = 0; j < this.lista[i].size(); j++) {
            lista1.add(lista[i].get(j).getDestino());
        }
        return lista1;
    }

    @Override
    public Representacao getRepsentacaoComputacional() {
        return Representacao.LISTA_ADJACENCIA;
    }


    @Override
    public int getVertices() {
        return(this.lista.length);
    }
    
}