package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Data.Course;
import Data.User;

public class CourseTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root",
				  password       = "hi";

	public CourseTable()
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
	public void createCourseTable()
	{
		String sql =   "CREATE TABLE " + "CourseTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PROF_ID INT(8) NOT NULL, " + 
				     "NAME VARCHAR(50) NOT NULL, " + 
				     "ACTIVE CHAR(1) NOT NULL, " + 
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
	public void addCourse(Course user)
	{
		String sql = "INSERT INTO " + "CourseTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ",? " +  
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setInt(2, user.getProfId());
			statement.setString(3, user.getName());
			if(user.getActive() == true)
			{
				statement.setInt(4, 1);
			}
			else
			{
				statement.setInt(4, 0);
			}
			
			
			
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
