/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

/**
 *
 * @author rafael
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinMaxPoda {

    ArrayList<Ponto> pontosDisponiveis;
    Scanner user_input = new Scanner(System.in);
    static Scanner quemjoga = new Scanner(System.in);
    int[][] tttBoard = new int[3][3];
    ArrayList<PontoValor> pontosEstadosFilhos = new ArrayList<>();
    int limiteProfundidade = 4; //MUDA O LIMITE DE PROFUNDIDADE
    static String[] teste = new String[9];
    static int[] meupc = new int[2];
    static String resposta = "";

    public int avaliarEstadoComHeuristica() {
        int pontuacaoEstado = 0;

        //avaliar linhas
        for (int i = 0; i < 3; ++i) {
            int vazios = 0;
            int numX = 0;
            int numO = 0;
            for (int j = 0; j < 3; ++j) {
                if (tttBoard[i][j] == 0) {
                    vazios++;
                } else if (tttBoard[i][j] == 1) {
                    numX++;
                } else {
                    numO++;
                }
            }
            pontuacaoEstado = pontuacaoEstado + heuristica(numX, numO);
        }

        //avaliar colunas
        for (int j = 0; j < 3; ++j) {
            int vazios = 0;
            int X = 0;
            int O = 0;
            for (int i = 0; i < 3; ++i) {
                if (tttBoard[i][j] == 0) {
                    vazios++;
                } else if (tttBoard[i][j] == 1) {
                    X++;
                } else {
                    O++;
                }
            }
            pontuacaoEstado += heuristica(X, O);
        }

        int vazios = 0;
        int numX = 0;
        int numO = 0;
        //avaliar primeira diagonal
        for (int i = 0, j = 0; i < 3; ++i, ++j) {
            if (tttBoard[i][j] == 1) {
                numX++;
            } else if (tttBoard[i][j] == 2) {
                numO++;
            } else {
                vazios++;
            }
        }

        pontuacaoEstado += heuristica(numX, numO);

        vazios = 0;
        numX = 0;
        numO = 0;
        //avaliar segunda diagonal
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            if (tttBoard[i][j] == 1) {
                numX++;
            } else if (tttBoard[i][j] == 2) {
                numO++;
            } else {
                vazios++;
            }
        }
        pontuacaoEstado += heuristica(numX, numO);

        return pontuacaoEstado;
    }

    private int heuristica(int numX, int numO) {
        int pontuacao;
        if (numX == 3) {
            pontuacao = 100;
        } else if (numX == 2 && numO == 0) {
            pontuacao = 10;
        } else if (numX == 1 && numO == 0) {
            pontuacao = 1;
        } else if (numO == 3) {
            pontuacao = -100;
        } else if (numO == 2 && numX == 0) {
            pontuacao = -10;
        } else if (numO == 1 && numX == 0) {
            pontuacao = -1;
        } else {
            pontuacao = 0;
        }
        return pontuacao;
    }

    public int alphaBetaMinimax(int alpha, int beta, int profundidade, int jogador) {
        //Não precisa mais expandir
        if (beta <= alpha) {
            if (jogador == 1) //computador
            {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        if (profundidade == limiteProfundidade || fimDeJogo()) {
            return avaliarEstadoComHeuristica();
        }

        ArrayList<Ponto> _pontosDisponiveis = getPontosDisponiveis();
        if (_pontosDisponiveis.isEmpty()) {
            return 0;
        }

        if (profundidade == 0) {
            pontosEstadosFilhos.clear();
        }

        int maxValor = Integer.MIN_VALUE, minValor = Integer.MAX_VALUE;

        for (int i = 0; i < _pontosDisponiveis.size(); ++i) {
            Ponto _ponto = _pontosDisponiveis.get(i);
            int pontuacaoAtual = 0;

            if (jogador == 1) {
                aplicarJogada(_ponto, 1);
                pontuacaoAtual = alphaBetaMinimax(alpha, beta, profundidade + 1, 2);
                maxValor = Math.max(maxValor, pontuacaoAtual);
                alpha = Math.max(pontuacaoAtual, alpha);
                if (profundidade == 0) {
                    pontosEstadosFilhos.add(new PontoValor(pontuacaoAtual, _ponto));
                }
            } else if (jogador == 2) {
                aplicarJogada(_ponto, 2);
                pontuacaoAtual = alphaBetaMinimax(alpha, beta, profundidade + 1, 1);
                minValor = Math.min(minValor, pontuacaoAtual);
                beta = Math.min(pontuacaoAtual, beta);
            }
            tttBoard[_ponto.x][_ponto.y] = 0;

            if (pontuacaoAtual == Integer.MAX_VALUE || pontuacaoAtual == Integer.MIN_VALUE) {
                break;
            }
        }
        return jogador == 1 ? maxValor : minValor;
    }

    public boolean fimDeJogo() {
        return (Xganhou() || Oganhou() || getPontosDisponiveis().isEmpty());
    }

    public boolean Xganhou() {
        if ((tttBoard[0][0] == tttBoard[1][1] && tttBoard[0][0] == tttBoard[2][2] && tttBoard[0][0] == 1)
                || (tttBoard[0][2] == tttBoard[1][1] && tttBoard[0][2] == tttBoard[2][0] && tttBoard[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((tttBoard[i][0] == tttBoard[i][1] && tttBoard[i][0] == tttBoard[i][2] && tttBoard[i][0] == 1)
                    || (tttBoard[0][i] == tttBoard[1][i] && tttBoard[0][i] == tttBoard[2][i] && tttBoard[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean Oganhou() {
        if ((tttBoard[0][0] == tttBoard[1][1] && tttBoard[0][0] == tttBoard[2][2] && tttBoard[0][0] == 2)
                || (tttBoard[0][2] == tttBoard[1][1] && tttBoard[0][2] == tttBoard[2][0] && tttBoard[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((tttBoard[i][0] == tttBoard[i][1] && tttBoard[i][0] == tttBoard[i][2] && tttBoard[i][0] == 2)
                    || (tttBoard[0][i] == tttBoard[1][i] && tttBoard[0][i] == tttBoard[2][i] && tttBoard[0][i] == 2)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Ponto> getPontosDisponiveis() {
        pontosDisponiveis = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (tttBoard[i][j] == 0) {
                    pontosDisponiveis.add(new Ponto(i, j));
                }
            }
        }
        return pontosDisponiveis;
    }

    public void aplicarJogada(Ponto ponto, int jogador) {
        //jogador = 1 para X ou 2 para O
        if (jogador == 1) {
            tttBoard[ponto.x][ponto.y] = jogador;
            teste[ponto.x * 3 + ponto.y] = "X";
        } else {
            tttBoard[ponto.x][ponto.y] = jogador;
            teste[ponto.x * 3 + ponto.y] = "O";
        }
        /////////////////////
    }

    public Ponto getMelhorJogada() {
        int MAX = -99999;
        int indiceMelhorJogada = -1;

        for (int i = 0; i < pontosEstadosFilhos.size(); ++i) {
            if (MAX < pontosEstadosFilhos.get(i).valor) {
                MAX = pontosEstadosFilhos.get(i).valor;
                indiceMelhorJogada = i;
            }
        }
        return pontosEstadosFilhos.get(indiceMelhorJogada).ponto;
    }

    void inputJodador() {
        if ("s".equals(resposta)) {
            System.out.println("Sua jogada: ");
            int x = user_input.nextInt();
            int y = user_input.nextInt();
            Ponto ponto = new Ponto(x, y);
            aplicarJogada(ponto, 2);
        } else {
            Pensa p = new Pensa(teste);
            System.out.println("Sua jogada: ");
            meupc = p.get();
            int x = meupc[0];
            int y = meupc[1];
            Ponto ponto = new Ponto(x, y);
            aplicarJogada(ponto, 2);
        }

    }

    public void mostrarJogo() {
        System.out.println();
        for (int i = 0; i < 3; ++i) {
            System.out.print("------------");
            System.out.println("");
            System.out.print("| ");
            for (int j = 0; j < 3; ++j) {
                if (tttBoard[i][j] == 2) {
                    System.out.print("X" + " | ");
                } else if (tttBoard[i][j] == 1) {
                    System.out.print("O" + " | ");
                } else {
                    System.out.print(" " + " | ");
                }
            }
            System.out.println();
        }
        System.out.print("------------");
        System.out.println();
    }

    public void limparJogo() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                tttBoard[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < teste.length; i++) {
            teste[i] = "";
        }
        MinMaxPoda jogoDaVelha = new MinMaxPoda();
        Pensa p = new Pensa(teste);
        System.out.println("Sua jogada: ");
        meupc = p.get();

        System.out.println("Como jogar: digitar as coordenadas da posição desejada sem colchetes.");
        System.out.println("[0 0] [0 1] [0 2]");
        System.out.println("[1 0] [1 1] [1 2]");
        System.out.println("[2 0] [2 1] [2 2]");

        System.out.println("Você quer jogar contra o MINMAX? s para sim e n para não: ");
        resposta = quemjoga.nextLine();
        while (!jogoDaVelha.fimDeJogo()) {

            Ponto userInput = null;
            meupc = p.get();
            int x = meupc[0];
            int y = meupc[1];
            if ("s".equals(resposta)) {
                userInput = new Ponto(jogoDaVelha.user_input.nextInt(), jogoDaVelha.user_input.nextInt());
            } else {
                userInput = new Ponto(x, y);
            }

            jogoDaVelha.aplicarJogada(userInput, 2);
            jogoDaVelha.mostrarJogo();
            if (jogoDaVelha.fimDeJogo()) {
                break;
            }

            jogoDaVelha.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
            jogoDaVelha.aplicarJogada(jogoDaVelha.getMelhorJogada(), 1);
            jogoDaVelha.mostrarJogo();
        }

        if (jogoDaVelha.Xganhou()) {
            System.out.println("Você perdeu!");
        } else if (jogoDaVelha.Oganhou()) {
            System.out.println("Você ganhou!");
        } else {
            System.out.println("Empatou!");
        }
    }
}
