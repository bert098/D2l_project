package Data;

import java.io.Serializable;

public class Email implements Serializable{
	/**
	 * @author Robert, Justin, Magnus
	 * Provides data fields and methods in order to create a class representing an email
	 * for this application
	 * it stores all the relevant information that an email has 
	 */
	private static final long serialVersionUID = -8892108116929089634L;
	/**
	 * email of the user who sent the email
	 */
	private String from;
	/**
	 * if it is a student the email is sent to the professor
	 * if the user is a professor then it sends an email to all the students in the course
	 */
	private Course course;
	/**
	 * the subject (or title) of the  email
	 */
	private String subject;
	/**
	 * the body of the email
	 */
	private String content;
	/**
	 * the password to login to the email account
	 */
	private String password;
	/**
	 * the constructor for the email course 
	 * @param from whom the email is being sent 
	 * @param course the course that is used to determine who is receiving the email
	 * @param subject the title of the email
	 * @param content is the body of the email
	 * @param password the password to login into the email
	 */
	public Email(String from, Course course, String subject, String content, String password)
	{
		this.from = from;
		this.course = course;
		this.subject = subject;
		this.content = content;
		this.password = password;
	}
	/**
	 * @return the sender of the email
	 */
	public String getFrom() {return from;}
	/**
	 * @return the id of the course
	 */
	public Integer getCourseId() {return course.getId();}
	/**
	 * @return the course itself
	 */
	public Course getCourse() {return course;}
	/**
	 * @return the title of the email
	 */
	public String getSubject() {return subject;}
	/**
	 * @return the content of the email
	 */
	public String getContent() {return content;}	
	/**
	 * @return the  password of the email account
	 */
	public String getPassword() {return password;}
}
