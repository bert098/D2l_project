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
/**
 * 
 * @author Robert, Justin, Magnus
 *	this class access the student enrollment table from the database
 *	it has various methods used to retrieve specific data from the database
 */
public class StudentEnrollmentTable {
	/**
	 * The connection used to connect to the student enrollment table
	 */
	public Connection jdbc_connection;
	/**
	 * the prepared statement used 
	 */
	public PreparedStatement statement;
	/**
	 * the password to access the database
	 */
	private String pass;
	/**
	   * the connection information for the specific database
	   */
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root";
	/**
	 * the constructor for this class that establishes a connection to the database
	 * @param password is the password to the database
	 */
	public StudentEnrollmentTable(String password)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			pass = password;
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	/**
	 * creates the enrollment table
	 */
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
			statement.close();
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
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * searches the table for a student enrollment 
	 * @param ID the id of the  student enrollment 
	 * @return the student enrollment 
	 */
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
				s.closeConnection();
				Course co = c.searchCourse(courseId);
				c.closeConnection();
				user.close();
				statement.close();
				return new StudentEnrollment(id, st, co);
			}
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
		}
		return null;
	}
	/**
	 * gets all the students enrolled in a course 
	 * @param Id the id of the course
	 * @return an arraylist of students enrolled  in a course
	 */
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
			statement.close();
			return courseList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * searches for all the courses a student is in 
	 * @param studentId the id of the student 
	 * @return an arraylist of the course ids
	 */
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
			statement.close();
			return courseList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * removes a student from the enrollment table
	 * @param id the id of the student 
	 * @return the course id of the enrollment to be removed   
	 */
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
			courseSet.close();
			statement.close();
			return c;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * deletes a student enrollment 
	 * @param id the id of the enrollment to delete 
	 */
	public void  delete(int id) {
		try { 
			String sql = "DELETE FROM " + "StudentEnrollmentTable" + " WHERE STUDENT_ID=?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * searches for a student enrollment by student id 
	 * @param Id the id of the student 
	 * @return the student 
	 */
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
			statement.close();
			courseSet.close();
			return studentList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * closes the connection 
	 */
	public void closeConnection()
	{
		try {
			jdbc_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
