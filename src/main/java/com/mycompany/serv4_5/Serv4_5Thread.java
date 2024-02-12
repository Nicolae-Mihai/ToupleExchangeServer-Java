package com.mycompany.serv4_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Serv4_5Thread extends Thread{
	
	private int id;
	private Socket cs;
	
	public Serv4_5Thread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Client Online");
			DataInputStream in=new DataInputStream(cs.getInputStream());
			DataOutputStream out= new DataOutputStream(cs.getOutputStream());
			
			out.writeUTF("Request recieved and accepted by Serv4_5"+id);
			while (true) {
				String message=in.readUTF();
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				System.out.println("Message recieved -> "+message+" by Serv4_5"+id);
				out.writeUTF("Recieved ->"+message);
				
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
