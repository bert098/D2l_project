package Data;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing a Student 
 */
public class Student extends User {

	
	/**
	 * the constructor for the student class
	 * @param ID is the student id 
	 * @param user is the student username
	 * @param pass is the password 
	 * @param userType is the type of the user, in this case is is Student 
	 * @param email is the email of the student 
	 * @param first is the first name of the user 
	 * @param last is the last name of the  user 
	 */
	public Student(Integer ID, String user, String pass, char userType, String email, String first, String last) {
		super(ID, user, pass, userType, email, first, last);
		
	}
	/**
	 * creates a string with the students first and last name along with their id 
	 */
	@Override
	public String toString() {
		return firstName.toString() +"  " + lastName.toString() + " " + id.toString();
		
	}
}
