package com.mycompany.client;

import com.mycompany.client.Client;
import java.io.IOException;

//Main class that uses Client
public class MainClient {
  public static void main(String[] args) throws IOException {
      Client cli = new Client(); //Creates the client

      System.out.println("Starting Client\n");
      cli.startClient(); //Starts the client
  }
}