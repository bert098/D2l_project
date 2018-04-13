package Data;

import java.io.Serializable;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing a Student's enrollment
 * for this application
 * it keeps track of every course that a student is in and therefore makes it much easier
 * to find out exactly which student is in every course
 *
 */
public class StudentEnrollment implements Serializable{
	/**
	 * the serialization number
	 */
	private static final long serialVersionUID = -5712827520506574254L;
	/**
	 * the id of the student enrollment 
	 */
	private Integer id;
	/**
	 * the id of the student being enrolled 
	 */
	private Integer studentId;
	/**
	 * the id of the course the student is being enrolled in
	 */
	private Integer courseId;
	/**
	 * the constructor for the student enrollment 
	 * @param id is the student being enrolled 
	 * @param studentId is the student being enrolled
	 * @param courseId the course in which the student is being enrolled
	 */
	public StudentEnrollment(Integer id, Student studentId, Course courseId)
	{
		this.id = id;
		this.studentId = studentId.getId();
		this.courseId = courseId.getId();
	}
	/**
	 * the constructor for the student enrollment 
	 * @param id is the student being enrolled 
	 * @param studentId is the id of the student being enrolled
	 * @param courseId the id of the course in which the student is being enrolled
	 */
	public StudentEnrollment(Integer id, Integer studentId, Integer courseId)
	{
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	/**
	 * 
	 * @return the id of the enrollment
	 */
	public Integer getId() {return id;}
	/**
	 * 
	 * @return the students id
	 */
	public Integer getStudentId() {return studentId;}
	/**
	 * 
	 * @return the id of the course 
	 */
	public Integer getCourseId() {return courseId;}
}
