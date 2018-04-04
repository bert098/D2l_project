package Database;

import Data.*;

public class DatabaseHelper {
	private  UserTable userTable;
	private CourseTable courseTable;
	private AssignmentTable assignmentTable;
	private StudentEnrollmentTable studentEnrollmentTable;
	private SubmissionTable submissionTable;
	private GradeTable gradeTable;
	private String login;
	private String password;
	private String dataBaseName;
	public  DatabaseHelper(String log, String pass, String dataBase)
	
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
	public void insertSubmission(Dropbox d)
	{
		submissionTable.addSubmission(d);
	}
	public void insertAssignment(Assignment user)
	{
		assignmentTable.addAssignment(user);
	}
	public void insertCourse(Course c)
	{
		courseTable.addCourse(c);
	}
	public void  insertGrade(Grade g)
	{
		gradeTable.addGrade(g);
	}
	public void insertStudentEnrollment(StudentEnrollment s)
	{
		studentEnrollmentTable.addEnrollment(s);;
	}
	public User searchUser(String use, String pass)
	{
		return userTable.search(use, pass);
	}
	public Course searchCourse(int id)
	{
		return courseTable.searchCourse(id);
	}
	public Assignment searchAssignment(int id)
	{
		return assignmentTable.search(id);
	}
	public Grade searchGrade(int id)
	{
		return gradeTable.searchID(id);
	}
	public StudentEnrollment searchStudentEnrollment(int id)
	{
		return studentEnrollmentTable.searchID(id);
	}
	public Dropbox searchDropBoxTable(int id)
	{
		return submissionTable.search(id);
	}

	
	
}
