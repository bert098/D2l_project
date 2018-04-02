package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.LoginWindow;

public class UserController {

	private LoginWindow loginWindow;  
	private String userName;
	private String password;
	//private User user; 
	
	public UserController() { 
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
			}
		});
	}
	
	public String getPassword() { 
		return password; 
	}
	
	public String getUserName() { 
		return userName; 
	}
}
