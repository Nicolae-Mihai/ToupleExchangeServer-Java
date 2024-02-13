package com.mycompany.replica1_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionReplica {
	private final int PORT=1238;
	private final String HOST="localhost";
	protected ServerSocket ss;
	protected Socket cs;
	
	public ConnectionReplica(String type) throws IOException{
		if(type.equalsIgnoreCase("server")) {
			ss=new ServerSocket(PORT);
		}else {
			cs=new Socket(HOST,PORT);
		}
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	public Socket getCs() {
		return cs;
	}

	public void setCs(Socket cs) {
		this.cs = cs;
	}
	
}
