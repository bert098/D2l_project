package Database;
import Data.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root"; 

	public UserTable(String password)
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
	public void createUserTable()
	{
		String tableName = "UserTable";
		String sql =   "CREATE TABLE " + tableName + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PASSWORD VARCHAR(20) NOT NULL, " + 
				     "USERNAME VARCHAR(30) NOT NULL, " + 
				     "EMAIL VARCHAR(50) NOT NULL, " + 
				     "FIRSTNAME VARCHAR(30) NOT NULL, " + 
				     "LASTNAME VARCHAR(30) NOT NULL, " + 
				     "TYPE CHAR(1) NOT NULL, " +
				     "PRIMARY KEY ( username ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void addUser(User user)
	{
		String sql = "INSERT INTO " + "UserTable" +
				" VALUES (? " +  ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ",? " + 
				 ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getFirstName());
			statement.setString(6, user.getLastName());
			statement.setString(7, String.valueOf(user.getType()));
			
			
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public User search(String UserName, String Password)
	{
		String sql = "SELECT * FROM " + "UserTable" + " WHERE USERNAME=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, UserName);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer id = user.getInt("ID");
				String password = user.getString("PASSWORD");
				String username = user.getString("USERNAME");
				String email = user.getString("EMAIL");
				String firstName = user.getString("FIRSTNAME");
				String lastName = user.getString("LASTNAME");
				String type = user.getString("TYPE");
				
				
				if(password.equals(Password))
				{
				if(type.equals("P"))
				{
					char chartype = type.charAt(0);
					return new Professor(id, username, password, chartype, email, firstName, lastName);
				}
				else
				{
					char chartype = type.charAt(0);
					return new Student(id, username, password, chartype, email, firstName, lastName);
				}
			}
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	public User searchID(int ID)
	{
		String sql = "SELECT * FROM " + "UserTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, ID);
			user = statement.executeQuery();
			if(user.next())
			{
				Integer id = user.getInt("ID");
				String password = user.getString("PASSWORD");
				String username = user.getString("USERNAME");
				String email = user.getString("EMAIL");
				String firstName = user.getString("FIRSTNAME");
				String lastName = user.getString("LASTNAME");
				String type = user.getString("TYPE");
			
				if(type.equals("P"))
				{
					char chartype = type.charAt(0);
					return new Professor(id, username, password, chartype, email, firstName, lastName);
				}
				else
				{
					char chartype = type.charAt(0);
					return new Student(id, username, password, chartype, email, firstName, lastName);
        }
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ArrayList<String> getStudentEmails(ArrayList<Integer> idList, Integer courseId)
	{
		try {
			ArrayList<String> emailList = new ArrayList<String>();
			String sql = "SELECT EMAIL FROM usertable WHERE ID = ?";
			
			for(int i = 0; i < idList.size(); i++)
			{
				statement = jdbc_connection.prepareStatement(sql);
				statement.setInt(1, idList.get(i));
				ResultSet emailSet = statement.executeQuery();
				emailSet.next();
				emailList.add(emailSet.getString("EMAIL"));
			}
			return emailList;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
