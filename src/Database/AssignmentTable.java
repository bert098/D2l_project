package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.Assignment;
import Data.Course;
import Data.Professor;
import Data.Student;
import Data.User;

public class AssignmentTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root",
				  password       = "hi";

	public AssignmentTable()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	// Use the jdbc connection to create a new database in MySQL. 
	// (e.g. if you are connected to "jdbc:mysql://localhost:3306", the database will be created at "jdbc:mysql://localhost:3306/InventoryDB")
	

	// Create a data table inside of the database to hold tools
	public void createAssignmentTable()
	{
		String sql =   "CREATE TABLE " + "AssignmentTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "COURSEID INT(8) NOT NULL, " + 
				     "TITLE VARCHAR(50) NOT NULL, " + 
				     "PATH VARCHAR(100) NOT NULL, " + 
				     "ACTIVE BIT(1) NOT NULL, " + 
				     "DUE_DATE VARCHAR(16) NOT NULL, " +
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
	public Assignment search(int ID)
	{
		String sql = "SELECT * FROM " + "AssignmentTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer id = user.getInt("ID");
				Integer courseId = user.getInt("COURSEID");
				String title = user.getString("TITLE");
				String path = user.getString("PATH");
				boolean active = user.getBoolean("ACTIVE");
				String due_date = user.getString("DUE_DATE");
				CourseTable c = new CourseTable();
				Course course = c.searchCourse(courseId);
				return new Assignment(id, course, title, path, active, due_date);
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	public void addAssignment(Assignment user)
	{
		String sql = "INSERT INTO " + "AssignmentTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setInt(2, user.getCourseId());
			statement.setString(3, user.getTitle());
			statement.setString(4, user.getPath());
			if(user.getActive() == true)
			{
			statement.setInt(5, 1);
			}
			else 
			{
				statement.setInt(5, 0);
			}
			statement.setString(6, user.getDate());
			
			
			
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
