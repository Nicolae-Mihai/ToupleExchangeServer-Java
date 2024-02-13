package com.mycompany.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnexionClient {
    private final int PORT = 1234; //The port used for the connection
    private final String HOST = "localhost"; //Host used for the connection
    protected ServerSocket ss; //Server socket
    protected Socket cs; //Client socket
    
    public ConnexionClient(String tipo) throws IOException {//Constructor
        if(tipo.equalsIgnoreCase("server")) {
            ss = new ServerSocket(PORT);//A socket is created on port 1234 for the server
            //cs = new Socket(); //Client socket
        } else {
            cs = new Socket(HOST, PORT); //Client socket at localhost on port 1234
        }
    }
}