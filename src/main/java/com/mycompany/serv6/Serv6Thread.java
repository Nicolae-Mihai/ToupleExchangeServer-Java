package com.mycompany.serv6;

import com.mycompany.crud.Crud;
import com.mycompany.linda.Tuple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Serv6Thread extends Crud {
	private int id;
	protected Socket cs;
	public List<Tuple> database = new ArrayList<>();
	public Semaphore semread = new Semaphore(1);
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
			
			while(true) {
				String message = in.readUTF();
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
				System.out.println("Message recieved -> "+message+" by Serv6"+id);
//				this switch checks for the type of action it should take and depending on it returns a different message
//				to Linda which can in return send that information to the client

				//Extract the option from the message
				int option = getOption(message);

				//Transform the message into a tuple
				Tuple tuple = tuplifyer(message);

				//Switch case for the options
				switch (option){

					//Case for addNote
					case 1:
						this.database = addNote(tuple, database);
						out.writeUTF("Tuple added succesfully");
						break;
					//Case for addNote
					case 2:
						semread.acquire();
						ArrayList<Tuple> result = findNote(tuple, database);
						semread.release();
						if(result.isEmpty()) 
							out.writeUTF("Not a touple with those characteristcs was found");
						
						else{
							//Returns the message of every tuple in the result if not empty
							String list="Your search results are the following:\n";
							
							for(Tuple t : result){
								list+=messagefyer(t)+"\n";
							}
							out.writeUTF(list);
						}

						break;

					//Case for deleteNote
					case 3:
						semread.acquire();
						List<Tuple> results = findNote(tuple, database);
						this.database = deleteNote(results, database);
						semread.release();
						out.writeUTF("Coincident results were deleted");
				}
			}
			
			cs.close();
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
