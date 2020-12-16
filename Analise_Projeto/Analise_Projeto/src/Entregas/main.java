/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregas;

import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author rafael
 */
public class main {

    public static void main(String[] args) throws IOException {
        int tamanho = args.length;
        String csvorigem = null;
        int capacity = 0;
        int caminhao = 20;
        double distancia = 0;
        int[] serviceTime = new int[101];
        int[] id = new int[101];
        int[] x = new int[101];
        int[] y = new int[101];
        int[] demand = new int[101];
        int[] readyTime = new int[101];
        int[] closeTime = new int[101];

        for (int i = 0; i < tamanho - 1; i++) {
            if ("-arq".equals(args[i])) {
                csvorigem = args[i + 1];
            }
        }
        Leitura l = new Leitura();
        capacity = l.add(csvorigem, id, x, y, demand, readyTime, closeTime, capacity,serviceTime);
        int start = readyTime[0];
        int end = closeTime[100];

        LinkedList<LinkedList<Integer>> teste = new LinkedList();
        Resolve resolv = new Resolve(id, x, y, demand, readyTime, closeTime, capacity,serviceTime);
        teste = resolv.abre();

        for (int i = 0; i < teste.size(); i++) {
            for (int j = 0; j < teste.get(i).size(); j++) {
                System.out.print(" " + teste.get(i).get(j));
            }
            System.out.println("");
        }

    }
}
