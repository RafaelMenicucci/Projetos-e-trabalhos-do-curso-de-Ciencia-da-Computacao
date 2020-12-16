/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisor_de_grupos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class Divisor {

    int[][] resultado;
    Grafoabstract Grafo;
    int cont = 0;
    Score s;
    double valor_grupos[] = new double[7];
    double valor_total = 0;
    int linha = 0;
    int linha2 = 0;
    int pos = 0;
    int pos2 = 0;
    int score = 0;
    int aux = 0;

    Divisor(int[][] resultado, Grafoabstract Grafo) {
        this.resultado = resultado;
        this.Grafo = Grafo;
    }

    public int Dividir(Score s,int r) {
        cont=0;
        Random numero = new Random(r);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                resultado[i][j] = cont;
                cont++;
            }
        }
        s = new Score(resultado,Grafo);
        for (int i = 0; i < 1000; i++) {
            linha = numero.nextInt(7);
            linha2 = numero.nextInt(7);
            while (linha == linha2) {
                linha2 = numero.nextInt(7);
            }
            if (linha == 6) {
                pos = numero.nextInt(4);
            } else {
                pos = numero.nextInt(5);
            }
            if (linha2 == 6) {
                pos2 = numero.nextInt(4);
            } else {
                pos2 = numero.nextInt(5);
            }

            valor_grupos[0] = s.grupo1();
            valor_grupos[1] = s.grupo2();
            valor_grupos[2] = s.grupo3();
            valor_grupos[3] = s.grupo4();
            valor_grupos[4] = s.grupo5();
            valor_grupos[5] = s.grupo6();
            valor_grupos[6] = s.grupo7();
            for (int k = 0; k < valor_grupos.length; k++) {
                valor_total = valor_total + valor_grupos[k];
            }
            score = (int) valor_total;
            valor_total=0;

            aux = resultado[linha][pos];
            resultado[linha][pos] = resultado[linha2][pos2];
            resultado[linha2][pos2] = aux;

            valor_grupos[0] = s.grupo1();
            valor_grupos[1] = s.grupo2();
            valor_grupos[2] = s.grupo3();
            valor_grupos[3] = s.grupo4();
            valor_grupos[4] = s.grupo5();
            valor_grupos[5] = s.grupo6();
            valor_grupos[6] = s.grupo7();
            for (int k = 0; k < valor_grupos.length; k++) {
                valor_total = valor_total + valor_grupos[k];
            }

            if (score > (int) valor_total) {
                aux = resultado[linha][pos];
                resultado[linha][pos] = resultado[linha2][pos2];
                resultado[linha2][pos2] = aux;
            }
            valor_total=0;

        }

        valor_grupos[0] = s.grupo1();
        valor_grupos[1] = s.grupo2();
        valor_grupos[2] = s.grupo3();
        valor_grupos[3] = s.grupo4();
        valor_grupos[4] = s.grupo5();
        valor_grupos[5] = s.grupo6();
        valor_grupos[6] = s.grupo7();
        for (int k = 0; k < valor_grupos.length; k++) {
            valor_total = valor_total + valor_grupos[k];
        }
        return (int) valor_total;
    }

}
