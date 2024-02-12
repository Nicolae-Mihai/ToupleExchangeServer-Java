/*The last server is for touples with a length of 6. 
 * This exercise consists in creating a Coordonation Distributed system LINDA.
 *The system must include:
 *   * PostNote (PN)
 *   * RemoveNote (RN)
 *   * ReadNote (ReadN) 
 *A template will be a special touple type in which variables can appear, only 
 *with RN or ReadN operations.Example:
 * ["John","20","Maried"] is a simple 3 element touple
 * ["John","?X","?Y"] is a simple 3 element touple with 2 variables,"X" and "Y".
 *Internaly in every one of these servers the touples are stored in memory.
 */
package com.mycompany.serv6;

import java.io.IOException;

/**
 *
 * @author gusti
 */
public class Serv6 extends Connection{
    
	public Serv6() throws IOException {
    	super("serv");
    }
	
	public void startServ6() {
		try {
			System.out.println("Waiting...");
			int i=0;
			while(true) {
				cs=ss.accept();
				Serv6Thread serv6Thread = new Serv6Thread(i, this.cs);			
				serv6Thread.start();
				i++;
			}
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
}
