package com.mycompany.serv6;

import com.mycompany.crud.Crud;
import com.mycompany.linda.Tuple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Serv6Thread extends Crud {
	private int id;
	protected Socket cs;
	public List<Tuple> database;
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
				String[] words=message.split(",");
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
				switch (words[0]) {
					case "1": //insert note
						out.writeUTF("serv6"+id+" inserted -> "+this.messagefyer(this.tuplifyer(message)));
						break;
					
					case "2": //read note
						// change the message to the
						out.writeUTF("the result of your search is:\n"+this.findNote(this.tuplifyer(message), database));
						
						break;
					
					case "3"://delete note
						out.writeUTF("serv6"+id+" deleted -> "+this.messagefyer(this.tuplifyer(message)));
						break;
	
					default:
						break;
				}
				
				if(words[0].equalsIgnoreCase("1"))
					out.writeUTF("serv6"+id+" recieved -> "+message);
				System.out.println("Message recieved -> "+ message+" by Serv6"+id);
				out.writeUTF("Recieved -> "+message);
			
			}
			
			cs.close();
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
	}
}
