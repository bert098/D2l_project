package Data;
/**
 * @author Robert, Justin, Magnus
 * Provides data fields and methods in order to create a class representing an assignment folder
 * for this application
 * its a class meant to hold an assignment an also hold the 
 * same assignment in bytes to transfer from the student to the professor and back 
 */
public class AssignmentFileContainer extends FileContainer{

	/**
	 * The serialization id 
	 */
	private static final long serialVersionUID = -7009087033670052089L;
	/**
	 * the assignment 
	 */
	private Assignment assignment;
	/**
	 * @return the assignment 
	 */
	
	public Assignment getAssignment() {return assignment;}
	/**
	 * the constructor for the  assignment file container 
	 * @param fileArr is the array of bytes representing the assignment 
	 * @param fileName is the is the name of the file 
	 * @param assignment is the assignment that is being transported 
	 */
	public AssignmentFileContainer(byte[] fileArr, String fileName, Assignment assignment)
	{
		super(fileArr, fileName);
		this.assignment = assignment;
	}
}
