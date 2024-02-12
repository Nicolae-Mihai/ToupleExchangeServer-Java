/*
 *The second server manages tuple operations that have a length of 4-5
 * This exercise consists in creating a Coordinated Distributed system LINDA.
 *The system must include:
 *   * PostNote (PN)
 *   * RemoveNote (RN)
 *   * ReadNote (ReadN) 
 *A template will be a special tuple type in which variables can appear, only 
 *with RN or ReadN operations.Example:
 * ["John","20","Married"] is a simple 3 element tuple
 * ["John","?X","?Y"] is a simple 3 element tuple with 2 variables,"X" and "Y".
 *Internally in every one of these servers the tuples are stored in memory.
 */
package com.mycompany.serv4_5;

import java.io.IOException;

public class Serv4_5 extends Connection{

	public Serv4_5() throws IOException {
		super("server");	
	}
	
	public void startServ4_5() {
		try {
			System.out.println("Waiting");
			int i=0;
			while (true) {
				cs = ss.accept();
				Serv4_5Thread serv4_5=new Serv4_5Thread(i, this.cs);
				serv4_5.start();
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
