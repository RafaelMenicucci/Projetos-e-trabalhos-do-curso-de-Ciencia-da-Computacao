/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author rafael
 */
public class Node {

    public Node parent;
    public int[][] matrix;

    public int x, y;

    public int cost;

    public int level;

    public Node(int[][] matrix, int x, int y, int newX, int newY, int level, Node parent) {
        this.parent = parent;
        this.matrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = matrix[i].clone();
        }

        this.matrix[x][y] = this.matrix[x][y] + this.matrix[newX][newY];
        this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
        this.matrix[x][y] = this.matrix[x][y] - this.matrix[newX][newY];

        this.cost = Integer.MAX_VALUE;
        this.level = level;
        this.x = newX;
        this.y = newY;
    }
}
