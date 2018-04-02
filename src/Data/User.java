package Data;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable{
	protected String username;
	protected String password;
	protected char type;
	protected String emailAddress;
	protected ArrayList <Course> courses;
	public User(String user, String pass, char userType, String email )
	{
		username = user;
		password = pass;
		type = userType;
		emailAddress= email;
		courses = new ArrayList<Course>();
				
	}
	public abstract void sendEmail();
	
}
