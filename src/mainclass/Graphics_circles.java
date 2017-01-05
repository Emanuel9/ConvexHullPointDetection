/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainclass;
import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.util.ArrayList;
/**
 *
 * @author Emanuel
 * @author Ovidiu
 */

public class Graphics_circles extends JFrame{
    CirclePanel drawing = new CirclePanel();
    static ArrayList<Circle> ac;
    static Point point_to_verify;
    Graphics_circles(ArrayList<Circle> ac,Point my_Point) {
        this.ac = ac;
        this.point_to_verify = new Point(my_Point.x,my_Point.y);
        //--- Get content pane, set layout, add components
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(drawing, BorderLayout.CENTER); // Note 2
        this.setTitle("Proiect 6 --- Cercuri");
        this.pack(); // finalize the layout
    }//
}
class CirclePanel extends JPanel {
    //=========================================== constructor
    public CirclePanel() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.white);
    }//end constructor
    //=========================================== paintComponent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Circle circle : Graphics_circles.ac) {
            drawCircle(g, (int)circle.x, (int)circle.y, 10);
        }
        g.setColor(Color.blue);
        for(int i=0;i<Graphics_circles.ac.size()-1;i++){
            g.drawLine((int)Graphics_circles.ac.get(i).x, (int)Graphics_circles.ac.get(i).y,
                    (int)Graphics_circles.ac.get(i+1).x, (int)Graphics_circles.ac.get(i+1).y); 
                    
        }
         g.drawLine((int)Graphics_circles.ac.get(Graphics_circles.ac.size()-1).x, (int)Graphics_circles.ac.get(Graphics_circles.ac.size()-1).y,
                    (int)Graphics_circles.ac.get(0).x, (int)Graphics_circles.ac.get(0).y); 
         
         g.setColor(Color.red);
         g.drawLine((int)Graphics_circles.point_to_verify.x, (int)Graphics_circles.point_to_verify.y, (int)Graphics_circles.point_to_verify.x, (int)Graphics_circles.point_to_verify.y);
         g.setColor(Color.black);
         g.drawString("*",(int)Graphics_circles.point_to_verify.x, (int)Graphics_circles.point_to_verify.y);
    }
    // Convenience method to draw from center with radius
    public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
        cg.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
     }//end drawCircle
}