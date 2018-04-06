package Data;

import java.io.Serializable;

public class StudentEnrollment implements Serializable{
	private Integer id;
	private Integer studentId;
	private Integer courseId;
	
	public StudentEnrollment(Integer id, Student studentId, Course courseId)
	{
		this.id = id;
		this.studentId = studentId.getId();
		this.courseId = courseId.getId();
	}
	public StudentEnrollment(Integer id, Integer studentId, Integer courseId)
	{
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	public Integer getId() {return id;}
	public Integer getStudentId() {return studentId;}
	public Integer getCourseId() {return courseId;}
}
