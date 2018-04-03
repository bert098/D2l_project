package Data;

public class StudentEnrollment {
	private Integer id;
	private Integer studentId;
	private Integer courseId;
	
	public StudentEnrollment(Integer id, Student studentId, Course courseId)
	{
		this.id = id;
		this.studentId = studentId.getId();
		this.courseId = courseId.getId();
	}
	public Integer getId() {return id;}
	public Integer getStudentId() {return studentId;}
	public Integer getCourseId() {return courseId;}	
}
