package com.mycompany.replica1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Replica1_3Thread extends Thread{
	private int id;
	private Socket cs;
	
	public Replica1_3Thread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Client Online");
			DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			out.writeUTF("Request received and accepted by Replica1_3"+this.id);
			while(true) {
				String message=in.readUTF();
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				System.out.println("Message recieved -> "+message+" by Replica1_3"+id);
				out.writeUTF("Recieved -> "+message);
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
