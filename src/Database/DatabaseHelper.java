package Database;

import Data.*;

public class DatabaseHelper {
	private UserTable userTable;
	private CourseTable courseTable;
	private AssignmentTable assignmentTable;
	private StudentEnrollmentTable studentEnrollmentTable;
	private SubmissionTable submissionTable;
	private GradeTable gradeTable;
	private String login;
	private String password;
	private String dataBaseName;
	
	public DatabaseHelper(String log, String pass, String dataBase)
	{
		userTable = new UserTable();
		courseTable = new CourseTable();
		assignmentTable = new AssignmentTable();
		studentEnrollmentTable = new StudentEnrollmentTable();
		submissionTable= new SubmissionTable();
		gradeTable = new GradeTable();
		login = log;
		password = pass;
		dataBaseName = dataBase;	
	}
	
	public  DatabaseHelper()
	{
		userTable = new UserTable();
		courseTable = new CourseTable();
		assignmentTable = new AssignmentTable();
		studentEnrollmentTable = new StudentEnrollmentTable();
		submissionTable= new SubmissionTable();
		gradeTable = new GradeTable();
	}
	public void createAllTables()
	{
		userTable.createUserTable();
		courseTable.createCourseTable();
		assignmentTable.createAssignmentTable();
		studentEnrollmentTable.createStudentEnrollmentTable();
		submissionTable.createSubmissionTable();
		gradeTable.createGradeTable();
	}
	public void insertUser(User user)
	{
		userTable.addUser(user);
	}
	public void insertSubmission(Assignment assign, Student student, int grade, String comment)
	{
		submissionTable.addUSubmission(assign, student, grade, comment);
	}
	public static void main(String [] args)
	{
		DatabaseHelper data = new DatabaseHelper();
		data.createAllTables();
	}
	
	
}
