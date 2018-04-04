package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Data.*;

public class SubmissionTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root",
				  password       = "hi";

	public SubmissionTable()
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
	public void createSubmissionTable()
	{
		String sql =   "CREATE TABLE " + "SubmissionTable" + "(" +
				     "ASSIGN_ID INT(8) NOT NULL, " + 
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "PATH VARCHAR(100) NOT NULL, " + 
				     "TITLE VARCHAR(50) NOT NULL, " + 
				     "SUBMISSION_GRADE INT(3) NOT NULL, " +
				     "COMMENTS VARCHAR(140) NOT NULL, " +
				     "TIMESTAMP VARCHAR(25) NOT NULL, " +
				     "PRIMARY KEY ( assign_id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void addSubmission(Dropbox d)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
	
		String sql = "INSERT INTO " + "SubmissionTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, d.getAssignId());
			statement.setInt(2, d.getStudentId());
			statement.setString(3, d.getPath());
			statement.setString(4, d.getTitle());
			statement.setInt(5, d.getGrade());
			statement.setString(6, d.getComment());
			statement.setString(7, dateFormat.format(cal.getTime()));
			
			
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Dropbox search(int ID)
	{
		String sql = "SELECT * FROM " + "SubmissionTable" + " WHERE ASSIGN_ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer assignId = user.getInt("ASSIGN_ID");
				Integer studentId = user.getInt("STUDENT_ID");
				String path = user.getString("PATH");
				String title = user.getString("TITLE");
				Integer grade = user.getInt("SUBMISSION_GRADE");
				String comments = user.getString("COMMENTS");
				String time = user.getString("TIMESTAMP");
				AssignmentTable assign = new AssignmentTable();
				UserTable use = new UserTable();
				Assignment a = assign.search(assignId);
				Student s = (Student)use.searchID(studentId);
				return new Dropbox(a,s,grade,comments );
			
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	
}
