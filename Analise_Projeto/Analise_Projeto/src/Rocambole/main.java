/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rocambole;

import java.io.IOException;

/**
 *
 * @author rafael
 */
public class main {
    public static void main(String[] args){
        int tamanho = args.length;
        String ordem = null;
        int numero = 0;
        int x= 0;
        int y = 0;
        
        for(int i = 0; i<tamanho-1;i++){
            if("-ordem".equals(args[i])){
                ordem = args[i+1];
            }else if("-numero".equals(args[i])){
                numero = Integer.parseInt(args[i+1]);
            }
        }
        
        Resultado result;
        if(null != ordem)switch (ordem) {
            case "n":
                Ordem_n n = new Ordem_n(numero);
                x=n.getX();
                y=n.getY();
                result = new Resultado(numero,x,y);
                break;
            case "1":
                Ordem_1 um = new Ordem_1(numero);
                x=um.getX();
                y=um.getY();
                result = new Resultado(numero,x,y);
                break;
            case "raiz_n":
                Ordem_raiz_n raiz_n = new Ordem_raiz_n(numero);
                x=raiz_n.getX();
                y=raiz_n.getY();
                result = new Resultado(numero,x,y);
                break;
            default:
                break;
        }
        
    }
}
