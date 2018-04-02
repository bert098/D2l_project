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
		userController = new UserController(); 
		try { 
			socket = new Socket(serverName, portNumber);
			stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stringOut = new PrintWriter((socket.getOutputStream()), true);
			objectIn = new ObjectInputStream(socket.getInputStream());
			objectOut = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	public void getUser() {
		String userName = ""; 
		String password = "";
		while (true) {
			userName = userController.getUserName();
			password = userController.getPassword();
			System.out.println("in loop: " + userName + password);
			if (!userName.equals("") && !password.equals("")) { 
				break;
			}
		}
		System.out.println("userName: " + userName + "password: " + password);
		stringOut.println(userName);
		stringOut.flush();
		stringOut.println(password);
		System.out.println("user and password sent to server");
	}
	
	public static void main(String[] args) {
		ClientMain theClient = new ClientMain("localhost", 6969); 
		theClient.getUser(); 
	}
}
