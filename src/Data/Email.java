package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Email implements Serializable{
	private String from;
	private ArrayList<String> to;
	private String subject;
	private String content;
	public Email(String from, ArrayList<String> to, String subject, String content)
	{
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}
	public String getFrom() {return from;}
	public ArrayList<String> geTo() {return to;}
	public String getSubject() {return subject;}
	public String getContent() {return content;}	
}
