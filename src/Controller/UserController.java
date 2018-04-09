package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Data.Professor;
import Data.Student;
import Data.User;
import Data.Constants;
import View.LoginWindow;
import View.ProfessorView;
import View.StudentView;
import Model.ClientMain;
import Model.StudentModel;

public class UserController implements Constants {
	private ClientMain clientMain; 
	private LoginWindow loginWindow;  
	private String userName;
	private String password;
	
	public UserController(ClientMain client) { 
		clientMain = client;
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
				clientMain.sendUserName(userName);
				clientMain.sendPassword(password);
				User user = clientMain.readUser();
				if (user == null) {
					loginWindow.displayWrongLogin(); 
				}
				else if (user.getType()== PROFESSOR) { 
					Professor theProfessor = new Professor(user.getId(), user.getUsername(), user.getPassword(),
							user.getType(), user.getEmail(), user.getFirstName(), user.getLastName());
					ProfessorController professorController = new ProfessorController(new ProfessorView(theProfessor), clientMain.getProfessorModel());
					loginWindow.closeWindow();
				}
				else if (user.getType() == STUDENT) {
					Student theStudent = new Student(user.getId(), user.getUsername(), user.getPassword(), 
							user.getType(), user.getEmail(), user.getFirstName(), user.getLastName());
					StudentController studentController = new StudentController(new StudentView(theStudent), clientMain.getStudentModel());
				}
			}
		});
	}
}
