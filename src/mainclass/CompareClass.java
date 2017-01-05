/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainclass;
import java.util.Comparator;

/**
 *
 * @author Emanuel
 */
public class CompareClass implements Comparator<Circle> {
    public int compare(Circle o1,Circle o2){
        int or = Circle.orientation(Circle.c0, o1, o2);
        if(or == 0)
            return ((Circle.Distance(Circle.c0, o2) >= Circle.Distance(Circle.c0, o1))?-1 : 1);
        //return this.compareTo(o);
        return ((or==2)? -1:1);
    }
}
