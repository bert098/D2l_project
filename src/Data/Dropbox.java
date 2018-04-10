package Data;

import java.io.Serializable;

public class Dropbox implements Serializable{
	private Integer assign_id;
	private Integer student_id;
	private String path;
	private Integer grade;
	private String comment;
	private String title;
	private String timeStamp; 
	
	public Dropbox(Assignment a, Student s, Integer grade, String comment)
	{
		assign_id = a.getId();
		student_id = s.getId();
		path = a.getPath();
		this.grade = grade;
		this.comment = comment;
		title = a.getTitle();
	}
	public Dropbox(Integer aId, Integer sId, String p, Integer g, String c, String t, String ts) 
	{
		assign_id = aId;
		student_id = sId; 
		path = p; 
		grade = g; 
		comment = c; 
		title = t;
		timeStamp = ts;
	}
	public Integer getAssignId() {return assign_id;}
	public Integer getStudentId() {return student_id;}
	public String getPath() {return path;}
	public Integer getGrade() {return grade;}
	public String getComment() {return comment;}
	public String getTitle() {return title;}
	
	@Override 
	public String toString() { 
		String s = "Assignment Id: " + assign_id + " Student Id: " + student_id + 
					" Assignment Title: " + title + " Time Stamp: " + timeStamp;
		return s;
	}
}
