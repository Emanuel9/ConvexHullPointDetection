/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import static java.lang.Math.min;
import java.util.ArrayList;


/**
 *
 * @author Ovidiu
 * @author Emanuel
 */
public class Point {
   float x,y;
   final static int INF = 10000;
   public Point(){
     x=0;
     y=0;
    }
   public Point(float a, float b){
       x=a;
       y=b;
   }

   public static float Distance(Point p1, Point p2){
        return (float) Math.sqrt(Math.pow(p2.x - p1.x,2)+ Math.pow(p2.y-p1.y, 2));
   }
   public static boolean Distance_check(Point c1, Point c2){
        return Distance(c1, c2)>=20.0;
    }
   
   void printxy(){
        System.out.println(x + " " + y);
   }
   
   public static boolean onSegment(Point p,Point q,Point r){
    return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
            q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
   }
   
   public static int orientation(Point p,Point q,Point r){
           float val = (q.y - p.y) * (r.x - q.x) -
              (q.x - p.x) * (r.y - q.y);
 
    if (val == 0) return 0;  // Points are colinear
    return (val > 0)? 1: 2; // clock or counterclock wise
   }
   public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2){
       // Find the four orientations needed for general and
    // special cases
    int o1 = orientation(p1, q1, p2);
    int o2 = orientation(p1, q1, q2);
    int o3 = orientation(p2, q2, p1);
    int o4 = orientation(p2, q2, q1);
 
    // General case
    if (o1 != o2 && o3 != o4)
        return true;
 
    // Special Cases
    // p1, q1 and p2 are colinear and p2 lies on segment p1q1
    if (o1 == 0 && onSegment(p1, p2, q1)) return true;
 
    // p1, q1 and p2 are colinear and q2 lies on segment p1q1
    if (o2 == 0 && onSegment(p1, q2, q1)) return true;
 
    // p2, q2 and p1 are colinear and p1 lies on segment p2q2
    if (o3 == 0 && onSegment(p2, p1, q2)) return true;
 
     // p2, q2 and q1 are colinear and q1 lies on segment p2q2
    if (o4 == 0 && onSegment(p2, q1, q2)) return true;
 
    return false; // Doesn't fall in any of the above cases
   }
   public boolean isInside(ArrayList<Circle> polygon){
       
// There must be at least 3 vertices in polygon[]
    if (polygon.size() < 3)  return false;
 
    // Create a point for line segment from p to infinite
    Point extreme =  new Point(INF, this.y);
 
    // Count intersections of the above line with sides of polygon
    int count = 0, i = 0;
    do
    {
        int next = (i+1)%polygon.size();
 
        // Check if the line segment from 'p' to 'extreme' intersects
        // with the line segment from 'polygon[i]' to 'polygon[next]'
        if (doIntersect(polygon.get(i), polygon.get(next), this, extreme))
        {
            // If the point 'p' is colinear with line segment 'i-next',
            // then check if it lies on segment. If it lies, return true,
            // otherwise false
            if (orientation(polygon.get(i), this, polygon.get(next)) == 0)
               return onSegment(polygon.get(i), this, polygon.get(next));
 
            count++;
        }
        i = next;
    } while (i != 0);
 
    // Return true if count is odd, false otherwise
    return (count%2==1);  // Same as (count%2 == 1)
   }
   
   
}
