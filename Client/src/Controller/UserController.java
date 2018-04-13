package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Data.Professor;
import Data.Student;
import Data.User;
import Data.Constants;
import View.LoginWindow;
import View.ProfessorView;
import View.StudentView;
import Model.ClientMain;

/**
 * User controller handles the interactions between the login view and the 
 * client main.
 * @author Justin, Magnus, Robert
 */
public class UserController implements Constants {
	
	/**
	 * Client main for interacting with the server 
	 */
	private ClientMain clientMain; 
	
	/**
	 * Gui for login view 
	 */
	private LoginWindow loginWindow;  
	
	/**
	 * Username of user
	 */
	private String userName;
	
	/**
	 * Password of user
	 */
	private String password;
	
	/**
	 * Constructor constructs the login window and adds an action listener for 
	 * the login button. If login successful the actionPerformed method will construct either 
	 * the professor controller or the student professor depending on user type.
	 * @param client client model that handles server interactions
	 */
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
					@SuppressWarnings("unused")
					ProfessorController professorController = new ProfessorController(new ProfessorView(theProfessor), clientMain.getProfessorModel());
					loginWindow.closeWindow();
				}
				else if (user.getType() == STUDENT) {
					Student theStudent = new Student(user.getId(), user.getUsername(), user.getPassword(), 
							user.getType(), user.getEmail(), user.getFirstName(), user.getLastName());
					@SuppressWarnings("unused")
					StudentController studentController = new StudentController(new StudentView(theStudent), clientMain.getStudentModel());
					loginWindow.closeWindow();
				}
			}
		});
	}
}
