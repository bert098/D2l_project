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
	private String timeStamp; 
	
	public Dropbox(Integer i, Assignment a, Student s, Integer grade, String comment)
	{
		id = i;
		assign_id = a.getId();
		student_id = s.getId();
		path = a.getPath();
		this.grade = grade;
		this.comment = comment;
		title = a.getTitle();
	}
	
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
	public Integer getId() {return id;}
	public Integer getAssignId() {return assign_id;}
	public Integer getStudentId() {return student_id;}
	public String getPath() {return path;}
	public Integer getGrade() {return grade;}
	public String getComment() {return comment;}
	public String getTitle() {return title;}
	
	@Override 
	public String toString() { 
		if (grade == -1) { 
			return "SUBMITTED BY: Student Id: " + student_id + " Submission Time: " + timeStamp + " UNMARKED";
		}
		return "SUBMITTED BY: Student Id: " + student_id + " Submission Time: " + timeStamp + " Grade: " + grade;
	}
}
