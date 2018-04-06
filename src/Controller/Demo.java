package Controller;

import Model.ClientMain;

/**
 * 
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 *
 */
public class Demo {

	public static void main(String[] args) {
		ClientMain theClient = new ClientMain("localhost", 6969); 
		UserController userController = new UserController(theClient); 
	}
}