package Database;
import Data.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * @author Robert, Justin, Magnus
 *	this class access the user table from the database
 *	it has various methods used to retrieve specific data from the database
 */
public class UserTable {
	/**
	 * The connection used to connect to the user table
	 */
	public Connection jdbc_connection;
	/**
	 * the prepared statement used 
	 */
	public PreparedStatement statement;
	
	/**
	 * the password to access the database
	 */
	public String connectionInfo = "jdbc:mysql://localhost:3306/demo?useSSL=false",  
				  login          = "root"; 

		/**
	   * the connection information for the specific database
	   * @param password the password  to access the database
	   */
	public UserTable(String password)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	/**
	 * creates a user table
	 */
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
			statement.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * adds a user 
	 * @param user the uer to be added
	 */
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
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * searches using username and password
	 * @param UserName the username 
	 * @param Password the password
	 * @return the user if found 
	 */
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
					user.close();
					statement.close();
					return new Professor(id, username, password, chartype, email, firstName, lastName);
				}
				else
				{
					char chartype = type.charAt(0);
					user.close();
					statement.close();
					return new Student(id, username, password, chartype, email, firstName, lastName);
				}
			}
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	/**
	 * searchs through the table with a user id
	 * @param ID the id of the user
	 * @return the user
	 */
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
					user.close();
					statement.close();
					return new Professor(id, username, password, chartype, email, firstName, lastName);
				}
				else
				{
					char chartype = type.charAt(0);
					user.close();
					statement.close();
					return new Student(id, username, password, chartype, email, firstName, lastName);
        }
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	/**
	 * gets an arraylist of student emails in a course
	 * @param idList the array list of ids 
	 * @param courseId the course id 
	 * @return an arraylist of emails 
	 */
	public ArrayList<String> getStudentEmails(ArrayList<Integer> idList, Integer courseId)
	{
		ArrayList<String> emailList = new ArrayList<String>();
		
		for(int i = 0; i < idList.size(); i++)
		{
			emailList.add(getUserEmail(idList.get(i)));
		}
		return emailList;
	}
	/**
	 * gets an email of a user
	 * @param id the id of the user
	 * @return the email of the user
	 */
	public String getUserEmail(Integer id)
	{
		try {
			String sql = "SELECT EMAIL FROM usertable WHERE ID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet emailSet = statement.executeQuery();
			emailSet.next();
			return emailSet.getString("EMAIL");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * gets all the students
	 * @return an array  list  of students 
	 */
	public ArrayList<Student> getAll()
	{
		String sql = "SELECT * FROM " + "UserTable";
		ResultSet user;
		ArrayList<Student> studentList = new ArrayList<Student>(); 
		try {
			studentList = new ArrayList<Student>(); 
			statement = jdbc_connection.prepareStatement(sql);
			user = statement.executeQuery();
			while(user.next())
			{
				Integer id = user.getInt("ID");
				String password = user.getString("PASSWORD");
				String username = user.getString("USERNAME");
				String email = user.getString("EMAIL");
				String firstName = user.getString("FIRSTNAME");
				String lastName = user.getString("LASTNAME");
				String type = user.getString("TYPE");
			
				if(type.equals("S"))
				{
					char chartype = type.charAt(0);
					studentList.add( new Student(id, username, password, chartype, email, firstName, lastName));
				
				}
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return studentList;
	}
	/**
	 * closes all the connections
	 */
	public void closeConnection()
	{
		try {
			jdbc_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
