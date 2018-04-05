package Controller;

import Model.ClientMain;

public class Demo {

	public static void main(String[] args) {
		ClientMain theClient = new ClientMain("localhost", 6969); 
		UserController userController = new UserController(theClient.getStringOut(), theClient.getStringIn()
														  ,theClient.getObjectIn(), theClient.getObjectOut()); 
	}
}