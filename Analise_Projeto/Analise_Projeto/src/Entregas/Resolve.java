/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregas;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class Resolve {

    int[] id = new int[101];
    int[] x = new int[101];
    int[] y = new int[101];
    int[] demand = new int[101];
    int[] readyTime = new int[101];
    int[] closeTime = new int[101];
    int capacity = 0;
    int[] serviceTime = new int[101];

    public Resolve(int[] id, int[] x, int[] y, int[] demand, int[] readyTime, int[] closeTime, int capacity, int[] serviceTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.demand = demand;
        this.readyTime = readyTime;
        this.closeTime = closeTime;
        this.capacity = capacity;
        this.serviceTime = serviceTime;
    }

    public LinkedList abre() {
        LinkedList<Integer> japassou = new LinkedList();
        LinkedList<LinkedList<Integer>> teste = new LinkedList();
        double distancia = 0;
        int total = 0;
        Random r = new Random();
        int casa = 0;
        int caminhao = 0;
        int capacitycaminhao = 0;
        double[] distanciacaminhao = new double[25];
        int numcasa = 0;
        int contcasa = 0;
        double distanciacaminhaototal = 0;

        for (int i = 0; i < 25; i++) {
            teste.add(new LinkedList());
        }

        teste.get(caminhao).add(0);
        japassou.add(0);
        while (japassou.size() != 101) {
            casa = r.nextInt(101);

            if (!japassou.contains(casa)) {
                japassou.add(casa);
                teste.get(caminhao).add(casa);
                numcasa++;

                distancia = distancia + Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                if (distancia < closeTime[casa]) {
                    if (distancia < readyTime[casa] && (readyTime[casa] - distancia < 10)) {
                        distancia = readyTime[casa];
                        //distancia = distancia + serviceTime[casa];
                        capacitycaminhao = capacitycaminhao + demand[casa];
                    } else if (distancia > readyTime[casa]) {
                        //distancia = distancia + serviceTime[casa];
                        capacitycaminhao = capacitycaminhao + demand[casa];
                    }

                    if (capacitycaminhao > capacity) {
                        teste.get(caminhao).removeLast();
                        japassou.removeLast();
                        teste.get(caminhao).add(0);
                        distancia = distancia + Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                        distanciacaminhao[caminhao] = distancia;
                        distancia = 0;
                        caminhao++;
                        if (caminhao == 25) {
                            System.out.println("IMPOSSIVEL");
                        }
                        capacitycaminhao = 0;
                        numcasa = 0;
                        teste.get(caminhao).add(0);
                    }

                } else {
                    if (distancia > closeTime[0]) {
                        distancia = distancia - Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                        teste.get(caminhao).removeLast();
                        japassou.removeLast();
                        teste.get(caminhao).add(0);
                        distancia = distancia + Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                        distanciacaminhao[caminhao] = distancia;
                        distancia = 0;
                        caminhao++;
                        capacitycaminhao = 0;
                        numcasa = 0;
                        teste.get(caminhao).add(0);
                    } else {
                        distancia = distancia - Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                        teste.get(caminhao).removeLast();
                        japassou.removeLast();
                        numcasa--;
                        contcasa++;
                        if (contcasa == 101) {
                            teste.get(caminhao).add(0);
                            distancia = distancia + Math.sqrt((((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)])) * ((x[teste.get(caminhao).get(numcasa - 1)] - x[teste.get(caminhao).get(numcasa)]))) + ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]) * ((y[teste.get(caminhao).get(numcasa - 1)] - y[teste.get(caminhao).get(numcasa)]))));
                            distanciacaminhao[caminhao] = distancia;
                            distancia = 0;
                            caminhao++;
                            capacitycaminhao = 0;
                            numcasa = 0;
                            teste.get(caminhao).add(0);
                            contcasa = 0;
                        }
                    }

                }
            }

        }
        for (int i = 0; i < distanciacaminhao.length; i++) {
            System.out.println("Caminhao " + i + " Distancia: " + distanciacaminhao[i]);
            distanciacaminhaototal = distanciacaminhaototal + distanciacaminhao[i];
        }
        System.out.println(distanciacaminhaototal);
        return teste;
    }

}
