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
public class Forca_Bruta {

    static int cont = 0;

    static int[] p;

    static int[][] resultado;

    static int score = 0;

    static int scorefinal = -1000;

    static int fim = 0;

    static int conta = 0;

    public int permuta(int[] vet, Score s) {

        p = new int[vet.length];
        permuta(vet, 0, s);
        return scorefinal;
    }

    private static void permuta(int[] vet, int n, Score s) {

        if (n == vet.length) {
            cont++;
            imprime(s);

        } else {

            for (int i = 0; i < vet.length; i++) {

                boolean achou = false;

                for (int j = 0; j < n; j++) {

                    if (p[j] == vet[i]) {
                        achou = true;
                    }
                }

                if (!achou) {

                    p[n] = vet[i];
                    permuta(vet, n + 1, s);
                }

            }

        }

    }

    static void imprime(Score s) {
        /*System.out.println();
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }*/

        for (int i = 0;i < resultado.length;i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                resultado[i][j] = p[conta];
                conta++;
            }
        }
        conta = 0;

        score = s.grupo(0);
        score = score + s.grupo(1);
        //score = score + s.grupo(2);

        if (score > scorefinal) {
            scorefinal = score;
        }
        //System.out.println("\t"+score);
        score = 0;
        /*if(scorefinal==180){
            System.out.println("MELHOR GRUPO");
            for(int i = 0;i<resultado.length;i++){
                for(int j = 0;j<resultado[i].length;j++){
                    System.out.println(resultado[i][j]);
                }
            }
        }*/

    }

    public Forca_Bruta(int p[], int[][] resultado, Score s) {
        this.p = p;
        this.resultado = resultado;

    }
}
