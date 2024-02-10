/*
 * 
 */
package com.mycompany.linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javafx.util.Pair
/**
 *
 * @author gusti
 */
public class LindaThread extends Linda implements Runnable {
    String name;
  
    public LindaThread(String name) throws IOException{
        this.name=name;
    }

    @Override
    public void run(){
        try{
            cs = ss.accept(); //Accept starts the socket and waits for a connexion from a client
            System.out.println("Client online");

            DataInputStream in = new DataInputStream(cs.getInputStream());
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            
            //Sends a message to the client using it's out tunnel
            out.writeUTF("Request recieved and accepted by "+ name);
            while(true) {
	            String message = in.readUTF();
	            if(message.equalsIgnoreCase("END OF SERVICE")) break;
//	            System.out.println("Message recieved -> " + message);
//	            out.writeUTF("Vocals en: \"" + message + "\" : " + calculateVocals(message));
            }
            cs.close();//Se finaliza la conexión con el cliente
        } catch(Exception e){}
    }
    
    private int toupleLen(String message){
        int len=0;
        Pair<String,Integer> pair=pair.with("miau",11);
        return len;
    }
}
