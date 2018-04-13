package Data;

import java.io.Serializable;
/**
 * @author Robert, Justin, Magnus
 * this class is the File container the holds the assignment as an array 
 * of  bytes to transport is across a server 
 *
 */
public abstract class FileContainer implements Serializable{

	/**
	 * the serialization number 
	 */
	private static final long serialVersionUID = -8047145445625243701L;
	/**
	 * The array that holes the bytles
	 */
	private byte[] fileArr;
	/**
	 * the name of the file  
	 */
	private String fileName;
	
	/**
	 * 
	 * @return the array of bytes
	 */
	public byte[] getFileArr() {return fileArr;}
	/**
	 * @return the name of the file 
	 */
	public String getFileName() {return fileName;}
	/**
	 * the constructor for the FileContainer class 
	 * @param fileArr the array with the  bytes
	 * @param fileName the  name of the file 
	 */
	public FileContainer(byte[] fileArr, String fileName)
	{
		this.fileArr = fileArr;
		this.fileName = fileName;
	}
}
