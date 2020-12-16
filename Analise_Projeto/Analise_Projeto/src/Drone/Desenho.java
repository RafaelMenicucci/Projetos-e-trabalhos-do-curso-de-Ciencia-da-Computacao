/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import javafx.scene.shape.Ellipse;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rafael
 */
public class Desenho extends JFrame {

    float[] x = new float[1000];
    float[] y = new float[1000];
    float[] raio;
    float[] resposta = new float[2000];
    int cont = 0;
    double distancia = 0;
    int size=0;
    int contmelhorou = 0;
    int contmelhorouSimulated = 0;
    
    Desenho(float[] x, float[] y, float[] raio, float[] resposta,int size,int contmelhorou,int contmelhorouSimulated) {
        this.size=size;
        this.x = x;
        this.y = y;
        this.raio = raio;
        this.resposta = resposta;
        this.distancia = distancia;
        this.contmelhorou = contmelhorou;
        this.contmelhorouSimulated = contmelhorouSimulated;
        setSize(1366, 768);
        setLayout(null);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void setDistancia(double distancia){
        this.distancia=distancia;
    }
    public void setcont(int contmelhorou){
        this.contmelhorou=contmelhorou;
    }
    public void setcontSimulated(int contmelhorouSimulated){
        this.contmelhorouSimulated=contmelhorouSimulated;
    }

    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        Point2D.Float p1 = new Point2D.Float();

        g2.setPaint(Color.RED);

        //g2.draw(new Ellipse2D.Float((float) ((x[0] - raio[0] / 2)*0.1), (float) ((y[0] - raio[0] / 2)*0.1), raio[0], raio[0]));
        g2.draw(new Ellipse2D.Float((float) (((x[0]*0.07) - ((raio[0]) / 2))), (float) (((y[0]*0.07) - ((raio[0]) / 2))), raio[0], raio[0]));

        g2.setPaint(Color.BLUE);
        for (int i = 1; i < x.length; i++) {
            //g2.draw(new Ellipse2D.Float((float) ((x[i] - raio[i] / 2)*0.1), (float) ((y[i] - raio[i] / 2)*0.1), raio[i], raio[i]));
            g2.draw(new Ellipse2D.Float((float) (((x[i]*0.07) - ((raio[i]) / 2))), (float) (((y[i]*0.07) - ((raio[i]) / 2))), raio[i], raio[i]));
        }

        g2.draw(new Line2D.Float((float) (resposta[0]*0.07), (float) (resposta[1]*0.07), (float) (resposta[2]*0.07), (float) (resposta[3]*0.07)));
        for (int i = 2; i < size*2-3; i = i + 2) {
            g2.draw(new Line2D.Float((float) (resposta[i]*0.07), (float) (resposta[i + 1]*0.07), (float) (resposta[i + 2]*0.07), (float) (resposta[i + 3]*0.07)));
        }
        g2.draw(new Line2D.Float((float) (resposta[size*2-2]*0.07), (float) (resposta[size*2-1]*0.07), (float) (resposta[0]*0.07), (float) (resposta[1]*0.07)));

        g2.setPaint(Color.BLACK);
        g2.drawString("Distancia:", 1030, 100);
        g2.drawString(String.valueOf(distancia), 1100, 100);
        
        g2.drawString("Melhorou:", 1030, 200);
        g2.drawString(String.valueOf(contmelhorou), 1100, 200);
        
        g2.drawString("Melhorou(Simulated):", 1030, 300);
        g2.drawString(String.valueOf(contmelhorouSimulated), 1200, 300);

    }
}
