package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Course;
import Data.Professor;
import Data.User;

public class CourseTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	private String pass;
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root";

	public CourseTable(String password)
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
	public void createCourseTable()
	{
		String sql =   "CREATE TABLE " + "CourseTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PROF_ID INT(8) NOT NULL, " + 
				     "NAME VARCHAR(50) NOT NULL, " + 
				     "ACTIVE BIT(1) NOT NULL, " + 
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
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
			statement.close();
		}
		catch(SQLException e)
		{
			
		}
	}
	public Course searchCourse(int toolID)
	{
		String sql = "SELECT * FROM " + "CourseTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, toolID);
			user = statement.executeQuery();
			if(user.next())
			{
				UserTable userTab =  new UserTable(pass);
				Integer id = user.getInt("ID");
				Integer 	prof_id = user.getInt("PROF_ID");
				String name = user.getString("NAME");
				boolean active = user.getBoolean("ACTIVE");
				Professor p = (Professor)userTab.searchID(prof_id);
				user.close();
				statement.close();
				userTab.closeConnection();
				return new Course(p, id, name, active);
				
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ArrayList<Course> courseTableToList() {
		try { 
			ArrayList<Course> courseList = new ArrayList<Course>(); 
			String sql = "SELECT * FROM " + "CourseTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet courseSet = statement.executeQuery();
			
			while(courseSet.next()) 
			{
				Course theCourse = new Course(courseSet.getInt("ID"), 
											  courseSet.getInt("PROF_ID"), 
											  courseSet.getString("NAME"), 
											  courseSet.getBoolean("ACTIVE"));
				courseList.add(theCourse);
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
	
	public void updateCourseStatus(Integer courseId, boolean status)
	{
		try
		{
			String sql = "UPDATE COURSETABLE SET " +
					"ACTIVE = ? " +
					"WHERE ID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			if(status)
			{
				statement.setInt(1, 1);
			}
			else
			{
				statement.setInt(1, 0);
			}
			statement.setInt(2, courseId);
			statement.executeUpdate();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
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
