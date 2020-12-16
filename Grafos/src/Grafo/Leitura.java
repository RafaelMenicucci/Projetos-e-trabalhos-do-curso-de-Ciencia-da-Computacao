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
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author rafa_
 */
public class Leitura {
    String rep = null;

    public int Ler(String nome, String rep) throws IOException {
        int nVertices = 0;
        if("BFS".equals(rep)||"DFS".equals(rep)){
            int maior = 0;
            String nomeARQ;
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

                    if(i>nVertices){
                        nVertices = i;
                    }
                    if(j>nVertices){
                        nVertices = j;
                    }

                }

            } catch (FileNotFoundException e) {

            }
            return (nVertices+1);
        }else if("kruskal".equals(rep)||"prim".equals(rep)||"Bellman-Ford".equals(rep)||"Dijkstra".equals(rep)){
            String nomeARQ;
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
                    nVertices = nVertices + 1;
                }

            } catch (FileNotFoundException e) {

            }
            }
        return (nVertices);
        }
}
