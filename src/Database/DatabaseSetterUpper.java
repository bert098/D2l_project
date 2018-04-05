package Database;
import Data.*;
public class DatabaseSetterUpper {
	
	private DatabaseHelper data; 
	
	public DatabaseSetterUpper(String password)
	{
		data = new DatabaseHelper(password);
		data.createAllTables();
		Student u1 = new Student(1,"Roberto098", "Pokemon", 'S', "rob.fungi@gmail.com", "Robert", "Dumitru");
		Professor u2 = new Professor(2,"Chrity09", "Justin", 'P', "chanchristyaaa@gmail.com", "Christy", "Chan");
		Student u3 = new Student(3,"Justin09", "dancedance", 'S', "justinhung11@gmail.com", "Justin", "Hung");
		Student u4 = new Student(4,"MagMan", "CarsAreLife", 'S', "mglyngberg2@gmail.com", "Magnus", "Lyngberg");
		Student u5 = new Student(5,"Tommy", "glasses", 'S', "123@hotmail.com", "Thomas", "Vy");
		Course c1 = new Course( u2, 1, "ENSF 420", true);
		Course c2 = new Course( u2, 271, "MATH", true);
		data.insertUser(u1);
		data.insertUser(u2);
		data.insertUser(u3);
		data.insertUser(u4);
		data.insertUser(u5);
		data.insertCourse(c1);
		data.insertCourse(c2);
	}
	
	public DatabaseHelper getDatabase() {
		return data;
	}

}
