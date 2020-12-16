 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.awt.*;  
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.awt.geom.*;
import javax.swing.JFrame;

/**
 *
 * @author rafael
 */
class Desenho extends JFrame{
    LinkedList<Float> desenho = new LinkedList();
    int []descoberto= null;
    int []finalizado = null;
    
    public Desenho(LinkedList desenho, int[]desc, int[]fin){ 
        this.desenho = desenho;
        this.descoberto = desc;
        this.finalizado = fin;
        setSize(1366,768);  
        setLayout(null);  
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    } 
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<desenho.size();i=i+2){
            Ellipse2D.Double s = new Ellipse2D.Double(desenho.get(i)*5, desenho.get(i+1)*5, 5,5);
            g2.draw(s);
        }
        for(int j = 0;j<descoberto.length;j++){
            Line2D.Double line = new Line2D.Double(desenho.get(descoberto[j]*2)*5,desenho.get(descoberto[j]*2+1)*5 ,desenho.get(finalizado[j]*2)*5 , desenho.get(finalizado[j]*2+1)*5);     
            g2.draw(line);            
        }
    }
}
    