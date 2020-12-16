/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rocambole;

/**
 *
 * @author rafael
 */
public class Ordem_raiz_n {

    int x = 0;
    int y = 0;
    int numero = 0;
    int cont = 0;
    int valor = 0;
    int raiz = 0;

    Ordem_raiz_n(int numero) {
        this.numero = numero;
        this.raiz = (int) Math.sqrt(numero);
        Descobrir();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void Descobrir() {
        for (int i = 1; i <= raiz; i++) {
            cont = cont + 2;
            valor = valor + cont;
            if (i == 1) {
                x = 1;
                y = 1;
            } else {
                if (i % 2 == 0) {
                    x = x * (-1);
                    y = y * (-1);
                } else {
                    x = (x * (-1)) + 1;
                    y = (y * (-1)) + 1;
                }
            }
        }

        if (raiz % 2 == 0) {
            if (valor > numero) {
                y = y + (valor - numero);
            } else if (valor < numero) {
                x = x + (numero - valor);
            }
        } else {
            if (valor > numero) {
                y = y - (valor - numero);
            } else if (valor < numero) {
                x = x - (numero - valor);
            }
        }

    }

}
