// this method is supposed to start all the servers at once (still needs some work)
package com.mycompany.linda;

import java.io.IOException;

import com.mycompany.replica1_3.Replica1_3;
import com.mycompany.serv1_3.Serv1_3;
import com.mycompany.serv4_5.Serv4_5;
import com.mycompany.serv6.Serv6;

public class RunServers {
	public static void main(String args[]) throws IOException{
			
		Replica1_3 replica1_3 = new Replica1_3();
		replica1_3.startReplica1_3();
		Linda linda=new Linda();
		linda.startLinda();
		Serv1_3 serv1_3=new Serv1_3();
		serv1_3.startServ1_3();
		Serv4_5 serv4_5=new Serv4_5();
		serv4_5.startServ4_5();
		Serv6 serv6=new Serv6();
		serv6.startServ6();
	}
}
