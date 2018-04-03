package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class LoginThread implements Runnable {
	
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 

	public LoginThread(ObjectInputStream oIn, ObjectOutputStream oOut, 
				BufferedReader sIn, PrintWriter sOut) { 
		objectIn = oIn; 
		objectOut = oOut; 
		stringIn = sIn; 
		stringOut = sOut; 
	}
	
	public void run() { 
		try { 
			System.out.println("Client and server connected");
			String userName = null, userPassword = null; 
			userName = stringIn.readLine();
			userPassword = stringIn.readLine();
			//userName = userName.substring(4);
			System.out.println(userName + " " + userPassword);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
}
