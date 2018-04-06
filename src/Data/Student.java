package Data;

public class Student extends User {

	

	public Student(Integer ID, String user, String pass, char userType, String email, String first, String last) {
		super(ID, user, pass, userType, email, first, last);
		
	}

	@Override
	public String toString() {
		return firstName.toString() +"  " + lastName.toString() + " " + id.toString();
		
	}

	@Override
	public void sendEmail() {
		// TODO Auto-generated method stub
		
	}

}
