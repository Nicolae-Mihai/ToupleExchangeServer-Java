package com.mycompany.replica1_3;

import com.mycompany.linda.Tuple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Replica1_3Thread extends Thread{
	private int id;
	private Socket cs;
	public List<Tuple> database;
	
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
				int option = getOption(message);
				Tuple tuple = tuplifyer(message);
				out.writeUTF(messagefyer(tuple));
			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private int getOption(String message){
		int option;
		String [] words = message.split(",");
		option = Integer.parseInt(words[0]);
		return option;
	}

	private Tuple tuplifyer(String message){
		String [] words = message.split(",");
		String[] array = new String[words.length -1];
		for(int i = 0; i < words.length-1; i++){
			array[i] = words[i+1];
		}
		Tuple tuple = new Tuple(array);

		return tuple;
	}

	private String messagefyer(Tuple tuple){
		String message = "";
		int count = 1;
		for(String s : tuple.getElem()){
			if(count == tuple.getElem().size()) message += s;
			else message += s+",";
			count++;
		}

		return message;
	}
	//todo: esto va a llegar en forma [a,b]
	private ArrayList<Tuple> findNote(Tuple tuple){
		ArrayList<String> searchValues = new ArrayList<>();
		List<String> elem = tuple.getElem();

		//We stash the values we are told to search with its position for later usage
		int pointer = 0;
		for(String word : elem){
			if(!word.matches("[a-zA-Z]\\?")){
				searchValues.add(word+pointer);
			}
		}

		//Now we proceed to search the tuples with those values in the indicated position
		ArrayList<Tuple> result = new ArrayList<>();
		for(Tuple t : this.database){
			for(String val : searchValues){
				int position = val.charAt(val.length() - 1);
				String word = val.substring(0, val.length() - 1);
				if(t.getElem().get(position).equalsIgnoreCase(word)) result.add(t);
			}
		}

		return result;
	}

	//This "cleans" the message off the instruction
	private Tuple toTuple(String message){
		String[] aux = message.split(",");
		ArrayList<String> aux2 = new ArrayList<>();
		for(int i = 0; i < aux.length; i++){
			aux2.add(aux[i]);
		}
		//We here create an array of the aux2 size because java wont get it any other way
		String[] words = aux2.toArray(new String[aux2.size()]);
		Tuple tuple = new Tuple(words);

		return tuple;
	}
}
