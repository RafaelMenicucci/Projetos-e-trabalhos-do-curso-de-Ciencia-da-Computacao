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
public class Ordem_n {
    
    int cont = 0;
    int numero = 0;
    int x = 0;
    int y = 0;
    int k = 1;
    int i = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    Ordem_n(int numero) {
        this.numero = numero;
        Descobrir();
    }
    
    private void Descobrir(){
        while(numero!=cont){
            for(i=0;i<k;i++){
                x=x+1;
                cont++;
                if(cont==numero){
                    break;
                }
            }
            if(cont==numero){
                    break;
                }
            for(i=0;i<k;i++){
                y=y+1;
                cont++;
                if(cont==numero){
                    break;
                }
            }
            if(cont==numero){
                    break;
                }
            k++;
            for(i=0;i<k;i++){
                x=x-1;
                cont++;
                if(cont==numero){
                    break;
                }
            }
            if(cont==numero){
                    break;
                }
            for(i=0;i<k;i++){
                y=y-1;
                cont++;
                if(cont==numero){
                    break;
                }
            }
            if(cont==numero){
                    break;
                }
            k++;
        }
    }
    
}
