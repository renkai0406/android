package com.renkai.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

import com.renkai.socket.MyServerSocket;
import com.renkai.util.Config;

public class MainServer {

	public static LinkedList<MyServerSocket> client_list = new LinkedList<MyServerSocket>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(Config.PORT);
			
			while(true) {
				MyServerSocket mySocket = new MyServerSocket(server.accept());
				Thread client = new Thread(mySocket);
				client.start();
				client_list.add(mySocket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
