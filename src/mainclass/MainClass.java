/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainclass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;
//Graphics
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Emanuel
 * @author Ovidiu
 */

class Circle extends Point{
    static Circle c0; // We use this point for orientation;
    static final int radius = 10;
    static int nr_of_circles;
    public Circle(int x, int y){
        super.x = x;
        super.y = y;
    }
    public static boolean CanAdd(Circle c,ArrayList<Circle> ac){
        int number = 0;
        for(int i=0;i<ac.size();i++){
            if(Circle.Distance_check(c,ac.get(i))){
                number++;
            }
                
        }
        return (number == ac.size());
    }
    
    public static void printCircles(ArrayList<Circle> ac){
        for(int i=0;i<ac.size();i++){
            System.out.println("Circle " + i + " :  ("+ac.get(i).x + "," + ac.get(i).y + ")");
            
        }
    }
    //Graham Scan methods
    public static Circle nextToTop(Stack<Circle> S){
        Circle c = S.peek();
        S.pop();
        Circle res = S.peek();
        S.push(c);
        return res;
    }
    
    public static void swap(Circle c1,Circle c2){
        Circle temp = c1;
        c1.x = c2.x;
        c1.y = c2.y;
        c2.x = temp.x;
        c2.y = temp.y;
    }
    
    public static int orientation(Circle p, Circle q, Circle r){
        float val = (q.y - p.y)*(r.x - q.x) - 
                  (q.x - p.x)*(r.y - q.y);
        if(val==0)
            return 0; // Points are colinear
        return (val>0?1:2);
    }

    
    
    public static void GrahamScan(ArrayList<Circle> ac, ArrayList<Circle> out){
        //Find the bottommost point
        float ymin = ac.get(0).y;
        int min=0;
        int i;
        for(i=0;i<ac.size();i++){
            float y = ac.get(i).y;
            
            //Pick the bottom-most or chose the left most poin in case of tie
            if((y<ymin) || (ymin==y && ac.get(i).x < ac.get((int) min).x)){
                ymin = ac.get(i).y;
                min = i;
            }
            //Place the bottom-most point at first position
            Circle.swap(ac.get(0), ac.get(min));
            
            //Sorting n-1
            Circle.c0 = ac.get(0);
            Collections.sort(ac.subList(1, ac.size()),new CompareClass());
            
            //Create an empty Stack and push first 3 elements
            Stack<Circle> S = new Stack<>();
            S.push(ac.get(0));
            S.push(ac.get(1));
            S.push(ac.get(2));
            
            //Process remaining n-3 points
            for(i=2;i<ac.size();i++){
                //Keep removind top while the angle formed by points next-to-top
                //top and ac.get(i) makes a non-left turn
                while(orientation(nextToTop(S), S.peek(), ac.get(i))!=2){
                    S.pop();
                }
                S.push(ac.get(i));
            }
            
            //Now stack is empty
            while(!S.empty()){
                Circle p = S.peek();
                out.add(p);
                S.pop();                
            }
        }
        
    }   
    
    //Graphics
 
    
    
}


public class MainClass {
    
    /**
     * @param args the command line arguments
     */
    public static boolean checkPointInCircles(ArrayList<Circle> out , Point my_point){
        for (Circle circle : out) {
            if(Point.Distance(my_point,circle) <=10){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Random randomGenerator = new Random();
        int nr_of_circles = randomGenerator.nextInt(15) + 3;
        Circle.nr_of_circles = nr_of_circles;
        int x,y;
        ArrayList<Circle> ac = new ArrayList<>();
        for(int i=0;i<nr_of_circles;i++){
            x=randomGenerator.nextInt(300);
            y=randomGenerator.nextInt(300);
            Circle my_circle = new Circle(x, y);
            if(ac.size()>0){
                if(Circle.CanAdd(my_circle,ac)){
                    ac.add(my_circle);
                }else
                    continue;
            }else{
                ac.add(my_circle);
            }
            
        }
        Circle.printCircles(ac);
        
        System.out.println("Acoperirea convexa:\n\n");
        ArrayList<Circle> out = new ArrayList<>();
        Circle.GrahamScan(ac, out);
        
        Circle.printCircles(out);
       
        x=randomGenerator.nextInt(300);
        y=randomGenerator.nextInt(300);
        
        Scanner sc = new Scanner(System.in);
        Point my_point = new Point(x,y);
        
        //1.Checking if a point is in a polygon
        if(my_point.isInside(out)){
            System.out.println("Punctul P("+x+","+y+") se afla in interiorul acoperirii convexe!");
        }else if(checkPointInCircles(out, my_point)){ //2. Checking if the points is in the circles
            System.out.println("Punctul P("+x+","+y+") se afla in interiorul acoperirii convexe!");
        }else if(4<2){ //3.Checking if the point is in rectangles formed by ConvexHull for circles
            System.out.println("Punctul P("+x+","+y+") se afla in interiorul acoperirii convexe!");
        }else{
            System.out.println("Punctul P("+x+","+y+") nu se afla in interiorul acoperirii convexe!");
        }
        
            
        
        
         
        
        
        //Test Printing the points
        JFrame myWindow = new Graphics_circles(out,my_point);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
                  
    }
    
}
