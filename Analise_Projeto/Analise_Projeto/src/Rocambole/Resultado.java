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
public class Resultado {
    int x = 0;
    int y = 0;
    int numero = 0;

    Resultado(int numero, int x, int y) {
        this.x = x;
        this.y = y;
        this.numero = numero;
        
        System.out.println("A coordenada do Ponto:"+this.numero+ " é ("+this.x+","+this.y+")");
    }
    
}
