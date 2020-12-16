#include <stdlib.h>
#include<stdio.h>
#include"fila.h"

pacote * aloca_pct() {
    return malloc(sizeof (pacote));
}

/**
 * 
 * @param inicio aponta o primeiro da fila
 * @param fim aponta o ultimo da fila
 * @param tamanho do pacote a ser inserido
 * @return 1 caso insira, 0 caso erro
 */
int inserir(pacote ** inicio, pacote ** fim, double atual, double tamanho) {
    //fila vazia
    if (*inicio == NULL) {
        *inicio = aloca_pct();
        if (*inicio == NULL) {
            return 0;
        }
        *fim = *inicio;
        (*inicio)->atual = atual;
        //(*inicio)->acaba = acaba;
        (*inicio)->tamanho = tamanho;
        (*inicio)->prox = NULL;
        return 1;
    } else {
        pacote * tmp = aloca_pct();
        if (tmp == NULL) {
            return 0;
        }
        tmp->atual = atual;
        //tmp->acaba = acaba;
        tmp->tamanho = tamanho;
        tmp->prox = NULL;

        (*fim)->prox = tmp;
        (*fim) = tmp;
        return 1;
    }
}

double remover(pacote ** inicio) {
    if (*inicio == NULL) {
        return 0;
    } else {
        pacote * tmp = *inicio;
        double atual = (*inicio)->atual;
        //double acaba = (*inicio)->acaba;
        double tamanho = (*inicio)->tamanho;
        (*inicio) = (*inicio)->prox;
        free(tmp);

        return tamanho;
    }
}