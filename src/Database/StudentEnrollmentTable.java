package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Course;
import Data.Professor;
import Data.Student;
import Data.StudentEnrollment;
import Data.User;

public class StudentEnrollmentTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	private String pass;
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root";

	public StudentEnrollmentTable(String password)
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			pass = password;
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	// Use the jdbc connection to create a new database in MySQL. 
	// (e.g. if you are connected to "jdbc:mysql://localhost:3306", the database will be created at "jdbc:mysql://localhost:3306/InventoryDB")
	

	// Create a data table inside of the database to hold tools
	public void createStudentEnrollmentTable()
	{
		String sql =   "CREATE TABLE " + "StudentEnrollmentTable" + "(" +
					"ID INT(8) NOT NULL, " +
				     "STUDENT_ID INT(8) NOT NULL, " +
				     "COURSE_ID INT(8) NOT NULL, " +  
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void addEnrollment(StudentEnrollment user)
	{
		String sql = "INSERT INTO " + "StudentEnrollmentTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setInt(2, user.getStudentId());
			statement.setInt(3, user.getCourseId());
			
			
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public StudentEnrollment searchID(int ID)
	{
		String sql = "SELECT * FROM " + "StudentEnrollmentTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer id = user.getInt("ID");
				Integer studentId = user.getInt("STUDENT_ID");
				Integer courseId = user.getInt("COURSE_ID");
				UserTable s = new UserTable(pass);
				CourseTable c = new CourseTable(pass);
				Student st = (Student)s.searchID(studentId);
				Course co = c.searchCourse(courseId);
				return new StudentEnrollment(id, st, co);
			}
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
		}
		return null;
	}
	public ArrayList<Integer> searchStudent(int Id) {
		try { 
			ArrayList<Integer> courseList = new ArrayList<Integer>(); 
			String sql = "SELECT * FROM " + "StudentEnrollmentTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet courseSet = statement.executeQuery();
			
			while(courseSet.next()) 
			{
				StudentEnrollment enrollments = new StudentEnrollment(courseSet.getInt("ID"), 
											  courseSet.getInt("STUDENT_ID"), 
											  courseSet.getInt("COURSE_ID"));
				if(enrollments.getCourseId().equals(Id))
				{
					courseList.add(enrollments.getStudentId());
				}
			}
			
			courseSet.close();
			return courseList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Integer> searchCoursesForStudent(int studentId) {
		try { 
			ArrayList<Integer> courseList = new ArrayList<Integer>(); 
			String sql = "SELECT * FROM " + "StudentEnrollmentTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet courseSet = statement.executeQuery();
			
			while(courseSet.next()) 
			{
				StudentEnrollment enrollments = new StudentEnrollment(courseSet.getInt("ID"), 
											  courseSet.getInt("STUDENT_ID"), 
											  courseSet.getInt("COURSE_ID"));
				if(enrollments.getStudentId().equals(studentId))
				{
					courseList.add(enrollments.getCourseId());
				}
			}
			courseSet.close();
			return courseList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int  unEnrollStudent(int id) {
		try { 
			String sql = "SELECT * FROM " + "StudentEnrollmentTable" + " WHERE STUDENT_ID=?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet courseSet = statement.executeQuery();
			int c = 0;
			if(courseSet.next())
			{
				c = courseSet.getInt("COURSE_ID");	
			}
			
			return c;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public void  delete(int id) {
		try { 
			String sql = "DELETE FROM " + "StudentEnrollmentTable" + " WHERE STUDENT_ID=?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Integer> SearchStudentId(int Id) {
		try { 
			ArrayList<Integer> studentList = new ArrayList<Integer>(); 
			String sql = "SELECT * FROM " + "StudentEnrollmentTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet courseSet = statement.executeQuery();
			
			while(courseSet.next()) 
			{
				StudentEnrollment enrollments = new StudentEnrollment(courseSet.getInt("ID"), 
											  courseSet.getInt("STUDENT_ID"), 
											  courseSet.getInt("COURSE_ID"));
				if(enrollments.getStudentId().equals(Id))
				{
				studentList.add(enrollments.getStudentId());
				}
			}
			
			courseSet.close();
			return studentList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}
