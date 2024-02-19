package com.mycompany.crud;

import com.mycompany.linda.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Crud extends Thread {
	Semaphore deleteSemaphore=new Semaphore(1);
	Semaphore insertSemaphore=new Semaphore(1);
	
	public int getOption(String message){
        int option;
        String [] words = message.split(",");
        option = Integer.parseInt(words[0]);
        return option;
    }

    public Tuple tuplifyer(String message){
        String [] words = message.split(",");
        String[] array = new String[words.length -1];
        for(int i = 1; i < words.length; i++){
            array[i - 1] = words[i];
        }
        Tuple tuple = new Tuple(array);

        return tuple;
    }

    public String messagefyer(Tuple tuple){
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
    public ArrayList<Tuple> findNote(Tuple tuple, List<Tuple> database){
        ArrayList<String> searchValues = new ArrayList<>();
        List<String> elem = tuple.getElem();

        //We stash the values we are told to search with its position for later usage
        int pointer = 0;
        for(String word : elem){
            if(!word.matches("[a-zA-Z]\\?")){
                searchValues.add(word+pointer);

            }
            pointer++;
        }

        //Now we proceed to search the tuples with those values in the indicated position
        ArrayList<Tuple> result = new ArrayList<>();
        
        synchronized (database) {
        	if(insertSemaphore.availablePermits()==1 && deleteSemaphore.availablePermits()==1) {
        		
	        	for(Tuple t : database){
	        		for(String val : searchValues){
	        			int  position = Integer.parseInt(val.substring(val.length() - 1));
	        			String word = val.substring(0, val.length() - 1);
	        			if(t.getElem().get(position).equalsIgnoreCase(word)) result.add(t);
	        		}
	        	}
        	}
        }
        return result;
    }

    public List<Tuple> deleteNote(List<Tuple> tupleList, List<Tuple> database){
        
    	synchronized (database) {
			if(insertSemaphore.availablePermits()==1)
				try {
					deleteSemaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
    	for(int i=0;i<database.size();i++)
    		for(Tuple tupleForDeletion : tupleList){
    			if (tupleForDeletion.equals(database.get(i))){
    				database.remove(database.get(i));
    			}
    		}
    	deleteSemaphore.release();
        return database;
    }
    //The addNote method contemplates the redundance of tuples in the database
    public List<Tuple> addNote(Tuple t, List<Tuple> database){
        synchronized(database) {
        	if(deleteSemaphore.availablePermits()==1)
				try {
					insertSemaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	database.add(t);
        	insertSemaphore.release();
        }

        return database;
    }


}
