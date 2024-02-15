/*
 * The client must be able to do any of the following operations:
 *   - Connect to the service
 *   - Disconnect from the service
 *   - Any of the three basic operations with tuples
 */

package com.mycompany.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client extends ConnexionClient {
	private boolean running=true;
	private boolean rnr;
	private boolean dnr;
	private String[] wildCard= {"A","B","C","D","E","F","G","H","I","J","K"};
    public Client() throws IOException {
    	super("client");
    } //We use the constructor for client from Connection

    public void startClient() {//Method to start the client message
        try {
        	// Tunnel to receive information (In)
        	DataInputStream in = new DataInputStream(cs.getInputStream());
        	// Tunnel to send information (Out)
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            
            String message = in.readUTF();
            System.out.println(message);
            
            try (Scanner entry = new Scanner(System.in)) {
            	while(true) {
            		int wci=0;
					System.out.println("\nWelcome what action do you wish to take?(please insert only the number)\n 1.Insert a Note!\n 2.Read a Note\n 3.Delete a note!\n 4.Exit client!");
					String strin = entry.nextLine();
					String tuple="";
					
					switch (strin) {
						case "1": //insert note
							System.out.println("You chose to insert a note!\n Write a tuple and separate the elements by commas(',') ");
							tuple=entry.nextLine();
							out.writeUTF("1,"+tuple);
								
							break;
//						Write a tuple and press enter!\n In the fields where you don't know that to put just fill them with\n by a capital letter(Do not repeat them!)  followed  a question mark(?)\n  
						case "2": //read note
							int element=1;
							rnr=true;
								while(rnr) {
									System.out.println("You chose to read a note!\n do you know what the element "+element+" is?(Please write only the number)\n 1.Yes!\n 2.No!\n 3.Stop inserting");
									tuple+=readNote(entry.nextLine(),wci);
									element++;
//									tuple=entry.nextLine();
								}
							out.writeUTF("2"+tuple);
							break;
							
						case "3": // delete note
							element=1;
							dnr=true;
							while (dnr) {
								
								System.out.println("\nYou chose to delete a note!\n Do you know what the element "+element+" is?(Please write only the number)\n 1.Yes!\n 2.No!\n 3.Stop inserting.");
								tuple+=deleteNote(entry.nextLine(),wci);//entry.nextLine();
								element++;
							}
							out.writeUTF("3"+tuple);
							break;
							
						case "4":// exit client
							running=false;
							break;
							
						default:
							System.out.println("The option you picked is not available");
							out.writeUTF("Not a choice");
							break;
					}
					if(!running) {
						System.out.println("Good Bye! Hope I can see you soon you cutie!");
						break;
					}
					System.out.println(in.readUTF());
				}
			}
            cs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private String readNote(String entry,int wci) {
    	
    	switch (entry) {
			case "1": //yes
				System.out.println("Please write the element!\n");
				Scanner elementIn=new Scanner(System.in);
				String element=","+elementIn.nextLine();
				return element;
			
			case "2": //no
				String variable = wildCard[wci];
				wci++;
				return ","+variable+"?";

			case "3": //stoppu
				rnr=false;
				break;
	
			default:
				System.out.println("That is not an option!");
		}
    	return "";
		
	}
    private String deleteNote(String entry,int wci) {
    	
    	switch (entry) {
			case "1": //yes
				System.out.println("Please write the element!\n");
				Scanner elementIn=new Scanner(System.in);
				String element=","+elementIn.nextLine();
				return element;
			
			case "2": //no
				String variable = wildCard[wci];
				wci++;
				return ","+variable+"?";

			case "3": //stoppu
				dnr=false;
				break;
	
			default:
				System.out.println("That is not an option!");
		}
    	return "";
		
	}
}