package Data;
/**
 * @author Robert, Justin, Magnus
 * this class holds all the constants need when communicating with the database
 *
 */
public interface Constants {
	/**
	 * what is assigned to a professor in the type field 
	 */
	public static final Character PROFESSOR = 'P';
	/**
	 * what is assigned to a student in the type field 
	 */
	public static final Character STUDENT = 'S';
	/**
	 * used to send the professors courses from the database to the professor model 
	 */
	public static final String PROF_COURSES = "PROF_COURSES";
	/**
	 * used to add a new course to the database
	 */
	public static final String CREATE_COURSE = "CREATE_COURSE";
	/**
	 * used to update an inactive course to active
	 */
	public static final String ACTIVATE_COURSE = "ACTIVATE_COURSE";
	/**
	 * used to deactivate an active course
	 */
	public static final String DEACTIVATE_COURSE = "DEACTIVATE_COURSE";
	/**
	 * used to search the database for a student 
	 */
	public static final String SEARCH_STUDENT_ID = "SEARCH_STUDENT_ID";
	/**
	 * used to search the database using the students last name 
	 */
	public static final String SEARCH_STUDENT_LASTNAME = "SEARCH_STUDENT_LASTNAME";
	/**
	 * used to enroll an unenrolled student 
	 */
	public static final String ENROLL_STUDENT = "ENROLL_STUDENT";
	/**
	 * used to unenroll an enrolled student 
	 */
	public static final String UNENROLL_STUDENT = "UNENROLL_STUDENT"; 
	/**
	 * use to get an assignment from the datbase 
	 */
	public static final String GET_ASSIGN = "GET_ASSIGN";
	/**
	 * used to to update the grade of an assignment 
	 */
	public static final String GRADE_SUBMISSON = "GRADE_SUBMISSION";
	/**
	 * used to retrieve a submission from the database 
	 */
	public static final String GET_SUBMISSIONS = "GET_SUBMISSIONS";
	/**
	 * used to activate an assignment 
	 */
	public static final String ACTIVATE_ASSIGN = "ACTIVATE_ASSIGN";
	/**
	 * used to deactivate an assignment 
	 */
	public static final String DEACTIVATE_ASSIGN = "DEACTIVATE_ASSIGN";
	/**
	 * used to upload a new assignment 
	 */
	public static final String UPLOAD_ASSIGN = "UPLOAD_ASSIGN";
	/**
	 * used to retrieve all of a students courses 
	 */
	public static final String STUDENT_COURSES = "STUDENT_COURSES";
	/**
	 * used to download an assignment 
	 */
	public static final String DOWNLOAD_ASSIGN = "DOWNLOAD_ASSIGN";
	/**
	 * used to submit an assignment 
	 */
	public static final String SUBMIT_ASSIGN = "SUBMIT_ASSIGN";
	/**
	 * used to retrieve all of a students grades
	 */
	public static final String GET_GRADES = "GET_GRADES";
	/**
	 * used to send an email
	 */
	public static final String SEND_EMAIL = "SEND_EMAIL";
	/**
	 * used to retrieve all the students in a course
	 */
	public static final String ALL_STUDENTS = "ALL_STUDENTS";
	/**
	 * used to download a submission
	 */
	public static final String DOWNLOAD_SUB = "DOWNLOAD_SUB";
	/**
	 * used to exit the server 
	 */
	public static final String EXIT = "EXIT";
}
