/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisor_de_grupos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author rafael
 */
public class Leitura {

    public void Ler(Grafoabstract Grafo, String nome, int[] grupos, String modelo) throws IOException {

        int cont = 0;
        int cont2 = 0;
        int k = 0;
        int r = 0;
        String nomeARQ;
        nomeARQ = nome;

        File CSV = new File(nomeARQ);

        BufferedReader conteudoCSV = null;
        String linha = "";
        String csvSeparadorCampo = "\t";
        try {

            conteudoCSV = new BufferedReader(new FileReader(CSV));
            while (cont != 2) {
                conteudoCSV.readLine();
                cont++;
            }

            while ((linha = conteudoCSV.readLine()) != null) {
                if (linha.equals("")) {
                    break;
                }

                String[] grafo = linha.split(csvSeparadorCampo);

                if (cont2 >= 0 && cont2 < 7) {
                    grupos[cont2] = Integer.parseInt(grafo[1]);
                    cont2++;
                } else {
                    for (int l = 0; l < grafo.length; l++) {
                        Grafo.addAresta(k, r, Integer.parseInt(grafo[l]));
                        r++;
                        if (r == 34) {
                            r = 0;
                            k++;
                        }
                    }
                }

            }

        } catch (FileNotFoundException e) {

        }

    }

}
