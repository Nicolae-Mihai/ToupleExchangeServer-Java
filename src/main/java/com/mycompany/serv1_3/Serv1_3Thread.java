package com.mycompany.serv1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;




public class Serv1_3Thread extends Thread{
	
	private int id;
	private Socket cs;
	
	public Serv1_3Thread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;
	}
	
	@Override
	public void run(){
		try {
			System.out.println("Connection accepted");
			DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			
			out.writeUTF("connected to Serv1_3"+id);
			while(true) {
				String message= in.readUTF();
				
        		if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
        		System.out.println("Message recieved -> "+message+" by Serv1_3"+id);
				out.writeUTF("Recieved by Serv1_3"+id+" -> "+message);
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
