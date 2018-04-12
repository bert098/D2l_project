package Data;

public class SubmissionFileContainer extends FileContainer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7306691467404914933L;
	
	private Dropbox submission;
	
	public Dropbox getSubmission() {return submission;}
	
	public SubmissionFileContainer(byte[] fileArr, String fileName, Dropbox submission)
	{
		super(fileArr, fileName);
		this.submission = submission;
	}

}
