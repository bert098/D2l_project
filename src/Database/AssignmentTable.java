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
import java.util.ArrayList;

import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Course;
import Data.FileContainer;
import Data.Professor;
import Data.Student;
import Data.User;

public class AssignmentTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	private String pass;
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root"; 
	
	public AssignmentTable(String password)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			pass = password;
		}
		catch(Exception e) { e.printStackTrace(); }
	}
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
			statement.close();
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
				String title  = user.getString("TITLE");
				String path  = user.getString("PATH");
				boolean active = user.getBoolean("ACTIVE");
				String dueDate = user.getString("DUE_DATE");
				Integer courseId = user.getInt("COURSEID");
				CourseTable c = new CourseTable(pass);
				Course course = c.searchCourse(courseId);
				user.close();
				c.closeConnection();
				statement.close();
				return new Assignment(id, course, title, path, active, dueDate);
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
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Assignment> assignmentTableToList() {
		try { 
			ArrayList<Assignment> assignmentList = new ArrayList<Assignment>(); 
			String sql = "SELECT * FROM " + "AssignmentTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet assignmentSet = statement.executeQuery();
			
			while (assignmentSet.next()) {
				Assignment theAssignment = new Assignment(assignmentSet.getInt("ID"), 
						  assignmentSet.getInt("COURSEID"), 
						  assignmentSet.getString("TITLE"),
						  assignmentSet.getString("PATH"),
						  assignmentSet.getBoolean("ACTIVE"),
						  assignmentSet.getString("DUE_DATE"));
				assignmentList.add(theAssignment);
			}
			assignmentSet.close();
			statement.close();
			return assignmentList;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public ArrayList<Assignment> courseAssignmentTableToList(Integer c) {
		try { 
			ArrayList<Assignment> assignmentList = new ArrayList<Assignment>(); 
			String sql = "SELECT * FROM " + "AssignmentTable";
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet assignmentSet = statement.executeQuery();
			
			while (assignmentSet.next()) {
				Assignment theAssignment = new Assignment(assignmentSet.getInt("ID"), 
						  assignmentSet.getInt("COURSEID"), 
						  assignmentSet.getString("TITLE"),
						  assignmentSet.getString("PATH"),
						  assignmentSet.getBoolean("ACTIVE"),
						  assignmentSet.getString("DUE_DATE"));
				if(theAssignment.getCourseId().equals(c))
				{
				assignmentList.add(theAssignment);
				}
			}
			assignmentSet.close();
			statement.close();
			return assignmentList;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public void updateAssignmentStatus(int id, boolean status) {
		try {
			String sql = "UPDATE AssignmentTable SET " +
					     "ACTIVE = ? " +
					     "WHERE ID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			if (status) {
				statement.setInt(1, 1);
			}
			else {
				statement.setInt(1, 0);
			}
			statement.setInt(2, id);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public AssignmentFileContainer getAssignmentFile(Assignment assign)
	{
		try {
			String sql = "SELECT TITLE, PATH FROM AssignmentTable WHERE ID = ?";
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, assign.getId());
			
			ResultSet assignmentSet = statement.executeQuery();
			
			assignmentSet.next();
			
			String name = assignmentSet.getString("TITLE");
			String path = assignmentSet.getString("PATH");
			File file = new File(path);
			
			long length = file.length();
			byte[] content = new byte[(int)length];
			
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			
			bos.close();
			
			AssignmentFileContainer fileContainer = new AssignmentFileContainer(content, name, assign);
			return fileContainer;
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
	public void closeConnection()
	{
		try {
			jdbc_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
