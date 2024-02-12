/*
 * The client must be able to do any of the following operations:
 *   - Connect to the service
 *   - Disconnect from the service
 *   - Any of the three basic operations with tuples
 */

package com.mycompany.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client extends Connexion {
    public Client() throws IOException {
    	super("client");
    } //We use the constructor for client from Connection

    public void startClient() {//Method to start the client message
        try {
        	// Tunnel to receive information (In)
        	DataInputStream in = new DataInputStream(cs.getInputStream());
        	// Tunnel to send information (Out)
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            
            String message = in.readUTF();
            System.out.println(message);
            
            try (Scanner entry = new Scanner(System.in)) {
				while(true) {
					System.out.println("Write a message (END OF SERVICE to close): ");
					String strin = entry.nextLine();
					out.writeUTF(strin);
					if(strin.equalsIgnoreCase("END OF SERVICE")) break;
					System.out.println(in.readUTF());
				}
			}
            cs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}