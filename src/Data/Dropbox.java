package Data;

import java.io.Serializable;

public class Dropbox implements Serializable{
	private Integer id;
	private Integer assign_id;
	private Integer student_id;
	private String path;
	private Integer grade;
	private String comment;
	private String title;
	public Dropbox(Integer id, Assignment a, Student s, Integer grade, String comment)
	{
		this.id =  id;
		assign_id = a.getId();
		student_id = s.getId();
		path = a.getPath();
		this.grade = grade;
		this.comment = comment;
		title = a.getTitle();
	}
	public Integer getId() {return id;}
	public Integer getAssignId() {return assign_id;}
	public Integer getStudentId() {return student_id;}
	public String getPath() {return path;}
	public Integer getGrade() {return grade;}
	public String getComment() {return comment;}
	public String getTitle() {return title;}
	
}
