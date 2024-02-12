/*
 * 
 */
package com.mycompany.linda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusti
 */

public class Touple< A, B, C, D, E, F> {
    private final A first;
//    private final B second;
//    private final C third;
//    private final D fourth;
//    private final E fifth;
//    private final F sixth;
    private final List elem;
    
//  constructor for a touple with one element also called a Unit
    public Touple(A first){
        this.first=first;
        elem=new ArrayList<>();
        elem.add(first);
    }
//  Constructor for a touple with 2 elements
    public Touple(A first,B second){
        this.first=first;
//        this.second=second;
        
        elem=new ArrayList<>();
        elem.add(first);
        elem.add(second);
    }
//  Constructor for a touple with 3 elements also called a triplet
    public Touple(A first,B second, C third){
        this.first=first;
//        this.second=second;
//        this.third=third;
        elem=new ArrayList<>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
    }
//  Constructor for a touple with 4 elements also called a quartet
    public Touple(A first,B second, C third, D fourth){
        this.first=first;
//        this.second=second;
//        this.third=third;
//        this.fourth=fourth;
        
        elem=new ArrayList<>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
        elem.add(fourth);
    }
//  Constructor for a touple with 5 elements also called a quintet
    public Touple(A first,B second, C third, D fourth, E fifth){
        this.first=first;
//        this.second=second;
//        this.third=third;
//        this.fourth=fourth;
//        this.fifth=fifth;
        
        elem=new ArrayList<>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
        elem.add(fourth);
        elem.add(fifth);
    }
//  Constructor for a touple with 6 elements also called a sextet
    public Touple(A first,B second, C third, D fourth, E fifth, F sixth){
        this.first=first;
//        this.second=second;
//        this.third=third;
//        this.fourth=fourth;
//        this.fifth=fifth;
//        this.sixth=sixth;
        
        elem=new ArrayList<>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
        elem.add(fourth);
        elem.add(fifth);
        elem.add(sixth);
    }
    
    public int length(){
        return elem.size();
    }
     
}
