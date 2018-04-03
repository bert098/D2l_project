package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import View.LoginWindow;

public class UserController {
	private PrintWriter stringOut;
	private LoginWindow loginWindow;  
	private String userName;
	private String password;
	
	public UserController(PrintWriter out) { 
		stringOut = out;
		userName = ""; 
		password = "";
		loginWindow = new LoginWindow(); 
		loginWindow.addLoginListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				loginWindow.updateUserNamePassword();
				userName = loginWindow.getUserName();
				password = loginWindow.getPassword();
				stringOut.println(userName);
				stringOut.println(password);
			}
		});
	}
}
