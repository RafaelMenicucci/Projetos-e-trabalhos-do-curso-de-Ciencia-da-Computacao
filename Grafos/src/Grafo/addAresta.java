/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;

/**
 *
 * @author rafa_
 */
public class addAresta {

    Grafoabstract Grafo;
    String rep=null;
    LinkedList<Float> desenho = new LinkedList();

    public Grafoabstract add(Grafoabstract Grafo, String nome,String rep) throws IOException {
        this.Grafo = Grafo;
        String nomeARQ;
        this.rep = rep;
        ArrayList<Float> vertices = new ArrayList();
        
        if("BFS".equals(rep)||"DFS".equals(rep)){
            int maior = 0;
            nomeARQ = nome;

            //File CSV = new File("/home/a16016/NetBeansProjects/"+nomeARQ);
            File CSV = new File(nomeARQ);

            BufferedReader conteudoCSV = null;
            String linha = "";
            String csvSeparadorCampo = ",";
            try {

                conteudoCSV = new BufferedReader(new FileReader(CSV));

                conteudoCSV.readLine();

                while ((linha = conteudoCSV.readLine()) != null) {
                    if (linha.equals("")) {
                        break;
                    }

                    String[] grafo = linha.split(csvSeparadorCampo);

                    int i = Integer.parseInt(grafo[0]);
                    int j = Integer.parseInt(grafo[1]);

                    this.Grafo.addAresta(i, j, 1);

                }

            } catch (FileNotFoundException e) {

            }
            return this.Grafo;
        }else if("kruskal".equals(rep)||"prim".equals(rep)||"Bellman-Ford".equals(rep)||"Dijkstra".equals(rep)){
            nomeARQ = nome;
            float x = 0;
            float y = 0;
            int count = 0;

            //File CSV = new File("/home/a16016/NetBeansProjects/"+nomeARQ);
            File CSV = new File(nomeARQ);

            BufferedReader conteudoCSV = null;
            String linha = "";
            String csvSeparadorCampo = ",";
            try {

                conteudoCSV = new BufferedReader(new FileReader(CSV));

                conteudoCSV.readLine();

                while ((linha = conteudoCSV.readLine()) != null) {
                    if (linha.equals("")) {
                        break;
                    }
                    count = 1;
                    String[] grafo = linha.split(csvSeparadorCampo);
                    
                    x = Float.parseFloat(grafo[0]);
                    y = Float.parseFloat(grafo[1]);
                    
                    vertices.add(x);
                    vertices.add(y);                   
                }

            } catch (FileNotFoundException e) {

            }
            }
        float x1 = 0;
        float x2 = 0;
        float y1 = 0;
        float y2 = 0;
        float distancia = 0;
        int vertice = 0;
        int vertice2 = 0;
        int j = 0;
        int i = 0;
        for(i=0;i<=vertices.size()-2;i = i+2){
            x1 = vertices.get(i);
            y1 = vertices.get(i+1);
            for(j = 0;j<=vertices.size()-2;j = j+2){
                x2 = vertices.get(j);
                y2 = vertices.get(j+1);
                distancia = (float) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
                this.Grafo.addAresta(vertice, vertice2, distancia);
                vertice2 = vertice2 +1;
            }
            
            desenho.add(x1);
            desenho.add(y1);
            
            vertice = vertice +1;
            vertice2 = 0;
        }
        return this.Grafo;
    }
    
    public LinkedList getDesenho(){
        return desenho;
    }
}
