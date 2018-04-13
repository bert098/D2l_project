package Data;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing a Professor 
 */
public class Professor extends User {



	/**
	 * The serialization id 
	 */
	private static final long serialVersionUID = 122876235743907377L;
	/**
	 * the constructor for the professor class
	 * @param ID is the professors id 
	 * @param user is the professors username
	 * @param pass is the password 
	 * @param userType is the type of the user, in this case is is professor 
	 * @param email is the email of the professor 
	 * @param first is the first name of the user 
	 * @param last is the last name of the  user 
	 */
	public Professor(Integer ID, String user, String pass, char userType, String email, String first, String last) {
		super(ID, user, pass, userType, email, first, last);
	}
}
