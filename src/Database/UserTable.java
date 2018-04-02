package Database;
import Data.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTable {
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
  
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root",
				  password       = "hi";

	public UserTable()
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
//	public void addItem(User tool)
//	{
//		String sql = "INSERT INTO " + "UserTable" +
//				" VALUES (? " +  ",? '" + 
//				 "',? " + 
//				 ",? " + 
//				 ",? " + 
//				 ");";
//		try{
//			statement = jdbc_connection.prepareStatement(sql);
//			statement.setInt(1, tool.getID());
//			statement.setString(2, tool.getName());
//			statement.setInt(3, tool.getQuantity());
//			statement.setDouble(4, tool.getPrice());
//			statement.setInt(5, tool.getSupplierID());
//			
//			
//			statement.executeUpdate();
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
}
