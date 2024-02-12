package com.mycompany.serv6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Serv6Thread extends Thread{
	private int id;
	protected Socket cs;
	
	public Serv6Thread(int id,Socket cs) {
		this.cs=cs;
		this.id=id;
	}
	@Override
	public void run() {
		try {
			System.out.println("Client online");
			DataInputStream in=new DataInputStream(cs.getInputStream());
			DataOutputStream out= new DataOutputStream(cs.getOutputStream());
			out.writeUTF("Request received and accepted by Serv6"+id);
			
			while(true) {
				String message = in.readUTF();
				
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				System.out.println("Message recieved -> "+ message+" by Serv6"+id);
				out.writeUTF("Recieved -> "+message);
			
			}
			
			cs.close();
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
	}
}
