package Data;

public class AssignmentFileContainer extends FileContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7009087033670052089L;
	
	private Assignment assignment;
	
	public Assignment getAssignment() {return assignment;}
	
	public AssignmentFileContainer(byte[] fileArr, String fileName, Assignment assignment)
	{
		super(fileArr, fileName);
		this.assignment = assignment;
	}
}
