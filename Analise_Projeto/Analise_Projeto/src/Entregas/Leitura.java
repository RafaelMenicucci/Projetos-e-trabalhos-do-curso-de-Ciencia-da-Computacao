/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class Leitura {

    int cont = 0;
    int cont2 = 0;

    public int add(String nome,int[] id,int[] x,int[] y,int[] demand, int[] readyTime,int[] closeTime,int capacity,int[] serviceTime) throws IOException {

        String nomeARQ;
        ArrayList<Float> vertices = new ArrayList();

            int maior = 0;
            nomeARQ = nome;

            File CSV = new File("/home/rafael/Documentos/análise/instances/C101.txt");
            //File CSV = new File(nomeARQ);

            BufferedReader conteudoCSV = null;
            String linha = "";
            String csvSeparadorCampo = "\\s+";
            try {

                conteudoCSV = new BufferedReader(new FileReader(CSV));
                for (int i = 0; i < 8; i++) {
                    conteudoCSV.readLine();
                    if(i==3){
                        linha = conteudoCSV.readLine();
                        linha=linha.trim();
                        String[] grafo = linha.split(csvSeparadorCampo);
                        capacity = Integer.parseInt(grafo[1]);
                    }
                }
                                

                while ((linha = conteudoCSV.readLine()) != null) {
                    if (linha.equals("")) {
                        break;
                    }
                    linha=linha.trim();
                    String[] grafo = linha.split(csvSeparadorCampo);
                    
                    id[cont] = Integer.parseInt(grafo[cont2]);
                    x[cont] = Integer.parseInt(grafo[cont2+1]);
                    y[cont] = Integer.parseInt(grafo[cont2+2]);
                    demand[cont] = Integer.parseInt(grafo[cont2+3]);
                    readyTime[cont] = Integer.parseInt(grafo[cont2+4]);
                    closeTime[cont] = Integer.parseInt(grafo[cont2+5]);
                    serviceTime[cont] = Integer.parseInt(grafo[cont2+6]);
                    
                    cont++;
                }

            } catch (FileNotFoundException e) {

            }
            return capacity;
    }
}

