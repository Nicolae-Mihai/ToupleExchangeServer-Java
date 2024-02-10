/*
 * The client must be able to do any of the following operations:
 *   - Connect to the service
 *   - Disconnect from the service
 *   - Any of the three basic operations with touples
 */
package com.mycompany.client;

import com.mycompany.linda.Connexion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client extends Connexion {
    public Client() throws IOException {
    	super("client");
    } //We use the constructor for client from Conexion

    public void startClient() {//Method to start the client
        try {
            // Tunnel to recieve information (In)
            DataInputStream in = new DataInputStream(cs.getInputStream());
            // Tunnel to send information (Out)
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            
            String mensaje = in.readUTF();
            System.out.println(mensaje);
            
            try(Scanner entry = new Scanner(System.in)){
                while(true) {
                    System.out.println("Write a message (END OF SERVICE to close): ");
                    String message = entry.nextLine();
                    out.writeUTF(message);
                    if(message.equalsIgnoreCase("END OF SERVICE")) break;
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

