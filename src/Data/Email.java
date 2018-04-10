package Data;

import java.io.Serializable;

public class Email implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892108116929089634L;
	
	private String from;
	private Integer courseId;
	private String subject;
	private String content;
	private String password;
	
	public Email(String from, Integer courseId, String subject, String content, String password)
	{
		this.from = from;
		this.courseId = courseId;
		this.subject = subject;
		this.content = content;
		this.password = password;
	}
	public String getFrom() {return from;}
	public Integer getCourseId() {return courseId;}
	public String getSubject() {return subject;}
	public String getContent() {return content;}	
	public String getPassword() {return password;}
}
