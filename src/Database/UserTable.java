package Database;
import Data.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public User search(int UserID)
	{
		String sql = "SELECT * FROM " + "UserTable" + " WHERE ID=?";
		ResultSet user;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, UserID);
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
				
				
				
				if(user.getString("TYPE") == "P")
				{
					char chartype = type.charAt(0);
					return new Professor(id, username, password, chartype, email, firstName, lastName);
				}
				else
				{
					char chartype = type.charAt(0);
					return new Student(id, username, password, chartype, email, firstName, lastName);
				}
//				
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
}
