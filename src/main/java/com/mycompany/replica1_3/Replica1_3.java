/*
 * Let's suppose that the server managing the 1-3 touple length needs an extra 
 * security level,given how we don't want to lose the stored data, in order to 
 * avoid losing data we need to create a replica server
 * if the primary server 
 * Is down, the replica will recieve the operations intended for the primary 
 * server and it will keep the system functional.
 */
package com.mycompany.replica1_3;

import java.io.IOException;

/**
 *
 * @author gusti
 */
public class Replica1_3 extends Connection{
 
	public Replica1_3() throws IOException{
    	super("server");
    }
	
	public void startReplica1_3() {
		try {
			System.out.println("Waiting...");
			int i=0;
			while(true) {
				cs=ss.accept();
				Replica1_3Thread replica1_3Thread=new Replica1_3Thread(i, this.cs);
				replica1_3Thread.start();
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
