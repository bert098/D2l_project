package Data;

public class Student extends User {

	

	public Student(Integer ID, String user, String pass, char userType, String email, String first, String last) {
		super(ID, user, pass, userType, email, first, last);
		
	}

	@Override
	public void sendEmail() {
		
		
	}

}
