package com.mycompany.serv1_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServ1_3 {
	private final int PORT = 1235;
	private final String HOST = "localhost";
	protected ServerSocket ss;
	protected Socket cs;
	
	public ConnectionServ1_3(String type) throws IOException{
		if (type.equalsIgnoreCase("server")) {
			ss = new ServerSocket(PORT);
		}else {
			cs=new Socket(HOST,PORT);
		}
	}
}
