package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import Data.Professor;
import Data.User;
import View.LoginWindow;
import View.ProfessorView;

public class UserController implements Constants {
	private ObjectInputStream objectIn;
	private PrintWriter stringOut;
	private LoginWindow loginWindow;  
	private String userName;
	private String password;
	
	public UserController(PrintWriter out, ObjectInputStream in) { 
		objectIn = in; 
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
				try { 
					User user = (User) objectIn.readObject();
					if (user == null) {
						loginWindow.displayWrongLogin(); 
					}
					else if (user.getType()== PROFESSOR) { 
						Professor theProfessor = new Professor(user.getId(), user.getUsername(), user.getPassword()
								,user.getType(), user.getEmail(), user.getFirstName(), user.getLastName());
						ProfessorController professorController = new ProfessorController(new ProfessorView(theProfessor));
					}
					else if (user.getType() == STUDENT) {
						//todo
					}
				}
				catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
