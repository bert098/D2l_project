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
	public User searchUser()
	{
		return userTable.search(69);
	}
	public static void main(String [] args)
	{
		DatabaseHelper data = new DatabaseHelper();
		data.createAllTables();
		Professor p = new Professor(69, "Lmao", "ILoveHentai", 'P', "hello@gmail.com", "Magnus", "Lyngberg");
		//Course c = new Course(p, 420, "Ensf", true);
		//Assignment a = new Assignment(200,  c, "Final", "C:\\Users\\Robert\\Desktop\\Folders", true, "April 3, 2017");
		//Student s = new Student(69, "Lmao", "ILovePokemon", 'S', "hello@gmail.com", "Robert", "Dumitru");
		//Grade g = new Grade(s, 100, c,  a , 69);
		//StudentEnrollment se = new StudentEnrollment(100, s, c);
		data.insertUser(p);
		User pr = data.searchUser();
		System.out.println(pr.getFirstName() +  " " + pr.getLastName());
	}
	
	
}
