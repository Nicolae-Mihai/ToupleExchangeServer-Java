/*
 * 
 */
package com.mycompany.linda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Tuple {
    private String first = null;
    private String second = null;
    private String third = null;
    private String fourth = null;
    private String fifth = null;
    private String sixth = null;

    private  List<String> elem;
    
//  constructor for a tuple with one element also called a Unit
    public Tuple(String[] array){
        int len = array.length;

        if(len < 2) this.first = array[0];
        if(len < 3) this.second = array[1];
        if(len < 4) this.third= array[2];
        if(len < 5) this.fourth = array[3];
        if(len < 6) this.fifth = array[4];
        if(len < 7) this.sixth = array[5];

        this.elem.add(first);
        if(second != null) this.elem.add(second);
        if(third != null) this.elem.add(third);
        if(fourth != null) this.elem.add(fourth);
        if(fifth != null) this.elem.add(fifth);
        if(sixth != null) this.elem.add(sixth);

    }
    public List<String> getElem(){
        return this.elem;
    }
    public void print() {
    	System.out.println(elem);
    }
     
}
