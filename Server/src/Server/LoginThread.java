package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Data.User;
import Data.Constants;
import Database.DatabaseHelper;

/**
 * Login thread class handles the login screen server management for the client. 
 * Checks user login information in data base and sends back the user class to the client. 
 * @author Justin, Robert, Magnus
 */
public class LoginThread implements Runnable, Constants {
	
	/**
	 * Receive objects from client
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * Write objects to client
	 */
	private ObjectOutputStream objectOut;
	
	/**
	 * Receive string input from client 
	 */
	private BufferedReader stringIn; 
	
	/**
	 * Write string output to client 
	 */
	private PrintWriter stringOut; 
	
	/**
	 * Database helper class that contains access methods to all data tables 
	 */
	private DatabaseHelper database; 

	/**
	 * Construct login thread with socket connection with the client 
	 * @param oIn objectIn stream
	 * @param oOut objectOut stream
	 * @param sIn receive string
	 * @param sOut write string
	 * @param data database 
	 */
	public LoginThread(ObjectInputStream oIn, ObjectOutputStream oOut, 
				BufferedReader sIn, PrintWriter sOut, DatabaseHelper data) { 
		database = data;
		objectIn = oIn; 
		objectOut = oOut; 
		stringIn = sIn; 
		stringOut = sOut; 
	}
	
	/**
	 * Runs the login thread by waiting for user login username and password returns resulting user for username
	 * and password back to client.
	 */
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
			if (user.getType() == PROFESSOR) {
				ProfessorThread profThread = new ProfessorThread(stringIn, stringOut, objectOut, objectIn, database);
				profThread.run();
			}
			else if (user.getType() == STUDENT) {
				StudentThread studentThread = new StudentThread(stringIn, stringOut, objectOut, objectIn, database);
				studentThread.run(); 
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
