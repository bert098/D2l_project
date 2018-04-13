package Controller;

import Model.ClientMain;

/**
 * Main class for the client of the ENSF 409 final project.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 *
 */
public class Demo {
	public static void main(String[] args) {	
		ClientMain theClient = new ClientMain("localhost", 6969); 
		@SuppressWarnings("unused")
		UserController userController = new UserController(theClient); 
	}
}