package Database;

import java.util.ArrayList;

import Data.*;

public class DatabaseHelper {
	private UserTable userTable;
	private CourseTable courseTable;
	private AssignmentTable assignmentTable;
	private StudentEnrollmentTable studentEnrollmentTable;
	private SubmissionTable submissionTable;
	private GradeTable gradeTable;
	
	public DatabaseHelper(String pass)
	{
		userTable = new UserTable(pass);
		courseTable = new CourseTable(pass);
		assignmentTable = new AssignmentTable(pass);
		studentEnrollmentTable = new StudentEnrollmentTable(pass);
		submissionTable= new SubmissionTable(pass);
		gradeTable = new GradeTable(pass);
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
	public ArrayList<Course> courseTableToList() {
		return courseTable.courseTableToList();
	}
	public ArrayList<Assignment> assignmentTableToList() {
		return assignmentTable.assignmentTableToList();
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

	public void deactivateAssignment(int id) {
		assignmentTable.updateAssignmentStatus(id, false);
	}
	
	public void activateAssignment(int id) {
		assignmentTable.updateAssignmentStatus(id, true);
	} 
	
	public ArrayList<Integer> searchStudentEnrollmentByStudent(int id)
	{
		return studentEnrollmentTable.searchStudent(id);
	}
	
	public User searchUserTableID(int id)
	{
		return userTable.searchID(id);
	}
	
	public void updateCourseStatus(Integer courseId, boolean status)
	{
		courseTable.updateCourseStatus(courseId, status);
	}

	public void addAssignment(Assignment assignment)
	{
		assignmentTable.addAssignment(assignment);
	}

	public int unEnroll(Integer StudentId)
	{
		return studentEnrollmentTable.unEnrollStudent(StudentId);
	}
	public void delete(Integer StudentId)
	{
		 studentEnrollmentTable.delete(StudentId);
	}
	
	public ArrayList<String> getStudentEmails(Integer courseId)
	{
		return userTable.getStudentEmails(studentEnrollmentTable.searchStudent(courseId), courseId);
	}

	public ArrayList<Assignment> assignmentList(Course c)
	{
		 return assignmentTable.courseAssignmentTableToList(c);
	}

	public ArrayList<Integer> searchCoursesForStudent(int studentId) {
		return studentEnrollmentTable.searchCoursesForStudent(studentId);
	}

	public ArrayList<Dropbox> searchAssignmentInSubmissions(int assignmentId) {
		return submissionTable.searchAssignment(assignmentId);
	}
	
	public ArrayList<Student> AllStudent() {
		return userTable.getAll();
	}
	
	public String getProfessorEmail(Integer profId)
	{
		return userTable.getUserEmail(profId);
	}

}
