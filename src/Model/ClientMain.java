package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Controller.*;
import Data.User;

public class ClientMain {
	
	private Socket socket; 
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 
	private ObjectInputStream objectIn; 
	private ObjectOutputStream objectOut; 
	private ProfessorModel professorModel; 
	
	public ClientMain(String serverName, int portNumber) {
		try { 
			socket = new Socket(serverName, portNumber);
			stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stringOut = new PrintWriter((socket.getOutputStream()), true);
			objectIn = new ObjectInputStream(socket.getInputStream());
			objectOut = new ObjectOutputStream(socket.getOutputStream());
			professorModel = new ProfessorModel(stringIn, stringOut, objectIn, objectOut);
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	public ObjectOutputStream getObjectOut() {
		return objectOut; 
	}
	
	public ObjectInputStream getObjectIn() {
		return objectIn;
	}
	
	public PrintWriter getStringOut() {
		return stringOut; 
	}
	
	public BufferedReader getStringIn() {
		return stringIn; 
	}
	
	public ProfessorModel getProfessorModel() {
		return professorModel; 
	}
	
	public void sendUserName(String userName) {
		stringOut.println(userName);
	}
	
	public void sendPassword(String password) {
		stringOut.println(password);
	}
	
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
