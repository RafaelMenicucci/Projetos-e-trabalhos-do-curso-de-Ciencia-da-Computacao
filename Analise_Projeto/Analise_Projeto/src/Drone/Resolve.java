/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drone;

import static java.lang.Math.exp;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class Resolve {

    int raio = 0;
    int troca1 = 0;
    int troca2 = 0;
    float aux = 0;
    float[] x = new float[1000];
    float[] y = new float[1000];
    LinkedList pordnv = new LinkedList();
    float[] resposta = new float[2000];
    int cont = 0;
    int ponto = 0;
    LinkedList japassou = new LinkedList();
    double distancia = 0;
    double distancia2 = 1000;
    double distancia3 = 0;
    double distancia4 = 0;
    double distancia5 = 0;
    double distanciatotal = 999999999;
    int ponto2 = 0;
    int[] troca = new int[2000];
    float[] raioxy;
    int contmelhorou = 0;
    int contmelhorouSimulated = 0;
    int tipo = 0;
    int size = 0;
    public static double T = 1;
    static final double Tmin = .0001;
    static final double alpha = 0.9;
    static final int numIterations = 100000;
    double ap = 0;

    Resolve(float[] x, float[] y, float[] raioxy, int tipo, int size) {
        this.x = x;
        this.y = y;
        this.raio = raio;
        resposta[0] = x[0];
        resposta[1] = y[0];
        pordnv.add(0);
        japassou.add(0);
        this.raioxy = raioxy;
        for (int i = 0; i < size; i++) {
            troca[i] = i;
        }
        this.tipo = tipo;
        this.size = size;
    }

    public float[] caminha() {
        while (cont < size * 2 - 2) {
            ponto = calculadist(x[ponto2], y[ponto2]);
            resposta[cont + 2] = x[ponto];
            resposta[cont + 3] = y[ponto];
            cont = cont + 2;
        }
        Desenho d = new Desenho(x, y, raioxy, resposta, size,contmelhorou,contmelhorouSimulated);

        Random numero = new Random();

        // Continues annealing until reaching minimum 
        // temprature 
        troca1 = 0;
        troca2 = 0;
        ///////////////////

        while (T > Tmin) {
            for (int k = 0; k < numIterations; k++) {

                aux = resposta[troca1];
                resposta[troca1] = resposta[troca2];
                resposta[troca2] = aux;

                aux = resposta[troca1 + 1];
                resposta[troca1 + 1] = resposta[troca2 + 1];
                resposta[troca2 + 1] = aux;

                aux = x[troca1 / 2];
                x[troca1 / 2] = x[troca2 / 2];
                x[troca2 / 2] = aux;

                aux = y[troca1 / 2];
                y[troca1 / 2] = y[troca2 / 2];
                y[troca2 / 2] = aux;

                aux = raioxy[troca1 / 2];
                raioxy[troca1 / 2] = raioxy[troca2 / 2];
                raioxy[troca2 / 2] = aux;

                for (int i = 0; i < size * 2 - 3; i = i + 2) {
                    distancia5 = distancia5 + Math.sqrt(((resposta[i + 2] - resposta[i]) * (resposta[i + 2] - resposta[i])) + ((resposta[i + 3] - resposta[i + 1]) * (resposta[i + 3] - resposta[i + 1])));
                }
                distancia5 = distancia5 + Math.sqrt(((resposta[0] - resposta[size - 1]) * (resposta[0] - resposta[size - 1])) + ((resposta[1] - resposta[size - 2]) * (resposta[1] - resposta[size - 2])));
                if (distanciatotal > distancia5) {
                    distanciatotal = distancia5;
                    //System.out.println("MELHOROU " + contmelhorou + " VEZES;");
                    contmelhorou++;
                    d.repaint();
                } else {
                    aux = resposta[troca2];
                    resposta[troca2] = resposta[troca1];
                    resposta[troca1] = aux;

                    aux = resposta[troca2 + 1];
                    resposta[troca2 + 1] = resposta[troca1 + 1];
                    resposta[troca1 + 1] = aux;

                    aux = x[troca2 / 2];
                    x[troca2 / 2] = x[troca1 / 2];
                    x[troca1 / 2] = aux;

                    aux = y[troca2 / 2];
                    y[troca2 / 2] = y[troca1 / 2];
                    y[troca1 / 2] = aux;

                    aux = raioxy[troca2 / 2];
                    raioxy[troca2 / 2] = raioxy[troca1 / 2];
                    raioxy[troca1 / 2] = aux;

                    //ap = Math.pow(Math.E, (distanciatotal - distancia5) / T);
                    if (ap < Math.random()) {
                        aux = resposta[troca1];
                        resposta[troca1] = resposta[troca2];
                        resposta[troca2] = aux;

                        aux = resposta[troca1 + 1];
                        resposta[troca1 + 1] = resposta[troca2 + 1];
                        resposta[troca2 + 1] = aux;

                        aux = x[troca1 / 2];
                        x[troca1 / 2] = x[troca2 / 2];
                        x[troca2 / 2] = aux;

                        aux = y[troca1 / 2];
                        y[troca1 / 2] = y[troca2 / 2];
                        y[troca2 / 2] = aux;

                        aux = raioxy[troca1 / 2];
                        raioxy[troca1 / 2] = raioxy[troca2 / 2];
                        raioxy[troca2 / 2] = aux;

                        distanciatotal = distancia5;
                        //System.out.println("MELHOROU " + contmelhorouSimulated + " VEZES;SIMULATED");
                        contmelhorouSimulated++;
                        d.repaint();
                    }

                    distancia5 = 0;
                    troca1 = numero.nextInt((size * 2) - 1);
                    troca2 = numero.nextInt((size * 2) - 1);
                    while (troca1 % 2 != 0) {
                        troca1 = numero.nextInt((size * 2) - 1);
                    }
                    while (troca2 % 2 != 0) {
                        troca2 = numero.nextInt((size * 2) - 1);
                    }
                    ap = ap + 0.01;
                }
            }

            T *= alpha; // Decreases T, cooling phase 
        }
        System.out.println("Melhorou:Simulated: " + contmelhorouSimulated);
        System.out.println("Melhorou: " + contmelhorou);

        ///////////////////
        /*for (int k = 0; k < 1000000; k++) {
            aux = resposta[troca1];
            resposta[troca1] = resposta[troca2];
            resposta[troca2] = aux;

            aux = resposta[troca1 + 1];
            resposta[troca1 + 1] = resposta[troca2 + 1];
            resposta[troca2 + 1] = aux;

            aux = x[troca1 / 2];
            x[troca1 / 2] = x[troca2 / 2];
            x[troca2 / 2] = aux;

            aux = y[troca1 / 2];
            y[troca1 / 2] = y[troca2 / 2];
            y[troca2 / 2] = aux;

            aux = raioxy[troca1 / 2];
            raioxy[troca1 / 2] = raioxy[troca2 / 2];
            raioxy[troca2 / 2] = aux;

            for (int i = 0; i < size * 2 - 3; i = i + 2) {
                distancia5 = distancia5 + Math.sqrt(((resposta[i + 2] - resposta[i]) * (resposta[i + 2] - resposta[i])) + ((resposta[i + 3] - resposta[i + 1]) * (resposta[i + 3] - resposta[i + 1])));
            }
            distancia5 = distancia5 + Math.sqrt(((resposta[0] - resposta[size - 1]) * (resposta[0] - resposta[size - 1])) + ((resposta[1] - resposta[size - 2]) * (resposta[1] - resposta[size - 2])));
            if (distanciatotal > distancia5) {
                distanciatotal = distancia5;
                System.out.println("MELHOROU " + contmelhorou + " VEZES;");
                contmelhorou++;
                d.repaint();
            } else {
                aux = resposta[troca2];
                resposta[troca2] = resposta[troca1];
                resposta[troca1] = aux;

                aux = resposta[troca2 + 1];
                resposta[troca2 + 1] = resposta[troca1 + 1];
                resposta[troca1 + 1] = aux;

                aux = x[troca2 / 2];
                x[troca2 / 2] = x[troca1 / 2];
                x[troca1 / 2] = aux;

                aux = y[troca2 / 2];
                y[troca2 / 2] = y[troca1 / 2];
                y[troca1 / 2] = aux;

                aux = raioxy[troca2 / 2];
                raioxy[troca2 / 2] = raioxy[troca1 / 2];
                raioxy[troca1 / 2] = aux;

            }
            distancia5 = 0;
            troca1 = numero.nextInt((size * 2) - 1);
            troca2 = numero.nextInt((size * 2) - 1);
            while (troca1 % 2 != 0) {
                troca1 = numero.nextInt((size * 2) - 1);
            }
            while (troca2 % 2 != 0) {
                troca2 = numero.nextInt((size * 2) - 1);
            }
        }

        for (int i = 1; i < size * 2 - 3; i = i + 2) {
            //cima
            distancia = Math.sqrt(((resposta[i + 2] - resposta[i] - raioxy[i / 2] / 2) * (resposta[i + 2] - resposta[i] - raioxy[i / 2] / 2)) + ((resposta[i + 1] - resposta[i + 3]) * (resposta[i + 1] - resposta[i + 3])));
            //baixo
            distancia2 = Math.sqrt(((resposta[i + 2] - resposta[i] + raioxy[i / 2] / 2) * (resposta[i + 2] - resposta[i] + raioxy[i / 2] / 2)) + ((resposta[i + 1] - resposta[i + 3]) * (resposta[i + 1] - resposta[i + 3])));
            //esquerda
            distancia3 = Math.sqrt(((resposta[i + 1] - raioxy[i / 2] / 2 - resposta[i - 1]) * (resposta[i + 1] - raioxy[i / 2] / 2 - resposta[i - 1])) + ((resposta[i] - resposta[i + 2]) * (resposta[i] - resposta[i + 2])));
            //direita
            distancia4 = Math.sqrt(((resposta[i + 1] + raioxy[i / 2] / 2 - resposta[i - 1]) * (resposta[i + 1] + raioxy[i / 2] / 2 - resposta[i - 1])) + ((resposta[i] - resposta[i + 2]) * (resposta[i] - resposta[i + 2])));

            if (distancia < distancia2) {
                if (distancia < distancia3) {
                    if (distancia < distancia4) {
                        resposta[i] = resposta[i] + raioxy[i / 2] / 2;
                    } else {
                        resposta[i - 1] = resposta[i - 1] - raioxy[i / 2] / 2;
                    }
                } else {
                    if (distancia3 < distancia4) {
                        resposta[i - 1] = resposta[i - 1] + raioxy[i / 2] / 2;
                    } else {
                        resposta[i - 1] = resposta[i - 1] - raioxy[i / 2] / 2;
                    }

                }

            } else {
                if (distancia2 < distancia3) {
                    if (distancia2 < distancia4) {
                        resposta[i] = resposta[i] - raioxy[i / 2] / 2;
                    } else {
                        resposta[i - 1] = resposta[i - 1] - raioxy[i / 2] / 2;
                    }
                } else {
                    if (distancia3 < distancia4) {
                        resposta[i - 1] = resposta[i - 1] + raioxy[i / 2] / 2;
                    } else {
                        resposta[i - 1] = resposta[i - 1] - raioxy[i / 2] / 2;
                    }
                }
            }
            distancia = 0;
            distancia2 = 0;
            distancia3 = 0;
            distancia4 = 0;
        }*/
        ///////////
        for (int i = 0; i < size * 2 - 3; i = i + 2) {
            distancia = distancia + Math.sqrt(((resposta[i + 2] - resposta[i]) * (resposta[i + 2] - resposta[i])) + ((resposta[i + 3] - resposta[i + 1]) * (resposta[i + 3] - resposta[i + 1])));
        }
        distancia = distancia + Math.sqrt(((resposta[0] - resposta[size * 2 - 2]) * (resposta[0] - resposta[size * 2 - 2])) + ((resposta[1] - resposta[size * 2 - 1]) * (resposta[1] - resposta[size * 2 - 1])));
        d.setDistancia(distancia);
        d.setcont(contmelhorou);
        d.setcontSimulated(contmelhorouSimulated);
        d.repaint();

        return resposta;
    }

    private int calculadist(float f, float f0) {
        for (int i = 1; i < size; i++) {
            if (!japassou.contains(i)) {
                japassou.add(i);
                distancia = Math.sqrt(((x[i] - f) * (x[i] - f)) + ((y[i] - f0) * (y[i] - f0)));
                if (distancia2 > distancia) {
                    distancia2 = distancia;
                    ponto2 = i;
                }
            }
        }
        distancia2 = 100000;
        pordnv.add(ponto2);
        japassou.clear();
        for (int i = 0; i < pordnv.size(); i++) {
            japassou.add(pordnv.get(i));
        }
        return ponto2;
    }

}
