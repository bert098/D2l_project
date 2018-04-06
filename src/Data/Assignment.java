package Data;

import java.io.Serializable;

public class Assignment implements Serializable{
	private Integer id;
	private Integer courseId;
	private String title;
	private String path;
	private boolean active;
	private String date;
	public Assignment(Integer id, Course courseId, String title,  String path, boolean active, String date)
	{
		this.id = id;
		this.courseId = courseId.getId();
		this.title = title;
		this.path = path;
		this.active = active;
		this.date = date;
	}
	public Integer getId() {return id;}
	public Integer getCourseId() {return courseId;}
	public String getTitle() {return title;}
	public boolean getActive() {return active;}
	public String getPath() {return path;}
	public String getDate() {return date;}
	
	public void setId(Integer id) {this.id = id;}
	public void setPath(String path) {this.path = path;}
}
