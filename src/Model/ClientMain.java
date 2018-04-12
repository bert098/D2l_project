package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Controller.*;
import Data.Constants;
import Data.User;

/**
 * Client main initializes a connection with the server through sockets and constructs 
 * the input and output streams for strings and objects. Also handles the login client server
 * interactions.
 * @author Justin, Magnus, Robert
 */
public class ClientMain implements Constants {
	
	/**
	 * socket connection to server 
	 */
	private Socket socket; 
	
	/**
	 * Read string input from server 
	 */
	private BufferedReader stringIn; 
	
	/**
	 * Write string to server 
	 */
	private PrintWriter stringOut; 
	
	/**
	 * Read Object input from server 
	 */
	private ObjectInputStream objectIn; 
	
	/**
	 * Write object to server 
	 */
	private ObjectOutputStream objectOut; 
	
	/**
	 * Professor model serves as the client server connection between the professor thread 
	 * and the professor client. 
	 */
	private ProfessorModel professorModel; 
	
	/**
	 * Student model serves as the client server connection between the student thread 
	 * and the student client. 
	 */
	private StudentModel studentModel; 
	
	/**
	 * Construct the client by connecting the sockets to the specified 
	 * server name and port. Sets up connection of the input and output stream for
	 * objects and strings
	 * @param serverName Name of server 
	 * @param portNumber Port of server 
	 */
	public ClientMain(String serverName, int portNumber) {
		try { 
			socket = new Socket(serverName, portNumber);
			stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stringOut = new PrintWriter((socket.getOutputStream()), true);
			objectIn = new ObjectInputStream(socket.getInputStream());
			objectOut = new ObjectOutputStream(socket.getOutputStream());
			professorModel = new ProfessorModel(stringIn, stringOut, objectIn, objectOut);
			studentModel = new StudentModel(stringIn, stringOut, objectIn, objectOut);
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * Get student model
	 * @return student model
	 */
	public StudentModel getStudentModel() { 
		return studentModel;
	}
	
	/**
	 * Get professor model 
	 * @return professor model
	 */
	public ProfessorModel getProfessorModel() {
		return professorModel; 
	}
	
	/**
	 * Write username to server 
	 * @param userName username being sent 
	 */
	public void sendUserName(String userName) {
		stringOut.println(userName);
	}
	
	/**
	 * Write password to server 
	 * @param password password being sent 
	 */
	public void sendPassword(String password) {
		stringOut.println(password);
	}
	
	/**
	 * Read user from server 
	 * @return return user 
	 */
	public User readUser(){ 
		try {
			return (User) objectIn.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
