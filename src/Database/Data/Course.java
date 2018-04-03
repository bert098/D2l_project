package Data;

import java.io.Serializable;

public class Course implements Serializable {
	private  Integer id;
	private Integer prof_id;
	private String name;
	private boolean active;
	
	public Course(Professor p, Integer id, String name, boolean active)
	{
		this.id = id;
		prof_id = p.getId();
		this.name = name;
		this.active = active;
	}
	public Integer getId() {return id;}
	public Integer getProfId() {return prof_id;}
	public String getName() {return name;}
	public boolean getActive() {return active;}
}
