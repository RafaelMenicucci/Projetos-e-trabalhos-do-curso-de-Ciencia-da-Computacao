/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisor_de_grupos;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class main {

    public static void main(String[] args) throws IOException {

        long tempoInicio = System.currentTimeMillis();
        float porcentagem = 0;
        String arq = null;
        LinkedList<Integer> grupos20 = new LinkedList();
        LinkedList<Integer> agrup = new LinkedList();
        int tamanho = args.length;
        int grupos[] = new int[7];
        int resultado[][];
        double valor_grupos[] = new double[7];
        double valor_total = 0;
        int cont = 0;
        int cont1000 = 0;
        String modelo = null;
        for (int i = 0; i < tamanho - 1; i++) {
            if ("-arq".equals(args[i])) {
                arq = args[i + 1];
            } else if ("-modelo".equals(args[i])) {
                modelo = args[i + 1];
            }
        }

        if ("hillclimb".equals(modelo)) {
            resultado = new int[7][5];

            for (int j = 0; j < 7; j++) {
                if (j == 6) {
                    resultado[j] = new int[4];
                } else {
                    resultado[j] = new int[5];
                }
            }

            Grafoabstract MA = new Grafo_MA(34);
            Leitura l = new Leitura();
            l.Ler(MA, arq, grupos, modelo);
            Divisor d = new Divisor(resultado, MA);
            Score s = new Score(resultado, MA);
            for (int r = 0; r < 1000; r++) {
                grupos20.add(d.Dividir(s,r));
            }
            for(int i = 0;i<grupos20.size();i++){
                if(grupos20.get(i)==1080){
                    System.out.println("Seed: "+i);
                    cont++;
                }
                if(grupos20.get(i)>=1000){
                    cont1000++;
                }
            }

            System.out.println("Número de 1080: "+cont);
            System.out.println("Número de scores maiores que 1000: "+cont1000);
            
            int media = 0;
            for (int i = 0; i < grupos20.size(); i++) {
                media = media + (int) grupos20.get(i);
            }
            System.out.println("Media: "+media / grupos20.size());
            media = 0;

            System.out.println(grupos20);

            Collections.sort(grupos20);

            int mediana = 0;
            int aux = 0;

            aux = (grupos20.size() / 2);
            mediana = (int) grupos20.get(aux);
            System.out.println("Mediana: "+mediana);
            mediana = 0;

            System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
        } else if ("forcaBruta".equals(modelo)) {
            Grafoabstract MA = new Grafo_MA(34);
            Leitura l = new Leitura();
            l.Ler(MA, arq, grupos, modelo);
            resultado = new int[2][5];

            /*for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    resultado[j] = new int[8];
                } else {
                    resultado[j] = new int[7];
                }
            }*/
            Score s = new Score(resultado, MA);

            int bruto[] = new int[10];
            for (int i = 0; i < bruto.length; i++) {
                bruto[i] = i;
            }
            Forca_Bruta f = new Forca_Bruta(bruto, resultado, s);
            System.out.println("Score final\t" + f.permuta(bruto, s));
            System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
        }

    }

}
