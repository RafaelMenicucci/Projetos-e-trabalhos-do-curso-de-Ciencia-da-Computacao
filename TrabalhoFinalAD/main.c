/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * 
 * Author: 2016.1.08.016 Rafael Alves Menicucci Pinto
 *
 * Created on April 26, 2019, 4:29 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "fila.h"

typedef struct little_ {
    double tempo_anterior;
    double soma_areas;
    double qtd_pacotes;
} little;

typedef struct node {
    char tipo;
    double priority;
    double tempo_cbr;
    double tempo_max;

    struct node* next;

} Node;

Node* newNode(char d, double p, double atual, double max) {
    Node* temp = (Node*) malloc(sizeof (Node));
    temp->tipo = d;
    temp->priority = p;
    temp->tempo_cbr = atual;
    temp->tempo_max = max;
    temp->next = NULL;

    return temp;
}

double peek(Node** head) {
    return (*head)->priority;
}

char peektipo(Node** head) {
    return (*head)->tipo;
}

void pop(Node** head) {
    Node* temp = *head;
    (*head) = (*head)->next;
    free(temp);
}

void push(Node** head, char d, double p, double atual, double max) {
    Node* start = (*head);

    Node* temp = newNode(d, p, atual, max);

    if ((*head)->priority > p) {
        temp->next = *head;
        (*head) = temp;
    } else {
        while (start->next != NULL &&
                start->next->priority < p) {
            start = start->next;
        }
        temp->next = start->next;
        start->next = temp;
    }
}

int isEmpty(Node** head) {
    return (*head) == NULL;
}

/*
 * @return numero aleatorio entre 0 e 1
 */

double aleatorio() {
    double u;
    u = rand() % RAND_MAX;
    u = u / RAND_MAX;
    u = 1.0 - u;

    return (u);
}

/**
 * 
 * @param l da exponencial
 * @return intervalo de tempo,com media tendendo ao intervalo informado pelo usuario
 */
double chegada_pct(double l) {
    return (-1.0 / l)*log(aleatorio());
}

/**
 * 
 * @return tamanho do pacote que acabou de chegar seguindo a proporcao aproximada de 50% = 550bytes, 40%= 40bytes e 10% = 1500bytes 
 */
double gera_tamanho_pct() {
    double a = aleatorio();
    //tamanhos convertidos em mb
    if (a <= 0.5) {
        return ((550.0 * 8.0) / (1000000.0));
    } else if (a <= 0.9) {
        return ((40.0 * 8.0) / (1000000.0));
    }
    return ((1500.0 * 8.0) / (1000000.0));
}

double minimo(double a, double b) {
    if (a <= b)
        return a;
    return b;
}

/**
 * 
 * @param l variavel little q sera iniciada
 */
void inicia_little(little * l) {
    l->qtd_pacotes = 0.0;
    l->soma_areas = 0.0;
    l->tempo_anterior = 0.0;
}

/**
 * 
 * @param min
 * @param max
 * @return random entre min e max
 */
double randfrom(double min, double max) {
    double range = (max - min);
    double div = RAND_MAX / range;
    return min + (rand() / div);
}

/*
 * 
 */
int main() {
    pacote ** inicio = malloc(sizeof (pacote *));
    pacote ** fim = malloc(sizeof (pacote *));
    *inicio = NULL;
    *fim = NULL;

    pacote ** iniciocbr = malloc(sizeof (pacote *));
    pacote ** fimcbr = malloc(sizeof (pacote *));
    *iniciocbr = NULL;
    *fimcbr = NULL;

    //variavel para en
    little en;
    //variavel para ew
    little ew_chegada;
    little ew_saida;

    inicia_little(&en);
    inicia_little(&ew_chegada);
    inicia_little(&ew_saida);

    //variavel para en fila WEB
    little enweb;
    //variavel para ew
    little ew_chegadaweb;
    little ew_saidaweb;

    inicia_little(&enweb);
    inicia_little(&ew_chegadaweb);
    inicia_little(&ew_saidaweb);

    //variavel para en fila CBR
    little encbr;
    //variavel para ew
    little ew_chegadacbr;
    little ew_saidacbr;

    inicia_little(&encbr);
    inicia_little(&ew_chegadacbr);
    inicia_little(&ew_saidacbr);

    //iniciando semente para geracao dos numeros pseudoaleatorios
    srand(time(NULL));
    //tempo atual
    double tempo = 0.0;
    //tempo total
    double tempo_total;
    printf("Informe o tempo total de simulacao: ");
    scanf("%lF", &tempo_total);

    //intervalo medio entre chegadas
    double intervalo;
    printf("Informe o intervalo médio de tempo(em segundos) entre pacotes: ");
    scanf("%lF", &intervalo);
    //ajustando parametro para a exponencial
    intervalo = 1.0 / intervalo;

    //intervalo medio entre chegadas
    double intervalo_ligacao_cbr;
    printf("Informe o intervalo médio de tempo(em segundos) entre ligações cbr: ");
    scanf("%lF", &intervalo_ligacao_cbr);
    //ajustando parametro para a exponencial
    intervalo_ligacao_cbr = 1.0 / intervalo_ligacao_cbr;

    //intervalo medio entre chegadas
    double intervalo_duracao_chamada;
    printf("Informe o intervalo médio de tempo(em segundos) da duração das chamadas: ");
    scanf("%lF", &intervalo_duracao_chamada);
    //ajustando parametro para a exponencial
    intervalo_duracao_chamada = 1.0 / intervalo_duracao_chamada;

    //tamanho do pacote gerado
    double tam_pct;
    //tamanho do link de saida do roteador
    double link;
    printf("Informe o tamanho do link (Mbps): ");
    scanf("%lF", &link);

    //tempo de chegada do proximo pacote ao sistema
    double chegada_proximo_pct = chegada_pct(intervalo);
    Node* pq = newNode('w', chegada_proximo_pct, -1, -1);

    //tempo de chegada do proximo pacote ao sistema
    double chegada_proximo_ligacao_cbr = chegada_pct(intervalo_ligacao_cbr); //////////////////////////////////////////
    push(&pq, 'n', chegada_proximo_ligacao_cbr, -2, -2);

    //tempo de duracao da chamada
    double duracao_chamada = chegada_pct(intervalo_duracao_chamada);

    double chegada_proximo_pct_cbr = 99999999;

    //tempo de saida do pacote que esta sendo atendido
    double saida_pct_atendimento;

    //variavel para o calculo da operacao do roteador
    double ocupacao = 0.0;
    double ocupacao_web = 0.0;
    double ocupacao_cbr = 0.0;
    double tempo_proximo_cbr = 0;

    int roteador_livre = 1;
    char tipo;
    while (tempo <= tempo_total) {
        tipo = peektipo(&pq);

        //chegada de pct
        if (tipo == 'w') {
            tempo = pq->priority;
            pop(&pq);

            //gera tamanho do pct q chega
            tam_pct = gera_tamanho_pct();

            //pacote colocado na fila
            inserir(inicio, fim, tempo, tam_pct);

            //gerar tempo de chegada do prixmo pacote
            chegada_proximo_pct = tempo + chegada_pct(intervalo);
            push(&pq, 'w', chegada_proximo_pct, -1, -1); /////////////////////////////////

            if (roteador_livre) {
                push(&pq, 's', tempo, -3, -3);
                roteador_livre = 0;
            }

            //calculo de little
            en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
            en.qtd_pacotes++;
            en.tempo_anterior = tempo;

            //calculo ew little
            ew_chegada.soma_areas += ew_chegada.qtd_pacotes * (tempo - ew_chegada.tempo_anterior);
            ew_chegada.qtd_pacotes++;
            ew_chegada.tempo_anterior = tempo;

            //calculo de little WEB
            enweb.soma_areas += enweb.qtd_pacotes * (tempo - enweb.tempo_anterior);
            enweb.qtd_pacotes++;
            enweb.tempo_anterior = tempo;

            //calculo ew little WEB
            ew_chegadaweb.soma_areas += ew_chegadaweb.qtd_pacotes * (tempo - ew_chegadaweb.tempo_anterior);
            ew_chegadaweb.qtd_pacotes++;
            ew_chegadaweb.tempo_anterior = tempo;

        } else if (tipo == 'c') {
            tempo = pq->priority;
            //gera tamanho do pct q chega
            tam_pct = (1200.0 * 8.0) / (1000000.0);

            //pacote colocado na fila
            inserir(iniciocbr, fimcbr, tempo, tam_pct);


            if ((pq->tempo_cbr + tempo) < pq->tempo_max) {
                push(&pq, 'c', pq->tempo_cbr + tempo, pq->tempo_cbr, pq->tempo_max);
            }

            pop(&pq);

            if (roteador_livre) {
                push(&pq, 's', tempo, -3, -3);
                roteador_livre = 0;
            }

            //calculo de little
            en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
            en.qtd_pacotes++;
            en.tempo_anterior = tempo;

            //calculo ew little
            ew_chegada.soma_areas += ew_chegada.qtd_pacotes * (tempo - ew_chegada.tempo_anterior);
            ew_chegada.qtd_pacotes++;
            ew_chegada.tempo_anterior = tempo;

            //calculo de little CBR
            encbr.soma_areas += encbr.qtd_pacotes * (tempo - encbr.tempo_anterior);
            encbr.qtd_pacotes++;
            encbr.tempo_anterior = tempo;

            //calculo ew little CBR
            ew_chegadacbr.soma_areas += ew_chegadacbr.qtd_pacotes * (tempo - ew_chegadacbr.tempo_anterior);
            ew_chegadacbr.qtd_pacotes++;
            ew_chegadacbr.tempo_anterior = tempo;


        } else if (tipo == 'n') {
            tempo = pq->priority;
            pop(&pq);

            //chegada pct cbr
            tempo_proximo_cbr = randfrom(0.010, 0.020);

            //gera tamanho do pct cbr
            tam_pct = (1200.0 * 8.0) / (1000000.0);

            //criar média da duração da chamada
            duracao_chamada = chegada_pct(intervalo_duracao_chamada);

            //c na priorityqueue
            push(&pq, 'c', tempo, tempo_proximo_cbr, tempo + duracao_chamada);

            //gerar chegada proxima ligacao cbr
            chegada_proximo_ligacao_cbr = tempo + chegada_pct(intervalo_ligacao_cbr);
            push(&pq, 'n', chegada_proximo_ligacao_cbr, -2, -2); ///////////////////////////


        } else {//saida de pacote
            pop(&pq);

            if (*inicio != NULL && *iniciocbr != NULL) {
                double atraso = 0;
                double atrasocbr = 0;
                atraso = (tempo - (*inicio)->atual) / 2;
                atrasocbr = tempo - (*iniciocbr)->atual;
                if (atraso > atrasocbr) {
                    //descobrir tam do pct
                    tam_pct = (*inicio)->tamanho;
                    //gerando o tempo em q pct saira do sistema
                    saida_pct_atendimento = tempo + (tam_pct / link);
                    push(&pq, 's', saida_pct_atendimento, -3, -3);

                    ocupacao += saida_pct_atendimento - tempo;
                    ocupacao_web += saida_pct_atendimento - tempo;

                    remover(inicio);
                    //calculo de little WEB
                    enweb.soma_areas += enweb.qtd_pacotes * (tempo - enweb.tempo_anterior);
                    enweb.qtd_pacotes--;
                    enweb.tempo_anterior = tempo;

                    //calculo ew little WEB
                    ew_saidaweb.soma_areas += ew_saidaweb.qtd_pacotes * (tempo - ew_saidaweb.tempo_anterior);
                    ew_saidaweb.qtd_pacotes++;
                    ew_saidaweb.tempo_anterior = tempo;
                    //calculo de little
                    en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
                    en.qtd_pacotes--;
                    en.tempo_anterior = tempo;

                    //calculo ew little
                    ew_saida.soma_areas += ew_saida.qtd_pacotes * (tempo - ew_saida.tempo_anterior);
                    ew_saida.qtd_pacotes++;
                    ew_saida.tempo_anterior = tempo;
                } else {
                    //descobrir tam do pct
                    tam_pct = (*iniciocbr)->tamanho;
                    //gerando o tempo em q pct saira do sistema
                    saida_pct_atendimento = tempo + (tam_pct / link);
                    push(&pq, 's', saida_pct_atendimento, -3, -3);

                    ocupacao += saida_pct_atendimento - tempo;
                    ocupacao_cbr += saida_pct_atendimento - tempo;

                    remover(iniciocbr);
                    //calculo de little CBR
                    encbr.soma_areas += encbr.qtd_pacotes * (tempo - encbr.tempo_anterior);
                    encbr.qtd_pacotes--;
                    encbr.tempo_anterior = tempo;

                    //calculo ew little CBR
                    ew_saidacbr.soma_areas += ew_saidacbr.qtd_pacotes * (tempo - ew_saidacbr.tempo_anterior);
                    ew_saidacbr.qtd_pacotes++;
                    ew_saidacbr.tempo_anterior = tempo;
                    //calculo de little
                    en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
                    en.qtd_pacotes--;
                    en.tempo_anterior = tempo;

                    //calculo ew little
                    ew_saida.soma_areas += ew_saida.qtd_pacotes * (tempo - ew_saida.tempo_anterior);
                    ew_saida.qtd_pacotes++;
                    ew_saida.tempo_anterior = tempo;
                }
            } else if (*inicio == NULL && *iniciocbr != NULL) {
                //descobrir tam do pct
                tam_pct = (*iniciocbr)->tamanho;
                //gerando o tempo em q pct saira do sistema
                saida_pct_atendimento = tempo + (tam_pct / link);
                push(&pq, 's', saida_pct_atendimento, -3, -3);

                ocupacao += saida_pct_atendimento - tempo;
                ocupacao_cbr += saida_pct_atendimento - tempo;

                remover(iniciocbr);
                //calculo de little CBR
                encbr.soma_areas += encbr.qtd_pacotes * (tempo - encbr.tempo_anterior);
                encbr.qtd_pacotes--;
                encbr.tempo_anterior = tempo;

                //calculo ew little CBR
                ew_saidacbr.soma_areas += ew_saidacbr.qtd_pacotes * (tempo - ew_saidacbr.tempo_anterior);
                ew_saidacbr.qtd_pacotes++;
                ew_saidacbr.tempo_anterior = tempo;
                //calculo de little
                en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
                en.qtd_pacotes--;
                en.tempo_anterior = tempo;

                //calculo ew little
                ew_saida.soma_areas += ew_saida.qtd_pacotes * (tempo - ew_saida.tempo_anterior);
                ew_saida.qtd_pacotes++;
                ew_saida.tempo_anterior = tempo;
            } else if (*inicio != NULL && *iniciocbr == NULL) {
                //descobrir tam do pct
                tam_pct = (*inicio)->tamanho;
                //gerando o tempo em q pct saira do sistema
                saida_pct_atendimento = tempo + (tam_pct / link);
                push(&pq, 's', saida_pct_atendimento, -3, -3);

                ocupacao += saida_pct_atendimento - tempo;
                ocupacao_web += saida_pct_atendimento - tempo;

                remover(inicio);
                //calculo de little WEB
                enweb.soma_areas += enweb.qtd_pacotes * (tempo - enweb.tempo_anterior);
                enweb.qtd_pacotes--;
                enweb.tempo_anterior = tempo;

                //calculo ew little WEB
                ew_saidaweb.soma_areas += ew_saidaweb.qtd_pacotes * (tempo - ew_saidaweb.tempo_anterior);
                ew_saidaweb.qtd_pacotes++;
                ew_saidaweb.tempo_anterior = tempo;
                //calculo de little
                en.soma_areas += en.qtd_pacotes * (tempo - en.tempo_anterior);
                en.qtd_pacotes--;
                en.tempo_anterior = tempo;

                //calculo ew little
                ew_saida.soma_areas += ew_saida.qtd_pacotes * (tempo - ew_saida.tempo_anterior);
                ew_saida.qtd_pacotes++;
                ew_saida.tempo_anterior = tempo;
            } else if (*inicio == NULL && *iniciocbr == NULL) {
                roteador_livre = 1;
            }

        }

    }
    //TOTAL
    ew_saida.soma_areas += ew_saida.qtd_pacotes * (tempo - ew_saida.tempo_anterior);
    ew_chegada.soma_areas += ew_chegada.qtd_pacotes * (tempo - ew_chegada.tempo_anterior);

    double en_final = en.soma_areas / tempo;
    double ew = ew_chegada.soma_areas - ew_saida.soma_areas;
    ew = ew / ew_chegada.qtd_pacotes;

    double lambda = ew_chegada.qtd_pacotes / tempo;

    //WEB
    ew_saidaweb.soma_areas += ew_saidaweb.qtd_pacotes * (tempo - ew_saidaweb.tempo_anterior);
    ew_chegadaweb.soma_areas += ew_chegadaweb.qtd_pacotes * (tempo - ew_chegadaweb.tempo_anterior);

    double en_finalweb = enweb.soma_areas / tempo;
    double ewweb = ew_chegadaweb.soma_areas - ew_saidaweb.soma_areas;
    ewweb = ewweb / ew_chegadaweb.qtd_pacotes;

    double lambdaweb = ew_chegadaweb.qtd_pacotes / tempo;

    //CBR
    ew_saidacbr.soma_areas += ew_saidacbr.qtd_pacotes * (tempo - ew_saidacbr.tempo_anterior);
    ew_chegadacbr.soma_areas += ew_chegadacbr.qtd_pacotes * (tempo - ew_chegadacbr.tempo_anterior);

    double en_finalcbr = encbr.soma_areas / tempo;
    double ewcbr = ew_chegadacbr.soma_areas - ew_saidacbr.soma_areas;
    ewcbr = ewcbr / ew_chegadacbr.qtd_pacotes;

    double lambdacbr = ew_chegadacbr.qtd_pacotes / tempo;



    printf("Ocupacao: %lF\n", ocupacao / tempo);
    printf("Ocupacao WEB: %lF\n", ocupacao_web / tempo);
    printf("Ocupacao CBR: %lF\n", ocupacao_cbr / tempo);

    printf("\n========Little WEB========\n");
    printf("E[N] = %lF\n", en_finalweb);
    printf("E[W]=%lF\n", ewweb);
    printf("Lambda = %lF\n", lambdaweb);
    printf("\n======================\n");
    printf("Validacao de little: %.20lF", en_finalweb - (lambdaweb * ewweb));
    printf("\n");

    printf("\n========Little CBR========\n");
    printf("E[N] = %lF\n", en_finalcbr);
    printf("E[W]=%lF\n", ewcbr);
    printf("Lambda = %lF\n", lambdacbr);
    printf("\n======================\n");
    printf("Validacao de little: %.20lF", en_finalcbr - (lambdacbr * ewcbr));
    printf("\n");

    printf("\n========Little TOTAL========\n");
    printf("E[N] = %lF\n", en_final);
    printf("E[W]=%lF\n", ew);
    printf("Lambda = %lF\n", lambda);
    printf("\n======================\n");
    printf("Validacao de little: %.20lF", en_final - (lambda * ew));
    printf("\n");


    return (EXIT_SUCCESS);
}

