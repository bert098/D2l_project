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

/**
 * Server class receives clients and executes login thread window.  
 * @author Justin
 */
public class MainServer {

	// socket to connect to client 
	private Socket socket;
	
	//thread pool to handle multiple clients 
	private ExecutorService threadPool; 
	
	//socket to connect to client 
	private ServerSocket serverSocket;
	
	// output stream to send messages to client 
	private ObjectOutputStream objectOut; 
	
	//input stream to receive messages from client 
	private ObjectInputStream objectIn;
	
	private BufferedReader stringIn; 
	
	private PrintWriter stringOut;
	
	
	/**
	 * Construct a Server with Port 6969
	 */
	public MainServer() {
		try {
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
				stringIn = new BufferedReader (new InputStreamReader(socket.getInputStream()));
				System.out.println("test1");
				stringOut = new PrintWriter((socket.getOutputStream())); 
				System.out.println("test2");
				objectOut = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("test4");
				LoginThread thread = new LoginThread(objectIn, objectOut, stringIn, stringOut);
				threadPool.execute(thread);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Run the Server.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		MainServer mainServer= new MainServer();
		mainServer.run();
	}
	
	
}