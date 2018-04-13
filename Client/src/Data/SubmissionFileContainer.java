package Data;
/**
 * 
 * @author Robert, Justin, Magnus
 *	Provides data fields and methods in order to create a class representing a SubmissionContainer
 * 	for this application
 * this contains all the useful data needed to submit a file 
 */
public class SubmissionFileContainer extends FileContainer{
	
	/**
	 * the serialization id 
	 */
	private static final long serialVersionUID = 7306691467404914933L;
	/**
	 * the submission
	 */
	private Dropbox submission;
	/**
	 * @return the dropbox that holds the submission
	 */
	public Dropbox getSubmission() {return submission;}
	/**
	 * the constructor for the SubmissionFileContainer constructor
	 * @param fileArr the array of bytes that holds the file
	 * @param fileName the name of the file
	 * @param submission the dropbox that holds the file
	 */
	public SubmissionFileContainer(byte[] fileArr, String fileName, Dropbox submission)
	{
		super(fileArr, fileName);
		this.submission = submission;
	}

}
