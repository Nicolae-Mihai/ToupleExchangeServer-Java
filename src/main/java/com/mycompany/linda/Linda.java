/*
 * The touples used to develop the program will have variable sizes with a maximum 
 * of 6 elements and all of them will be Strings.
 * A variable inside a Touple is alwais identified with "?" followed by an 
 * uppercase letter(A-Z).
 * The server is formed by 3 servers that are executed in different devices, 
 * tasked with storing and managing the operations that are done on the touples 
 * that match it's conditions. 
 * Every work group should choose the best data structure for it's data storage.
 * If the primary server is down, the replica will recieve the operations 
 * intended for the primary server and it will keep the system functional.
 */
package com.mycompany.linda;

import java.io.IOException;


public class Linda  extends Connexion{

  //Extends Connexion so it can use the sockets and everything else
    int servNum=1;
    public Linda() throws IOException {
    	super("server");
    }

    public void startLinda() {//Server start Method
        try {
            
            System.out.println("Waiting..."); //Waiting for a connection
            while(true){
                cs=ss.accept(); //Accept starts the socket and waits for a connexion from a client
                LindaThread ltd= new LindaThread("Linda"+servNum);
                ltd.startLinda();

                servNum++;
           }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}