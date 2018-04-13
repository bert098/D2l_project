package Data;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing a user
 * for this application
 * It stores all the important information that a user would have 
 */
public abstract class User implements Serializable{
	
	/**
	 * the serialization id 
	 */
	private static final long serialVersionUID = 5887074664825358619L;
	/**
	 * the id of the user
	 */
	protected Integer id;
	/**
	 * the username the user uses to log in
	 */
	private String username;
	/**
	 * the password the user uses to log in 
	 */
	private String password;
	/**
	 * the type of user (either student or professor)
	 */
	private char type;
	/**
	 * the email address of the user
	 */
	private String emailAddress;
	/**
	 * the first  name of the user
	 */
	protected String firstName;
	/**
	 * the last name of the user
	 */
	protected String lastName;
	/**
	 * all the courses a user has 
	 */
	@SuppressWarnings("unused")
	private ArrayList <Course> courses;
	/**
	 * this is the constructor of the user
	 * @param ID is the id of the user
	 * @param user is the username of the user
	 * @param pass is the password of the user
	 * @param userType is the users type
	 * @param email is the email of the user
	 * @param first is the first name of the user 
	 * @param last is the last name of the user 
	 */
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
	/**
	 * @return the id of the user
	 */
	public Integer getId() {return id;}
	/**
	 * @return the username of the user
	 */
	public String getUsername() {return username;}
	/**
	 * @return the password of the user 
	 */
	public String getPassword() {return password;}
	/**
	 * @return the type of of the user
	 */
	public char getType() {return type;}
	/**
	 * @return the email address of the user 
	 */
	public String getEmail() {return emailAddress;}
	/**
	 * @return the first name of the user 
	 */
	public String getFirstName() {return firstName;}
	/**
	 * @return the last name of the user 
	 */
	public String getLastName() {return lastName;}
	
	
}
