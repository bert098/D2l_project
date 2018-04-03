package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import View.LoginWindow;

public class ClientMain {
	
	private Socket socket; 
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 
	private ObjectInputStream objectIn; 
	private ObjectOutputStream objectOut; 
	private UserController userController; 
	
	public ClientMain(String serverName, int portNumber) {
		try { 
			socket = new Socket(serverName, portNumber);
			stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stringOut = new PrintWriter((socket.getOutputStream()), true);
			objectIn = new ObjectInputStream(socket.getInputStream());
			objectOut = new ObjectOutputStream(socket.getOutputStream());
			userController = new UserController(stringOut); 
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	public static void main(String[] args) {
		ClientMain theClient = new ClientMain("localhost", 6969); 
	}
}
