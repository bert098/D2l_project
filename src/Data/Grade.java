package Data;

import java.io.Serializable;

public class Grade implements Serializable{
	private Integer id;
	private Integer student_id;
	private Integer grade;
	private Integer course_id;
	private Integer assign_id;
	public Grade(Student s, Integer grade, Course c, Assignment a, Integer id)
	{
		this.id = id;
		student_id = s.getId();
		this.grade = grade;
		course_id = c.getId();
		assign_id = a.getId();
		
	}
	public Integer getId() {return id;}
	public Integer getStudentId() {return student_id;}
	public Integer getGrade() {return grade;}
	public Integer getCourseId() {return course_id;}
	public Integer getAssignId() {return assign_id;}
}
