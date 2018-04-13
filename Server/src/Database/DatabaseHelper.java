package Database;

import java.util.ArrayList;

import Data.*;
/**
 * 
 * @author Robert, Justin, Magnus
 * this very important class has an instance of every table in the database
 * and is used to access all the various searches needed for the application 
 *
 */
public class DatabaseHelper {
	/**
	 * the user table class used to communicate with the user table
	 */
	private UserTable userTable;
	/**
	 * the course table class that is used to communicate with  the course table 
	 */
	private CourseTable courseTable;
	/**
	 * the assignment table class that is used to communicate with the assignment table 
	 */
	private AssignmentTable assignmentTable;
	/**
	 * the student enrollment class that is used to communicate with the student enrollment table
	 */
	private StudentEnrollmentTable studentEnrollmentTable;
	/**
	 * the submission class that is used to communicate with the  submissions table
	 */
	private SubmissionTable submissionTable;
	/**
	 * the constructor that create a connection to all the classes that connect to the database 
	 * @param pass the password used to access the database 
	 */
	public DatabaseHelper(String pass)
	{
		userTable = new UserTable(pass);
		courseTable = new CourseTable(pass);
		assignmentTable = new AssignmentTable(pass);
		studentEnrollmentTable = new StudentEnrollmentTable(pass);
		submissionTable= new SubmissionTable(pass);
	}
	/**
	 * create all the tables in the data base 
	 */
	public void createAllTables()
	{
		userTable.createUserTable();
		courseTable.createCourseTable();
		assignmentTable.createAssignmentTable();
		studentEnrollmentTable.createStudentEnrollmentTable();
		submissionTable.createSubmissionTable();
	}
	/**
	 * used to get all the courses 
	 * @return an arraylist of all the courses in the database
	 */
	public ArrayList<Course> courseTableToList() {
		return courseTable.courseTableToList();
	}
	/**
	 * used to get all the assignments in the table
	 * @return an array list of all that assignments
	 */
	public ArrayList<Assignment> assignmentTableToList() {
		return assignmentTable.assignmentTableToList();
	}
	/**
	 * inserts a user into the table
	 * @param user the user to be inserted 
	 */
	public void insertUser(User user)
	{
		userTable.addUser(user);
	}
	/**
	 * inserts s submission
	 * @param d the submission being inserted 
	 */
	public void insertSubmission(Dropbox d)
	{
		submissionTable.addSubmission(d);
	}
	/**
	 * inserts an assignment into the table
	 * @param user the new assignment 
	 */
	public void insertAssignment(Assignment user)
	{
		assignmentTable.addAssignment(user);
	}
	/**
	 * inserts a new course into the course table
	 * @param c the  new course
	 */
	public void insertCourse(Course c)
	{
		courseTable.addCourse(c);
	}
	/**
	 * inserts a new student enrollment 
	 * @param s the new student enrollment
	 */
	public void insertStudentEnrollment(StudentEnrollment s)
	{
		studentEnrollmentTable.addEnrollment(s);;
	}
	/**
	 * searches the user table for a user  using their username and password 
	 * @param use the users username
	 * @param pass the users password 
	 * @return the user if found 
	 */
	public User searchUser(String use, String pass)
	{
		return userTable.search(use, pass);
	}
	/**
	 * searches the course tables for a course
	 * @param id is the id of the course
	 * @return the course if its found 
	 */
	public Course searchCourse(int id)
	{
		return courseTable.searchCourse(id);
	}
	/**
	 * searches the table for an assignment 
	 * @param id the id of the assignment 
	 * @return returns the assignment if its found 
	 */
	public Assignment searchAssignment(int id)
	{
		return assignmentTable.search(id);
	}
	/**
	 * searches for a student enrollment
	 * @param id the id of the student enrollment 
	 * @return the student enrollment if it's found 
	 */
	public StudentEnrollment searchStudentEnrollment(int id)
	{
		return studentEnrollmentTable.searchID(id);
	}
	/**
	 * searches the submission table for a submission 
	 * @param id the id of the submission 
	 * @return the dropbox if its found 
	 */
	public Dropbox searchDropBoxTable(int id)
	{
		return submissionTable.search(id);
	}
	/**
	 * gets an assignment and deactivates it 
	 * @param id the id of the assignment that is to be deactivated
	 */
	public void deactivateAssignment(int id) {
		assignmentTable.updateAssignmentStatus(id, false);
	}
	/**
	 * activates an assignment
	 * @param id the assignment to be activated 
	 */
	public void activateAssignment(int id) {
		assignmentTable.updateAssignmentStatus(id, true);
	} 
	/**
	 * gets all the students in a course 
	 * @param id the course id
	 * @return an arraylist of student ids 
	 */
	public ArrayList<Integer> searchStudentEnrollmentByStudent(int id)
	{
		return studentEnrollmentTable.searchStudent(id);
	}
	/**
	 * searches for a user by id rather then by passowrd and username 
	 * @param id the users id
	 * @return the user 
	 */
	public User searchUserTableID(int id)
	{
		return userTable.searchID(id);
	}
	/**
	 * updates the courses status 
	 * @param courseId the id of the course 
	 * @param status the new status 
	 */
	public void updateCourseStatus(Integer courseId, boolean status)
	{
		courseTable.updateCourseStatus(courseId, status);
	}
	/**
	 * this unenrolls a new student 
	 * @param StudentId the students id
	 * @return the unenrolled student 
	 */
	public int unEnroll(Integer StudentId)
	{
		return studentEnrollmentTable.unEnrollStudent(StudentId);
	}
	/**
	 * deletes a student from the table 
	 * @param StudentId the students id 
	 */
	public void delete(Integer StudentId)
	{
		 studentEnrollmentTable.delete(StudentId);
	}
	/**
	 * Gets the emails of all the students in a course 
	 * @param courseId the courses id 
	 * @return an arraylist of emails 
	 */
	public ArrayList<String> getStudentEmails(Integer courseId)
	{
		return userTable.getStudentEmails(studentEnrollmentTable.searchStudent(courseId), courseId);
	}
	/**
	 * gets all the assignments in a course 
	 * @param c the course id 
	 * @return an arraylist of assignments
	 */
	public ArrayList<Assignment> assignmentList(Integer c)
	{
		 return assignmentTable.courseAssignmentTableToList(c);
	}
	/**
	 * returns all ids of the students in a course
	 * @param studentId the id of the student 
	 * @return the array list of student ids 
	 */
	public ArrayList<Integer> searchCoursesForStudent(int studentId) {
		return studentEnrollmentTable.searchCoursesForStudent(studentId);
	}
	/**
	 * finds all the dropboxes for the given assignment 
	 * @param assignmentId the id of the assignment 
	 * @return the arraylist of dropboxes 
	 */
	public ArrayList<Dropbox> searchAssignmentInSubmissions(int assignmentId) {
		return submissionTable.searchAssignment(assignmentId);
	}
	/**
	 * gets all the students in the student table 
	 * @return an arraylist  of  students 
	 */
	public ArrayList<Student> AllStudent() {
		return userTable.getAll();
	}
	/**
	 * gets a professor's email
	 * @param profId the  id of the professor 
	 * @return the string that contains the professors email
	 */
	public String getProfessorEmail(Integer profId)
	{
		return userTable.getUserEmail(profId);
	}
	/**
	 * updates the grade  of a submission 
	 * @param comment the comment left by the professor
	 * @param grade the grade for the submission
	 * @param id the id of the submission 
	 */
	public void gradeSubmission(String comment, String grade, int id) 
	{
		submissionTable.gradeSubmission(comment, grade, id);
	}
	/**
	 * makes an Assignment file container from an assignment in the database 
	 * @param assign the assignment 
	 * @return the Assignment file container
	 */
	public AssignmentFileContainer getAssignFile(Assignment assign)
	{
		return assignmentTable.getAssignmentFile(assign);
	}
	/**
	 * gets a grade for an assignment 
	 * @param studentId the id of the student whos grade is being retrieved 
	 * @param assignment the assignment which was graded 
	 * @return the submission with the grade 
	 */
	public Dropbox getGrades(Integer studentId, Assignment assignment) {
		return submissionTable.GradeForAssignment(studentId, assignment);
	}
	/**
	 * gets a submission from the database and makes a Submission file container from it 
	 * @param submission the submission 
	 * @return Submission file container
	 */
	public SubmissionFileContainer getSubmissionFile(Dropbox submission) {
		return submissionTable.getSubmissionFile(submission);
	}
	/**
	 * adds a submission to to table
	 * @param submission the submission being added 
	 */
	public void addSubmission(Dropbox submission) {
		submissionTable.addSubmission(submission);
	}
}
