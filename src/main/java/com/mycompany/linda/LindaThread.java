package com.mycompany.linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.mycompany.replica1_3.ConnectionReplica;
import com.mycompany.serv1_3.ConnectionServ1_3;
import com.mycompany.serv4_5.ConnectionServ4_5;
import com.mycompany.serv6.ConnectionServ6;



public class LindaThread extends Thread {
	
	private int id;
	private Socket cs;
	public LindaThread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;

	}
	
	@Override
	public void run() {
	
		try {
			String[] words= {};
			System.out.println("Client online");
	        DataInputStream in;
			in = new DataInputStream(cs.getInputStream());
	        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	
	        //Sends a message to the client using it's own tunnel
	        out.writeUTF("Request recieved and accepted");
	        
			
	        
			while(true) {
	    
	        	String message = in.readUTF();
	        	words=message.split(",");
	        	
//	        	for (String string : words) 
//					System.out.println(string+" comma ");
	        	//this is here for testing purposes 
	        	
//	        	Tuple tuple=new Tuple(words[0], words[1], words[2], words[3]);
	        	System.out.println();
//	        	tuple.print();
	        	
	        	if(0 < words.length && words.length<4) {
	        		ConnectionReplica replica = new ConnectionReplica("client");
	        		servIDK(replica.getCs(), message);
//	        		ConnectionServ1_3 serv1_3 = new ConnectionServ1_3("client");
	        	}else if(3<words.length || words.length<6){
					ConnectionServ4_5 serv4_5=new ConnectionServ4_5("clinet");
				}else {
					ConnectionServ6 serv6=new ConnectionServ6("client");
				}
	        	if(message.equalsIgnoreCase("END OF SERVICE")) break;
	            
	        	System.out.println("Message received -> " + message + " by Linda" + id);
	            out.writeUTF("Received -> " + message);
	        }
	       	
	        cs.close();// Ends the connection with the client
		} catch (IOException e) {
			e.printStackTrace();
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
