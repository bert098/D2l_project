package Data;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable{
	protected Integer id;
	private String username;
	private String password;
	private char type;
	private String emailAddress;
	protected String firstName;
	protected String lastName;
	private ArrayList <Course> courses;
	public User(Integer ID, String user, String pass, char userType, String email, String first, String last)
	{
		id = ID;
		username = user;
		password = pass;
		type = userType;
		emailAddress= email;
		courses = new ArrayList<Course>();
		firstName = first;
		lastName = last;			
	}
	public abstract void sendEmail();
	public Integer getId() {return id;}
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public char getType() {return type;}
	public String getEmail() {return emailAddress;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	
	
}
