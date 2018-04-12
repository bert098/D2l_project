package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Data.*;

public class SubmissionTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	private String pass;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root";

	public SubmissionTable(String password)
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			pass =  password;
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	// Use the jdbc connection to create a new database in MySQL. 
	// (e.g. if you are connected to "jdbc:mysql://localhost:3306", the database will be created at "jdbc:mysql://localhost:3306/InventoryDB")
	

	// Create a data table inside of the database to hold tools
	public void createSubmissionTable()
	{
		String sql =   "CREATE TABLE " + "SubmissionTable" + "(" +
					 "ID INT(8) NOT NULL, " +
				     "ASSIGN_ID INT(8) NOT NULL, " + 
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "PATH VARCHAR(100) NOT NULL, " + 
				     "TITLE VARCHAR(50) NOT NULL, " + 
				     "SUBMISSION_GRADE INT(3) NOT NULL, " +
				     "COMMENTS VARCHAR(140) NOT NULL, " +
				     "TIMESTAMP VARCHAR(25) NOT NULL, " + 
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
				 ",? " + 
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, d.getId());
			statement.setInt(2, d.getAssignId());
			statement.setInt(3, d.getStudentId());
			statement.setString(4, d.getPath());
			statement.setString(5, d.getTitle());
			statement.setInt(6, d.getGrade());
			statement.setString(7, d.getComment());
			statement.setString(8, dateFormat.format(cal.getTime()));
			
			
			statement.executeUpdate();
			statement.close();
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
				Integer id = user.getInt("ID");
				Integer assignId = user.getInt("ASSIGN_ID");
				Integer studentId = user.getInt("STUDENT_ID");
				String path = user.getString("PATH");
				String title = user.getString("TITLE");
				Integer grade = user.getInt("SUBMISSION_GRADE");
				String comments = user.getString("COMMENTS");
				String time = user.getString("TIMESTAMP");
				AssignmentTable assign = new AssignmentTable(pass);
				UserTable use = new UserTable(pass);
				Assignment a = assign.search(assignId);
				Student s = (Student)use.searchID(studentId);
				user.close();
				statement.close();
				return new Dropbox(id, a,s,grade,comments );
			
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	public Dropbox GradeForAssignment(int ID, Assignment as)
	{
		String sql = "SELECT * FROM " + "SubmissionTable";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			user = statement.executeQuery();
			while(user.next())
			{
				Integer id = user.getInt("ID");
				Integer assignId = user.getInt("ASSIGN_ID");
				Integer studentId = user.getInt("STUDENT_ID");
				String path = user.getString("PATH");
				String title = user.getString("TITLE");
				Integer grade = user.getInt("SUBMISSION_GRADE");
				String comments = user.getString("COMMENTS");
				String time = user.getString("TIMESTAMP");
				AssignmentTable assign = new AssignmentTable(pass);
				UserTable use = new UserTable(pass);
				Assignment a = assign.search(assignId);
				assign.closeConnection();
				Student s = (Student)use.searchID(studentId);
				use.closeConnection();
				if(as.getId().equals(assignId) && studentId.equals(ID))
				{
					user.close();
					statement.close();
					return new Dropbox(id, a.getId(),s.getId(),path,grade,comments,title,time );
				}
			
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ArrayList<Dropbox> searchAssignment(int assignmentId) {
		try { 
			ArrayList<Dropbox> submissionList = new ArrayList<Dropbox>(); 
			String sql = "SELECT * FROM " + "SubmissionTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet submissionSet = statement.executeQuery();
			
			while(submissionSet.next()) {
				int setAssignmentId = submissionSet.getInt("ASSIGN_ID");
				if (setAssignmentId == assignmentId) {
					Dropbox submission = new Dropbox (submissionSet.getInt("ID"), setAssignmentId, submissionSet.getInt("STUDENT_ID"),
													  submissionSet.getString("PATH"), submissionSet.getInt("SUBMISSION_GRADE"),
													  submissionSet.getString("COMMENTS"), submissionSet.getString("TITLE"), 
													  submissionSet.getString("TIMESTAMP"));
					submissionList.add(submission);
				}
			}
			statement.close();
			submissionSet.close(); 
			return submissionList; 
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void gradeSubmission(String comment, String grade, int id) {
		try {
			String sql = "UPDATE SubmissionTable SET " +
					     "COMMENTS = ?, " +
					     "SUBMISSION_GRADE = ? " +
					     "WHERE ID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, comment);
			statement.setString(2, grade);
			statement.setInt(3, id);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
