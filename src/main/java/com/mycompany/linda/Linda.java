/*
 * The touples used to develop the program will have variable sizes with a maximum 
 * of 6 elements and all of them will be Strings.
 * A variable inside a Tuple is always identified with "?" followed by an 
 * uppercase letter(A-Z).
 * The server is formed by 3 servers that are executed in different devices, 
 * tasked with storing and managing the operations that are done on the tuples 
 * that match it's conditions. 
 * Every work group should choose the best data structure for it's data storage.
 * If the primary server is down, the replica will receive the operations 
 * intended for the primary server and it will keep the system functional.
 */
package com.mycompany.linda;

import java.io.IOException;

//Extends Connexion so it can use the sockets and everything else
public class Linda extends Connexion { 
	
    public Linda() throws IOException {
    	super("server");
    }

    public void startLinda() {//Server start method
        try {
        	System.out.println("Waiting..."); //Waiting for connection
        	int i=0;	            
        	while(true) {
	            cs = ss.accept(); //Accept starts the socket and waits for a connection from a client
	            LindaThread ldt =new LindaThread(i,this.cs);
	            ldt.start();
	            i++;
        	}
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

}