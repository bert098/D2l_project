package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Data.Professor;
import Data.User;
import Data.Constants;
import View.LoginWindow;
import View.ProfessorView;

public class UserController implements Constants {
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private PrintWriter stringOut;
	private BufferedReader stringIn;
	private LoginWindow loginWindow;  
	private String userName;
	private String password;
	
	public UserController(PrintWriter out, BufferedReader in, ObjectInputStream oIn, ObjectOutputStream oOut) { 
		objectIn = oIn; 
		stringOut = out;
		stringIn = in; 
		objectOut = oOut; 
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
						ProfessorController professorController = new ProfessorController(new ProfessorView(theProfessor),
																						  stringOut, stringIn, objectOut, objectIn);
						loginWindow.closeWindow();
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
