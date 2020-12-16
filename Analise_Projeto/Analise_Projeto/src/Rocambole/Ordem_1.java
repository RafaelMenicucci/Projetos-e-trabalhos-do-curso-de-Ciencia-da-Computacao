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
public class Ordem_1 {
    
    int x = 0;
    int y = 0;
    int numero = 0;
    int posição=0;
    int coordenada =0;
    int raiz = 0;
    
    Ordem_1(int numero) {
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
        if(raiz%2==0){
            coordenada = (int) (raiz/2);
            x = coordenada*-1;
            y = coordenada*-1;
            posição = (int) ((x+y-1)*-raiz);
        }else{
            coordenada = (int) ((raiz+1)/2);
            x = coordenada;
            y = coordenada;
            posição = (int) raiz*(x+y);
        }
        
        if(raiz%2==0){
            if(posição>numero){
                y=y+(posição-numero);
            }else if(posição<numero){
                x=x+(numero-posição);
            }
        }else{
            if(posição>numero){
                y=y-(posição-numero);
            }else if (posição<numero){
                x=x-(numero-posição);
            }
        }
        
    }
    
}
