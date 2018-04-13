package Data;

import java.io.Serializable;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing an assignment 
 * for this application
 * It holds all the key properties of an assignment 
 */
public class Assignment implements Serializable{
	/**
	 * the serialization number 
	 */
	private static final long serialVersionUID = -5635159349473954247L;
	/**
	 *  the id of the assignment 
	 */
	private Integer id;
	/**
	 *  the course id of the course that assigned this assignment 
	 */
	private Integer courseId;
	/**
	 * the title of the course 
	 */
	private String title;
	/**
	 *  the path where the assignment is held
	 */
	private String path;
	/**
	 * whether the assignment is active or not 
	 */
	private boolean active;
	/**
	 * the date the assignment is set active  
	 */
	private String date;

	/**
	 * the constructor of the assignment class
	 * @param id is the is of the assignment 
	 * @param courseId is the course that issued that assignment
	 * @param title the title of the assignment 
	 * @param path the path where the assignment is
	 * @param active whether the assignment is active or not 
	 * @param date the date the assignment was issued
	 */
	public Assignment(Integer id, Course courseId, String title,  String path, boolean active, String date)
	{
		this.id = id;
		this.courseId = courseId.getId();
		this.title = title;
		this.path = path;
		this.active = active;
		this.date = date;
	}

	/**
	 * the constructor of the assignment class
	 * @param id is the is of the assignment 
	 * @param courseId is the id of the course that issued that assignment
	 * @param title the title of the assignment 
	 * @param path the path where the assignment is
	 * @param active whether the assignment is active or not 
	 * @param date the date the assignment was issued
	 */
	public Assignment(Integer id, Integer courseId, String title,  String path, boolean active, String date)
	{
		this.id = id;
		this.courseId = courseId;
		this.title = title;
		this.path = path;
		this.active = active;
		this.date = date;
	}
	/**
	 * 
	 * @return the assignment id
	 */
	public Integer getId() {return id;}
/**
 * 
 * @return the id of the course
 */
	public Integer getCourseId() {return courseId;}
	/**
	 * 
	 * @return the title of the assignment 
	 */
	public String getTitle() {return title;}
	/**
	 * 
	 * @return whether the assignment is active or not 
	 */
	public boolean getActive() {return active;}
	/**
	 * 
	 * @return the path of the assignment 
	 */
	public String getPath() {return path;}
	/**
	 * 
	 * @return the date of the assignment  
	 */
	public String getDate() {return date;}
	/**
	 * turns the assignment into a string to be displayed
	 */
	public String toString() { 
		String line = title + " Due: " + date + " ";
		if(active)
		{
			line += "Active";
		}
		else
		{
			line += "Inactive";
		}
		return line; 
	}
	/**
	 * sets the assignment id 
	 * @param id the new id 
	 */
	public void setId(Integer id) {this.id = id;}
	/**
	 * sets a new path
	 * @param path the new path 
	 */
	public void setPath(String path) {this.path = path;}
}
