package Data;

public class StudentEnrollment {
	private Integer id;
	private Integer studentId;
	private Integer courseId;
	private boolean active;
	
	public StudentEnrollment(Integer id, Student studentId, Course courseId, boolean active)
	{
		this.id = id;
		this.studentId = studentId.getId();
		this.courseId = courseId.getId();
		this.active = active;
	}
	public Integer getId() {return id;}
	public Integer getStudentId() {return studentId;}
	public Integer getCourseId() {return courseId;}
	public boolean getType() {return active;}	
}
