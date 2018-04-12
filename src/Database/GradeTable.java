package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Grade;
import Data.Assignment;
import Data.Course;
import Data.Grade;
import Data.Professor;
import Data.Student;
import Data.User;

public class GradeTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	private String pass;
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root";

	public GradeTable(String password)
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
	public void createGradeTable()
	{
		String sql =   "CREATE TABLE " + "GradeTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "ASSIGN_ID INT(8) NOT NULL, " + 
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "COURSE_ID INT(8) NOT NULL, " + 
				     "GRADE INT(3) NOT NULL, " + 
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
	
	public void addGrade(Grade user)
	{
		String sql = "INSERT INTO " + "GradeTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " +  
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setInt(2, user.getAssignId());
			statement.setInt(3, user.getStudentId());
			statement.setInt(4, user.getCourseId());
			statement.setInt(5, user.getGrade());
			statement.executeUpdate();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Grade searchID(int ID)
	{
		String sql = "SELECT * FROM " + "GradeTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer id = user.getInt("ID");
				Integer assignId = user.getInt("ASSIGN_ID");
				Integer studentId = user.getInt("STUDENT_ID");
				Integer courseId = user.getInt("COURSE_ID");
				Integer grade = user.getInt("GRADE");
				
				UserTable use = new UserTable(pass);
				CourseTable course = new CourseTable(pass);
				AssignmentTable assign = new AssignmentTable(pass);
				
				Student s = (Student)use.searchID(studentId);
				Course c = course.searchCourse(courseId);
				Assignment a = assign.search(assignId);
				use.closeConnection();
				course.closeConnection();
				assign.closeConnection();
				user.close();
				statement.close();
				return new Grade(s,grade,c,a,id);
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	public ArrayList<Grade> searchIDAndCourse(Integer ID, Integer courseID)
	{
		String sql = "SELECT * FROM " + "GradeTable";
		ResultSet user;
		ArrayList<Grade> grades = new ArrayList<Grade>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			user = statement.executeQuery();
			while(user.next())
			{
				Integer id = user.getInt("ID");
				Integer assignId = user.getInt("ASSIGN_ID");
				Integer studentId = user.getInt("STUDENT_ID");
				Integer courseId = user.getInt("COURSE_ID");
				Integer grade = user.getInt("GRADE");
				
				UserTable use = new UserTable(pass);
				CourseTable course = new CourseTable(pass);
				AssignmentTable assign = new AssignmentTable(pass);
				
				Student s = (Student)use.searchID(studentId);
				Course c = course.searchCourse(courseId);
				Assignment a = assign.search(assignId);
				if(studentId.equals(ID) && courseId.equals(courseID))
				{
				grades.add(new Grade(s,grade,c,a,id));
				}
				use.closeConnection();
				course.closeConnection();
				assign.closeConnection();
				
			}
			
			user.close();
			statement.close();
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		
		return grades;
	}
	public void closeConnection()
	{
		try {
			jdbc_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
