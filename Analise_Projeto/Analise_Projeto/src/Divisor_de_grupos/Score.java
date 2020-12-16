/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Divisor_de_grupos;

/**
 *
 * @author rafael
 */
public class Score {

    int i = 0;
    int j = 0;
    int pessoa = 0;
    int pessoa2 = 0;
    int valor = 0;
    double valor1 = 0;
    double valor2 = 0;
    double valor3 = 0;
    double valor4 = 0;
    double valor5 = 0;
    double valor6 = 0;
    double valor7 = 0;
    int resultado[][];
    Grafoabstract Grafo;

    Score(int[][] resultado, Grafoabstract Grafo) {
        this.resultado = resultado;
        this.Grafo = Grafo;
    }

    Score() {

    }

    public int grupo(int x) {
        if (x == 1) {
            valor = 0;
            for (i = 0; i < 5; i++) {
                pessoa = resultado[x][i];
                for (j = 0; j < 5; j++) {
                    pessoa2 = resultado[x][j];
                    valor = (int) (valor + Grafo.getAresta(pessoa, pessoa2));
                }
            }
        } else {
            valor = 0;
            for (i = 0; i < 5; i++) {
                pessoa = resultado[x][i];
                for (j = 0; j < 5; j++) {
                    pessoa2 = resultado[x][j];
                    valor = (int) (valor + Grafo.getAresta(pessoa, pessoa2));
                }
            }
        }
        return valor;
    }

    public double grupo1() {
        valor1 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[0][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[0][j];
                valor1 = valor1 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor1;
    }

    public double grupo2() {
        valor2 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[1][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[1][j];
                valor2 = valor2 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor2;
    }

    public double grupo3() {
        valor3 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[2][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[2][j];
                valor3 = valor3 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor3;
    }

    public double grupo4() {
        valor4 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[3][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[3][j];
                valor4 = valor4 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor4;
    }

    public double grupo5() {
        valor5 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[4][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[4][j];
                valor5 = valor5 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor5;
    }

    public double grupo6() {
        valor6 = 0;
        for (i = 0; i < 5; i++) {
            pessoa = resultado[5][i];
            for (j = 0; j < 5; j++) {
                pessoa2 = resultado[5][j];
                valor6 = valor6 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor6;
    }

    public double grupo7() {
        valor7 = 0;
        for (i = 0; i < 4; i++) {
            pessoa = resultado[6][i];
            for (j = 0; j < 4; j++) {
                pessoa2 = resultado[6][j];
                valor7 = valor7 + Grafo.getAresta(pessoa, pessoa2);
            }
        }
        return valor7;
    }
}
