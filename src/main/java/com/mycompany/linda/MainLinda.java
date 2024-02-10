package com.mycompany.linda;

import java.io.IOException;

//Main class that uses Linda
public class MainLinda {
  public static void main(String[] args) throws IOException {
      Linda serv = new Linda(); //Creates Linda

      System.out.println("Iniciando servidor\n");
      serv.startLinda();//Starts Linda
  }
}