package Data;

import java.io.Serializable;

public abstract class FileContainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8047145445625243701L;
	
	private byte[] fileArr;
	private String fileName;
	
	public byte[] getFileArr() {return fileArr;}
	public String getFileName() {return fileName;}
	
	public FileContainer(byte[] fileArr, String fileName)
	{
		this.fileArr = fileArr;
		this.fileName = fileName;
	}
}
