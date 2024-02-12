package com.mycompany.linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class LindaThread extends Thread{
	
	private int id;
	private Socket cs;
	
	public LindaThread(int id,Socket cs) {
		
		this.id=id;
		this.cs=cs;
	}
	
	@Override
	public void run() {
	
		try {
		
			System.out.println("Client online");
	        DataInputStream in;
			in = new DataInputStream(cs.getInputStream());
	        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	
	        //Sends a message to the client using it's own tunnel
			out.writeUTF("Request recieved and accepted");
			
	        while(true) {
	    
	        	String message = in.readUTF();
	
	            if(message.equalsIgnoreCase("END OF SERVICE")) break;
	            System.out.println("Message received -> " + message + " by Linda" + id);
	            out.writeUTF("Received -> " + message);
	        }
	       	
	        cs.close();// Ends the connection with the client
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
