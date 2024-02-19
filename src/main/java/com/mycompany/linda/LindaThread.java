package com.mycompany.linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.mycompany.crud.Crud;
import com.mycompany.replica1_3.ConnectionReplica;
import com.mycompany.serv1_3.ConnectionServ1_3;
import com.mycompany.serv4_5.ConnectionServ4_5;
import com.mycompany.serv6.ConnectionServ6;



public class LindaThread extends Crud {
	
	private int id;
	private Socket cs;
	private ConnectionServ1_3 serv1_3;
	private ConnectionReplica replica;
	private ConnectionServ4_5 serv4_5;
	private ConnectionServ6 serv6;
	
	public LindaThread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;

	}
	
	@Override
	public void run() {
	
		try {
			String[] words= {};
			System.out.println("Client online");
	        DataInputStream in = new DataInputStream(cs.getInputStream());
	        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	        
	        //Sends a message to the client using its own tunnel
	        out.writeUTF("Request recieved and accepted");
			
			while(true) {
				
				//calls the method check
				if(this.cs.isConnected()) {
					String message = in.readUTF();
					check();
//					checkLoss();
					words=message.split(",");
					System.out.println(words.length);
					
		        	if(message.equalsIgnoreCase("Not a choice"))
		        		out.writeUTF("");
		        	
		        	if(1 < words.length && words.length<5) {
//			        	checks to see if either serv1_3 or replica is down,if the check is not made then both servers will respond at the same time and the servers will lag
						if(serv1_3!=null || replica!=null) 
							if(serv1_3!=null) {
								out.writeUTF(servIDK(serv1_3.getCs(), message));
								if(replica!=null)
									servIDK(replica.getCs(), message);
							}else if(replica!=null) 
								out.writeUTF(servIDK(replica.getCs(), message));
						
		        	}else if(4<words.length && words.length<7){
						out.writeUTF(servIDK(serv4_5.getCs(), message));
		        	}else if(7<=words.length && words.length<8){
	        			out.writeUTF(servIDK(serv6.getCs(), message));
					}else if(8<words.length){
						System.out.println("Please limit yourself to only 6 touples max!");
					}
		        	
		        	if(message.equalsIgnoreCase("END OF SERVICE")) break;
		            
		        	System.out.println("Message received -> " + message + " by Linda" + id);
				}
			}
	        cs.close();// Ends the connection with the client
		} catch (IOException exception) {
			System.out.println("Client disconnected!");
		}
	}

//Pass the message to the server so you don't have to do it in every if statement
	
	private synchronized String servIDK(Socket servSocket,String words) throws IOException{
			DataInputStream in = new DataInputStream(servSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(servSocket.getOutputStream());
			try {
				out.writeUTF(words);
				String response= in.readUTF();
				System.out.println(response);
				return response;
				
			} catch (Exception e) {
				System.out.println("Can't connect to the server whose port is: "+servSocket.getPort());
				servSocket.close();
			}
			return "";
	}
	
//	checks to see if there are any servers that down and if they are down attempts to connect to them 
	private void check() {
		
		if(this.serv1_3 == null || this.serv1_3.getCs().isClosed() == true)
			try{this.serv1_3=new ConnectionServ1_3("client");}
				catch (Exception e) {System.out.println("Serv1_3 is offline");}
		
		if(this.replica == null || this.replica.getCs().isClosed() == true)
			try {this.replica = new ConnectionReplica("client");} 
				catch (Exception e) {System.out.println("Replica is offline");}

		if(this.serv4_5 == null || this.serv4_5.getCs().isClosed() == true)	
			try {this.serv4_5 = new ConnectionServ4_5("client");} 
				catch (Exception e) {System.out.println("Serv4_5 is offline");}
		
		if(this.serv6 == null || this.serv6.getCs().isClosed() == true)
			try {this.serv6 = new ConnectionServ6("client");} 
				catch (Exception e) {System.out.println("Serv6 is offline");}
		
	}
}
