package com.mycompany.serv1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.mycompany.replica1_3.ConnectionReplica;


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
				
				ConnectionReplica replica = new ConnectionReplica("client");
        		servIDK(replica.getCs(), message);
				
        		if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
        		System.out.println("Message recieved -> "+message+"by Serv1_3"+id);
				out.writeUTF("Recieved by Serv1_3"+id+" -> "+message);
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void servIDK(Socket servSocket,String words) throws IOException{
		DataInputStream in= new DataInputStream(servSocket.getInputStream());
		DataOutputStream out= new DataOutputStream(servSocket.getOutputStream());
		String message= in.readUTF();
		System.out.println(message);
		out.writeUTF(words);
		
}
}
