package Data;

import java.io.Serializable;

public class FileContainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8047145445625243701L;
	
	private byte[] fileArr;
	private String fileName;
	private Assignment assignment;
	
	public byte[] getFileArr() {return fileArr;}
	public String getFileName() {return fileName;}
	public Assignment getAssignment() {return assignment;}
	
	public FileContainer(byte[] fileArr, String fileName, Assignment assignment)
	{
		this.fileArr = fileArr;
		this.fileName = fileName;
		this.assignment = assignment;
	}
}
