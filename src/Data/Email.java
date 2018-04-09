package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Email implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892108116929089634L;
	
	private String from;
	private ArrayList<String> to;
	private String subject;
	private String content;
	private String password;
	
	public Email(String from, ArrayList<String> to, String subject, String content, String password)
	{
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.password = password;
	}
	public String getFrom() {return from;}
	public ArrayList<String> geTo() {return to;}
	public String getSubject() {return subject;}
	public String getContent() {return content;}	
	public String getPassword() {return password;}
}
