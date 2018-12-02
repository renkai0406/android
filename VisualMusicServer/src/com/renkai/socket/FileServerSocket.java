package com.renkai.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.renkai.util.Config;

public class FileServerSocket {
	
	private ServerSocket file_server;
	private Socket file_client;
	private DataOutputStream output;
	private DataInputStream input;
	private int buf_size = 1024;
	
	public void init() {
		try {
			file_server = new ServerSocket(Config.FILE_PORT);
			file_client = file_server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getMessage(String name) {
		try {
			int read_size = 0;
			byte[] buf = new byte[buf_size];
			File lyric_file = new File(name);
			input = new DataInputStream(new BufferedInputStream(file_client.getInputStream()));
			DataOutputStream file_output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(lyric_file)));
			while(read_size != -1) {
				read_size = input.read(buf);
				file_output.write(buf);
			}
			file_output.flush();
			file_output.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMessage(String name) {
		try {
			int read_size = 0;
			byte[] buf = new byte[buf_size];
			output = new DataOutputStream(new BufferedOutputStream(file_client.getOutputStream()));
			File file = new File(name);
			DataInputStream file_input = new DataInputStream(new FileInputStream(file));
			while((read_size = file_input.read(buf)) != -1) {
				System.out.println("服务端开始执行");
				output.write(buf, 0, buf_size);
			}
			output.flush();
			file_input.close();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
