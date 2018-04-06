package Data;

import java.io.Serializable;

public class Course implements Serializable {
	private  Integer id;
	private Integer prof_id;
	private String name;
	private boolean active;
	
	public Course(Integer i, Integer pId, String n, boolean a) {
		id = i; 
		prof_id = pId; 
		name = n; 
		active = a; 
	}
	
	public Course(Professor p, Integer id, String name, boolean active)
	{
		this.id = id;
		prof_id = p.getId();
		this.name = name;
		this.active = active;
	}
	
	@Override
	public String toString() { 
		String line = name + " " + id + " ";
		if(active)
		{
			line += "Active";
		}
		else
		{
			line += "Inactive";
		}
		return line;
	}
	
	public Integer getId() {return id;}
	public Integer getProfId() {return prof_id;}
	public String getName() {return name;}
	public boolean getActive() {return active;}

}
