/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.util.Random;

/**
 *
 * @author rafael
 */
public class Pensa {

    String[] teste;
    Random gerador;
    int linha = 0;
    int coluna = 0;
    int[] linhaColuna = new int[2];

    Pensa(String[] teste) {
        this.teste = teste;
        gerador = new Random();
    }

    int[] get() {
        for (int j = 0; j < 3; j++) {
            if (teste[j * 3].equals(teste[j * 3 + 1]) && !"".equals(teste[j * 3]) && "".equals(teste[j * 3+2])) {
                int r[] = {j, 2};
                return r;
            }
            if (teste[j * 3].equals(teste[j * 3 + 2]) && !"".equals(teste[j * 3]) && "".equals(teste[j * 3+1])) {
                int r[] = {j, 1};
                return r;
            }
            if (teste[j * 3 + 1].equals(teste[j * 3 + 2]) && !"".equals(teste[j * 3 + 1]) && "".equals(teste[j * 3])) {
                int r[] = {j, 0};
                return r;
            }
            if (teste[j].equals(teste[j + 3]) && !"".equals(teste[j]) && "".equals(teste[j +6])) {
                int r[] = {2, j};
                return r;
            }
            if (teste[j].equals(teste[j + 6]) && !"".equals(teste[j]) && "".equals(teste[j +3])) {
                int r[] = {1, j};
                return r;
            }
            if (teste[j + 3].equals(teste[j + 6]) && !"".equals(teste[j + 3]) && "".equals(teste[j ])) {
                int r[] = {0, j};
                return r;
            }
        }
        if (teste[0].equals(teste[4]) && !"".equals(teste[0]) && "".equals(teste[8])) {
            int r[] = {2, 2};
            return r;
        }
        if (teste[0].equals(teste[8]) && !"".equals(teste[0]) && "".equals(teste[4])) {
            int r[] = {1, 1};
            return r;
        }
        if (teste[4].equals(teste[8]) && !"".equals(teste[4]) && "".equals(teste[0])) {
            int r[] = {0, 0};
            return r;
        }
        if (teste[2].equals(teste[4]) && !"".equals(teste[2]) && "".equals(teste[6])) {
            int r[] = {2, 0};
            return r;
        }
        if (teste[2].equals(teste[6]) && !"".equals(teste[2]) && "".equals(teste[4])) {
            int r[] = {2, 2};
            return r;
        }
        if (teste[4].equals(teste[6]) && !"".equals(teste[4]) && "".equals(teste[2])) {
            int r[] = {0, 2};
            return r;
        }
        linhaColuna[0] = gerador.nextInt(3);
        linhaColuna[1] = gerador.nextInt(3);
        return linhaColuna;
    }

}
