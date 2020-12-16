/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregas;

import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class Restriction {

    LinkedList<Integer> teste = new LinkedList();
    int[] id = new int[101];
    int[] x = new int[101];
    int[] y = new int[101];
    int[] demand = new int[101];
    int[] readyTime = new int[101];
    int[] closeTime = new int[101];
    double distancia = 0;
    int capacity = 0;
    int total = 0;

    public Restriction(LinkedList teste, int[] id, int[] x, int[] y, int[] demand, int[] readyTime, int[] closeTime, int capacity) {
        this.teste = teste;
        this.id = id;
        this.x = x;
        this.y = y;
        this.demand = demand;
        this.readyTime = readyTime;
        this.closeTime = closeTime;
        this.capacity = capacity;
    }

    public boolean timeRestrict() {
        boolean pode = false;
        for (int i = 0; i < teste.size()-1; i++) {
            distancia = distancia + Math.sqrt(((x[teste.get(i)] - x[teste.get(i+1)]) * (x[teste.get(i)] - x[teste.get(i+1)])) + ((y[teste.get(i)] - y[teste.get(i+1)]) * (y[teste.get(i)] - y[teste.get(i+1)])));
            if (readyTime[teste.get(i+1)] > distancia) {

                pode = true;
            }
            if (teste.size() - 2 != i) {
                distancia = distancia + 90;
            }
        }
        return pode;
    }

    public boolean capacityRestrict() {
        boolean pode = false;
        for (int i = 1; i < teste.size() - 1; i++) {
            total = total + demand[teste.get(i)];
        }
        if (total > capacity) {
            System.out.println("Não é possível realizar a rota.");
            System.out.println("Total de pedidos: " + total + "Capacidade: " + capacity);
            System.out.println("");
            pode = true;
        } else {
            //System.out.println("É possível realizar a rota.");
            //System.out.println("Total de pedidos: " + total + " Capacidade: " + capacity);
            //System.out.println("");
        }
        return pode;
    }

}
