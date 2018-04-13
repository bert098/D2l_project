package Data;

import java.io.Serializable;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing a course
 * for this application
 * this stores all the useful data  needed in a course
 *
 */
public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1051123387097514863L;
	/**
	 *the id of the course 
	 */
	private  Integer id;
	/**
	 * the id of the professor who created this course
	 */
	private Integer prof_id;
	/**
	 * the name of the course
	 */
	private String name;
	/**
	 * whether the course is active 
	 */
	private boolean active;
	/**
	 * this is the constructor of the course
	 * @param i is the id of the course
	 * @param pId is the id of the professor who made the course
	 * @param n is the name of the course
	 * @param a is whether the course is active or not 
	 */
	public Course(Integer i, Integer pId, String n, boolean a) {
		id = i; 
		prof_id = pId; 
		name = n; 
		active = a; 
	}
	/**
	 * this is another constructor for course
	 * @param p is the professor who made the course
	 * @param id is the id of the course
	 * @param name is the name of the course 
	 * @param active is whether the course is active or not 
	 */
	public Course(Professor p, Integer id, String name, boolean active)
	{
		this.id = id;
		prof_id = p.getId();
		this.name = name;
		this.active = active;
	}
	/**
	 * is a method that takes the course and displays its name and id and if
	 * it is active 
	 */
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
	/**
	 * @return the id of the course
	 */
	public Integer getId() {return id;}
	/**
	 * @return the professor's id
	 */
	public Integer getProfId() {return prof_id;}
	/**
	 * @return the name of the  course
	 */
	public String getName() {return name;}
	/**
	 * @return the active value
	 */
	public boolean getActive() {return active;}

}
