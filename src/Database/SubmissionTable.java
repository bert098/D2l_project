package Database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
/**
 * 
 * @author Robert, Justin, Magnus
 *	this class access the submission table from the database
 *	it has various methods used to retrieve specific data from the database
 */
public class SubmissionTable {
	/**
	 * The connection used to connect to the submission table
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
	public SubmissionTable(String password)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			pass =  password;
		}
		catch(Exception e) {
			
		}
	}
	/**
	 * creates a submission table
	 */
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
		
		}
	}
	/**
	 * adds a dropbox to the table
	 * @param d the dropbox added
	 */
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
			
		}
	}
	/**
	 * searches for a dropbox by id
	 * @param ID the if of the dropbox
	 * @return the dropbox
	 */
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
				return new Dropbox(id, a.getId(),s.getId(),path,grade,comments,title,time );
			
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	/**
	 * retrieves the grade for a submission 
	 * @param ID the id of the student 
	 * @param as the assignment to which the  grade belongs
	 * @return the dropbox with the correct grade
	 */
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
	/**
	 * all the sropboxes of a certain assignment 
	 * @param assignmentId the assignment id 
	 * @return the array list of dropboxes 
	 */
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
	/**
	 * updates a submission
	 * @param comment the comment 
	 * @param grade the grade that the student recieved 
	 * @param id the id of the student 
	 */
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
	/**
	 * makes a file container 
	 * @param submission the submission that is being converted 
	 * @return the submission file container 
	 */
	public SubmissionFileContainer getSubmissionFile(Dropbox submission) {
		try {
			String sql = "SELECT TITLE, PATH FROM SubmissionTable WHERE ID = ?";
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, submission.getId());
			
			ResultSet submissionSet = statement.executeQuery();
			
			submissionSet.next();
			
			String name = submissionSet.getString("TITLE");
			String path = submissionSet.getString("PATH");
			File file = new File(path);
			
			long length = file.length();
			byte[] content = new byte[(int)length];
			
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			
			bos.close();
			
			SubmissionFileContainer submissionContainer = new SubmissionFileContainer(content, name, submission);
			return submissionContainer;
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
