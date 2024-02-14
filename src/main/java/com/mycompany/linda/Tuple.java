/*
 * 
 */
package com.mycompany.linda;

import java.util.ArrayList;
import java.util.List;


public class Tuple {
    private  final String first;
    private  final String second;
    private  final String third;
    private  final String fourth;
    private  final String fifth;
    private  final String sixth;
    private  List<String> elem;
    
//  constructor for a tuple with one element also called a Unit
    public Tuple(String first){
        this.first=first;
		this.second = null;
		this.third= null;
		this.fourth = null;
		this.fifth = null;
		this.sixth = null;
        elem=new ArrayList<String>();
        elem.add(first);
    }
//  Constructor for a tuple with 2 elements
    public Tuple(String first,String second){
        this.first=first;
        this.second=second;
        this.third= null;
		this.fourth = null;
		this.fifth = null;
		this.sixth = null;
		
        elem=new ArrayList<String>();
        elem.add(first);
        elem.add(second);
    }
//  Constructor for a tuple with 3 elements also called a triplet
    public Tuple(String first,String second, String third){
        this.first=first;
        this.second=second;
        this.third=third;
		this.fourth = null;
		this.fifth = null;
		this.sixth = null;
		
        elem=new ArrayList<String>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
    }
//  Constructor for a tuple with 4 elements also called a quartet
    public Tuple(String first,String second, String third, String fourth){
        this.first=first;
        this.second=second;
        this.third=third;
        this.fourth=fourth;
		this.fifth = null;
		this.sixth = null;
        
        elem=new ArrayList<String>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
        elem.add(fourth);
    }
//  Constructor for a tuple with 5 elements also called a quintet
    public Tuple(String first,String second, String third, String fourth, String fifth){
        this.first=first;
        this.second=second;
        this.third=third;
        this.fourth=fourth;
        this.fifth=fifth;
        this.sixth=null;
        
        elem=new ArrayList<String>();
        elem.add(first);
        elem.add(second);
        elem.add(third);
        elem.add(fourth);
        elem.add(fifth);
    }
//  Constructor for a tuple with 6 elements also called a Sextet
    public Tuple(String first,String second, String third, String fourth, String fifth, String sixth){
        this.first=first;
        this.second=second;
        this.third=third;
        this.fourth=fourth;
        this.fifth=fifth;
        this.sixth=sixth;
        
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
    public void print() {
    	System.out.println(this.first+" comma "+this.second+" comma "+this.third+" comma "+this.fourth+" comma ");
    }
     
}
