package com.mycompany.serv1_3;

import com.mycompany.crud.Crud;
import com.mycompany.linda.Tuple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Serv1_3Thread extends Crud {
	
	private int id;
	private Socket cs;
	List<Tuple> database = new ArrayList<>();
	public Semaphore semread = new Semaphore(1);
	public Serv1_3Thread(int id,Socket cs) {
		this.id=id;
		this.cs=cs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run(){
		try {
			System.out.println("Connection accepted");
			DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			
			while(true) {
				String message= in.readUTF();
				
        		if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
        		System.out.println("Message recieved -> "+message+" by Serv1_3"+id);

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
						if(result.isEmpty()) out.writeUTF("Not a touple with those characteristcs was found");
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
						break;
					case 4:
						ObjectOutputStream objectOutput = new ObjectOutputStream(cs.getOutputStream());
			            objectOutput.writeObject(database);
					case 5:
						ObjectInputStream objectInput = new ObjectInputStream(cs.getInputStream());
						this.database=(List<Tuple>) objectInput.readObject();
				}
			}
			cs.close();
		} catch (Exception e) {
			
		}
	}
}
