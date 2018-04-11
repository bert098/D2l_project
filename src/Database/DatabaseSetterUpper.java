package Database;
import Data.*;
public class DatabaseSetterUpper {
	
	private DatabaseHelper data; 
	
	public DatabaseSetterUpper(String password)
	{
		data = new DatabaseHelper(password);
		data.createAllTables();
		Student u1 = new Student(1,"Roberto098", "Pokemon", 'S', "rob.fungi@gmail.com", "Robert", "Dumitru");
		Professor u2 = new Professor(2,"Chrity09", "Justin", 'P', "RobertLikesAnime@gmail.com", "Christy", "Chan");
		Student u3 = new Student(3,"Justin09", "dancedance", 'S', "justinhung11@gmail.com", "Justin", "Hung");
		Student u4 = new Student(4,"MagMan", "CarsAreLife", 'S', "mglyngberg2@gmail.com", "Magnus", "Lyngberg");
		Student u5 = new Student(5,"Tommy", "glasses", 'S', "123@hotmail.com", "Thomas", "Vy");
		Student u6 = new Student(1111, "Jhung", "butts", 'S', "shinobubodypillow@gmail.com", "J", "Ustin");
		Course c1 = new Course( u2, 1, "ENSF 420", true);
		Course c2 = new Course( u2, 271, "MATH", true);

		Assignment a1 = new Assignment (1234, 271, "finalproject", "path", true, "OCT 10 2019");
		Assignment a2 = new Assignment (14, 271, "midproject", "path", true, "OCT 11 2019");
		Assignment a3 = new Assignment (12, 1, "diaosd", "path", true, "tomorrow");

		StudentEnrollment e1 = new StudentEnrollment(100, u3, c1);
		StudentEnrollment e2 = new StudentEnrollment(103, u3, c2);
		StudentEnrollment e3 = new StudentEnrollment(101, u4, c1);
		StudentEnrollment e4 = new StudentEnrollment(102, u5, c2);
		StudentEnrollment e5 = new StudentEnrollment(104, u6, c2);
		
		Dropbox d1 = new Dropbox(1, 1234, 1, "", -1, "", "finalproject", "oct 10");
		Dropbox d2 = new Dropbox(2, 1234, 3, "", -1, "", "finalproject", "oct 11");
		Dropbox d3 = new Dropbox(3, 14, 1, "", -1, "", "midproject", "oct 9");
		
		data.insertStudentEnrollment(e1);
		data.insertStudentEnrollment(e2);
		data.insertStudentEnrollment(e3);
		data.insertStudentEnrollment(e4);
		data.insertStudentEnrollment(e5);
		data.insertUser(u1);
		data.insertUser(u2);
		data.insertUser(u3);
		data.insertUser(u4);
		data.insertUser(u5);
		data.insertUser(u6);
		data.insertCourse(c1);
		data.insertCourse(c2);
		data.insertAssignment(a1);
		data.insertAssignment(a2);
		data.insertAssignment(a3);
		data.insertSubmission(d1);
		data.insertSubmission(d2);
		data.insertSubmission(d3);
	}	
	
	public DatabaseHelper getDatabase() {
		return data;
	}

}
