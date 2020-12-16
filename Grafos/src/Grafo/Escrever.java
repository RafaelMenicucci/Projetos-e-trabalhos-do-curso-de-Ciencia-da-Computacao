/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rafa_
 */
public class Escrever {
    Grafoabstract Grafo;
    String i;
    int[] d;
    int[] f;
    public Escrever(String x, int[]desc, int[]fin, Grafoabstract Grafo){
        this.Grafo = Grafo;
        this.i = x;
        this.d = desc;
        this.f = fin;
    }
    public void EscreverGrafo(String saida){
        if("DFS".equals(i)){
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Vertice,Descoberto,Finalizado\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(j+ "," +d[j]+ "," +f[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else if("BFS".equals(i)){
            
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Vertice,pai,Descoberto\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(j+ "," +f[j]+ "," +d[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else if("kruskal".equals(i)){
            
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Vertice,Adjacente\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(f[j]+ "," +d[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else if("prim".equals(i)){
            double AGM = 0;
            for(int i =0;i<f.length;i++){
                AGM = AGM + Grafo.getAresta(f[i], d[i]);
            }
                
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Custo da AGM: "+AGM+"\r\n");
                arquivo.write("Vertice,Adjacente\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(f[j]+ "," +d[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else if("Bellman-Ford".equals(i)||"Dijkstra".equals(i)){
            
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Vertice,pai\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(d[j]+ "," +f[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else if("Ford".equals(i)){
            
            FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(saida));
                arquivo.write("Vertice,pai\r\n");
            for (int j = 0; j < Grafo.getVertices(); j++) {
                arquivo.write(d[j]+ "," +f[j]+ "\r\n");
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
}

