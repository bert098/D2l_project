package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Data.User;
import Database.DatabaseHelper;

public class LoginThread implements Runnable {
	
//	private EmailHelper emailHelper; 
//	private FileHelper fileHelper; 
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 
	private DatabaseHelper database; 

	public LoginThread(ObjectInputStream oIn, ObjectOutputStream oOut, 
				BufferedReader sIn, PrintWriter sOut, DatabaseHelper data) { 
		database = data;
		objectIn = oIn; 
		objectOut = oOut; 
		stringIn = sIn; 
		stringOut = sOut; 
	}
	
	public void run() { 
		try {
			User user = null;
			while (user==null) {
				String userName = null, userPassword = null; 
				userName = stringIn.readLine();
				userPassword = stringIn.readLine();
				System.out.println(userName + " " + userPassword);
				user = database.searchUser(userName, userPassword);
				objectOut.reset();
				objectOut.writeObject(user);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
