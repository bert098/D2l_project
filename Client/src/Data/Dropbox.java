package Data;

import java.io.Serializable;
/**
 * @author Robert, Justin, Magnus
 * This class provides fields and methods to create a class representing a dropbox for a student
 * and a professor to exchange assignments for grading
 * The purpose of this class is for a student to submit an assignment and then for the teacher to marks it and return it with 
 * a comment
 */
public class Dropbox implements Serializable{
	/**
	 * the serialization number 
	 */
	private static final long serialVersionUID = 5157973083870589842L;
	/**
	 * the id of the dropbox
	 */
	private Integer id;
	/**
	 * the assignment id that corresponds to the dropbox
	 */
	private Integer assign_id;
	/**
	 * the id of the student who submitted the assignment
	 */
	private Integer student_id;
	/**
	 * the path where the assignment exists on the computer runnning the server 
	 */
	private String path;
	/**
	 * The grade the the student received 
	 */
	private Integer grade;
	/**
	 * the comment the professor gave the student
	 */
	private String comment;
	/**
	 * the title of the assignment that is being submitted
	 */
	private String title;
	/**
	 * the time of the submission by the student
	 */
	private String timeStamp; 
	
	
	
	/**
	 * This is the constructor of the dropbox 
	 * @param i is the id of the dropbox
	 * @param aId is the assignment id 
	 * @param sId is he student's id who submitted the assignment 
	 * @param p is the path where the assignment is stored
	 * @param g is the grade the student received
	 * @param c is the comment that the professor gave to the student
	 * @param t is the title  of the assignment 
	 * @param ts is the time stamp of when the assignment was submitted  
	 */
	public Dropbox(Integer i, Integer aId, Integer sId, String p, Integer g, String c, String t, String ts) 
	{
		id = i;
		assign_id = aId;
		student_id = sId; 
		path = p; 
		grade = g; 
		comment = c; 
		title = t;
		timeStamp = ts;
	}
	/**
	 * @return the id of the submission
	 */
	public Integer getId() {return id;}
	/**
	 * 
	 * @return the assignment id 
	 */
	public Integer getAssignId() {return assign_id;}
	/**
	 * @return the student's id
	 */
	public Integer getStudentId() {return student_id;}
	/**
	 * @return the path  of the assignment 
	 */
	public String getPath() {return path;}
	/**
	 * @return the grade of the assignment
	 */
	public Integer getGrade() {return grade;}
	/**
	 * @return the comment 
	 */
	public String getComment() {return comment;}
	/**
	 * @return the title of the assignment 
	 */
	public String getTitle() {return title;}
	
	/**
	 * sets the path of the assignment 
	 * @param path is the path of the assignment 
	 */
	public void setPath(String path) {this.path = path;}
	/**
	 * sets the id of the submission 
	 * @param num is the id
	 */
	public void setId(Integer num) {this.id = num;}
	
	/**
	 * A method that displays the details of the submission in a string 
	 */
	@Override 
	public String toString() { 
		if (grade == -1) { 
			return "Title: " + title + " Student Id: " + student_id + " Submission Time: " + timeStamp + " UNMARKED";
		}
		return "Title: " + title + " Student Id: " + student_id + " Submission Time: " + timeStamp + " Grade: " + grade;
	}
}
