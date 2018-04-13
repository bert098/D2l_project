package Server; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Database.DatabaseHelper;
import Database.DatabaseSetterUpper;

/**
 * Main server class connects server with client through sockets and 
 * waits for a connection from the client side to runs a login thread for the client 
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 */
public class MainServer {
	
	/**
	 * Database manager for the server 
	 */
	private DatabaseHelper database;

	/**
	 * socket to connect to client 
	 */
	private Socket socket;
	
	/**
	 * thread pool to handle multiple clients 
	 */
	private ExecutorService threadPool; 
	
	/**
	 * socket to connect to client 
	 */
	private ServerSocket serverSocket;
	
	/**
	 * output stream to send objects to client 
	 */
	private ObjectOutputStream objectOut; 
	
	/**
	 * input stream to receive objects from client 
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * receive string input from client 
	 */
	private BufferedReader stringIn; 
	
	/**
	 * write string output to client 
	 */
	private PrintWriter stringOut;
	
	
	/**
	 * Construct a Server with Port 6969
	 * @param data DatabaseHelper
	 */
	public MainServer(DatabaseHelper data) {
		try {
			database = data;
			serverSocket = new ServerSocket(6969);
			threadPool = Executors.newCachedThreadPool();
			System.out.println("Server now running");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * setup server by connecting sockets input, output streams. 
	 * calls thread execute to run login thread.
	 */
	public void run() {
		try {
			while (true) 
			{    
				socket = serverSocket.accept(); 
				stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				stringOut = new PrintWriter((socket.getOutputStream()), true); 
				objectOut = new ObjectOutputStream(socket.getOutputStream());
				objectOut.flush();
				objectIn = new ObjectInputStream(socket.getInputStream());
				LoginThread thread = new LoginThread(objectIn, objectOut, stringIn, stringOut, database);
				threadPool.execute(thread);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Run the Server. Change password in argument of DatabaseSetterupper to proper mySQL password
	 * @param args none
	 * @throws IOException Print stack trace
	 */
	public static void main(String[] args) throws IOException {
		
		DatabaseSetterUpper setupDatabase = new DatabaseSetterUpper("pokemon");
		MainServer mainServer= new MainServer(setupDatabase.getDatabase());
		mainServer.run();
	}
}