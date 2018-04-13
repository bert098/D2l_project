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
/**
 * 
 * @author Robert, Justin, Magnus
 *	this class access the course table from the database
 *	it has various methods used to retrieve specific data from the database
 */
public class CourseTable {
	/**
	 * The connection used to connect to the course table
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
	/**
	 * creates a table of courses 
	 */
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
			e.printStackTrace();
		}
	}
	/**
	 * adds a course to the table
	 * @param user the course to be added
	 */
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
			e.printStackTrace();
		}
	}
	/**
	 * searches the table for a course
	 * @param ID the id of the course
	 * @return the course if found 
	 */
	public Course searchCourse(int ID)
	{
		String sql = "SELECT * FROM " + "CourseTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
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
	/**
	 * gets a list of all the courses 
	 * @return an arraylist of all the courses
	 */
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
	/**
	 * updates the status of a course
	 * @param courseId the id of the course
	 * @param status is the status of the course 
	 */
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
	/**
	 * closes the connection to the database 
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
