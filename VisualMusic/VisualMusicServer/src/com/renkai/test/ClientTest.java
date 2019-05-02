package com.renkai.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.renkai.repertory.LoginInfo;

public class ClientTest {

	public static void main(String[] args) throws Exception {  
        
        Socket socket = null;  
        ObjectOutputStream os = null;  
        ObjectInputStream is = null;  
          
        try {  
            socket = new Socket("localhost", 10000);  
  
            os = new ObjectOutputStream(socket.getOutputStream());  
            LoginInfo user = new LoginInfo();  
            os.writeObject(user);  
            os.flush();  
              
           
        } catch(IOException ex) {  
          
        } finally {  
            try {  
                is.close();  
            } catch(Exception ex) {}  
            try {  
                os.close();  
            } catch(Exception ex) {}  
            try {  
                socket.close();  
            } catch(Exception ex) {}  
        }  
      
}  
}
