package com.mycompany.client;

import java.io.IOException;

//Main class that uses Client
public class MainClient {
  public static void main(String[] args) throws IOException {
      Client cli = new Client(); //Create a client

      System.out.println("Starting client\n");
      cli.startClient(); //Starts the client
  }
}