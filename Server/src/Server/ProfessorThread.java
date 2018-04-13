package Server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import Database.DatabaseHelper;
import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.Dropbox;
import Data.Student;
import Data.StudentEnrollment;
import Data.SubmissionFileContainer;

/**
 * Professor thread runs the server operations for the professor client.
 * Client sends a operation which acts as a command for the thread to execute the proper method 
 * to communicate with the database and client.
 * @author Justin, Magnus, Robert
 */
public class ProfessorThread implements Constants {
	
	/**
	 * Operation being run 
	 */
	private String operation; 
	
	/**
	 * database class, provides access methods to all data base table in server 
	 */
	private DatabaseHelper database;
	
	/**
	 * receives object from client 
	 */
	private ObjectOutputStream objectOut; 
	
	/**
	 * write object to client
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * Receive string input from client 
	 */
	private BufferedReader stringIn; 
	
	/**
	 * write string to client 
	 */
	private PrintWriter stringOut;
	
	/**
	 * check if client closes connection 
	 */
	private Boolean checkEnd; 
	
	/**
	 * Constructs thread with socket connections to the client along with the database 
	 * @param sIn string in 
	 * @param sOut string out 
	 * @param oOut object out 
	 * @param oIn object in 
	 * @param data database
	 */
	public ProfessorThread(BufferedReader sIn, PrintWriter sOut, ObjectOutputStream oOut, ObjectInputStream oIn, DatabaseHelper data) {
		stringIn = sIn; 
		stringOut = sOut; 
		objectOut = oOut; 
		objectIn = oIn;
		database = data;
		checkEnd = false;
	}
	
	/**
	 * run the thread by continuously reading the operations being sent from the client 
	 * and executing the operation in readOperation(). Loop exits if client closes 
	 * connection.
	 */
	public void run() {
		while (true) {
			try 
			{
				operation = stringIn.readLine();
				System.out.println(operation);
				Thread.sleep(50);
				readOperation(); 
				if (checkEnd) {
					return;
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			} 
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Reads the connection with a series of if statements to execute the 
	 * proper method for the operation. 
	 */
	public void readOperation() {
		if (operation.equals(PROF_COURSES)) {
			getProfessorCourses(); 
		}
		else if (operation.equals(CREATE_COURSE)) {
			createCourse();
		}
		else if (operation.equals(ACTIVATE_COURSE)) {
			activateCourse(); 
		}
		else if (operation.equals(DEACTIVATE_COURSE))  {
			deactivateCourse(); 
		}
		else if (operation.equals(SEARCH_STUDENT_ID)) { 
			searchStudentId(); 
		}
		else if (operation.equals(SEARCH_STUDENT_LASTNAME)) {
			searchStudentLastName(); 
		}
		else if (operation.equals(ENROLL_STUDENT)) {
			enrollStudent(); 
		}
		else if (operation.equals(UNENROLL_STUDENT)) {
			unenrollStudent(); 
		}
		else if (operation.equals(GET_ASSIGN)) {
			getAssignments(); 
		}
		else if (operation.equals(GRADE_SUBMISSON)) {
			gradeSubmission();
		}
		else if (operation.equals(GET_SUBMISSIONS)) {
			getSubmissions();
		}
		else if (operation.equals(ACTIVATE_ASSIGN)) {
			activateAssignment(); 
		}
		else if (operation.equals(DEACTIVATE_ASSIGN)) {
			deactivateAssignment(); 
		}
		else if (operation.equals(UPLOAD_ASSIGN)) {
			uploadAssign();
		}
		else if (operation.equals(DOWNLOAD_SUB)) {
			downloadSubmission(); 
		}
		else if (operation.equals(SEND_EMAIL)) {
			sendEmail();
		}
		else if(operation.equals(ALL_STUDENTS)){
			getAll();
		}
		else if(operation.equals(EXIT)) {
			exitThread(); 
		}
		else {
			System.out.println(operation + " is incorrect");
		}
	}
	
	/**
	 * sends all the courses in the database to the professor client model
	 */
	public void getProfessorCourses() {
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = database.courseTableToList();
		try {
			objectOut.flush();
			objectOut.writeObject(courseList); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * creates a course by reading the course from the client and inserting it 
	 * into the database. 
	 */
	public void createCourse() 
	{
		try
		{
			Course course = (Course)objectIn.readObject();
			database.insertCourse(course);
			
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * activate the course by updating the active field in the database for the 
	 * selected course on the client side 
	 */
	public void activateCourse() {
		try {
			Integer courseId;
			courseId = (Integer)objectIn.readObject();
			System.out.println(courseId);
			database.updateCourseStatus(courseId, true);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}	
		catch (IOException e)
		{	
			e.printStackTrace();
		}
	}
	
	/**
	 * deactivate the course by updating the active field in the database for the 
	 * selected course on the client side 
	 */
	public void deactivateCourse() {
		try {
			Integer courseId;
			courseId = (Integer)objectIn.readObject();
			System.out.println(courseId);
			database.updateCourseStatus(courseId, false);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}	
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * reads current course from client and searches all the students in the course and
	 * sends if back to the client as an arraylist 
	 */
	public void searchStudentId() 
	{
		try {
			Course course = (Course)objectIn.readObject();
			ArrayList<Integer> a = database.searchStudentEnrollmentByStudent(course.getId());
			ArrayList<Student> s = new  ArrayList<Student>();
			for(int i = 0; i < a.size(); i++)
			{
				s.add((Student)database.searchUserTableID(a.get(i)));
			}
			objectOut.flush();
			objectOut.writeObject(s); 
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads current course from client and searches all the students in the course and
	 * sends if back to the client as an arraylist 
	 */
	public void searchStudentLastName() {
		try {
			Course course = (Course)objectIn.readObject();
			ArrayList<Integer> a = database.searchStudentEnrollmentByStudent(course.getId());
			ArrayList<Student> s = new  ArrayList<Student>();
			for(int i = 0; i < a.size(); i++)
			{
				s.add((Student)database.searchUserTableID(a.get(i)));
			}
			objectOut.flush();
			objectOut.writeObject(s); 
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enroll student in class by reading student enrollment from client 
	 * and inserting the enrollment into the database. Sends an updated 
	 * student list with the enrollment back to the client for displaying 
	 */
	public void enrollStudent() {
		try {
			StudentEnrollment st = (StudentEnrollment)objectIn.readObject();
			
			Student s = (Student)database.searchUserTableID(st.getStudentId());
			if(s == null)
			{
				objectOut.flush();
				objectOut.writeObject(null);
			}
			else
			{
				database.insertStudentEnrollment(st);
				ArrayList<Integer> a = database.searchStudentEnrollmentByStudent(st.getCourseId());
				ArrayList<Student> studentList = new ArrayList<Student>();
				for(int i = 0; i < a.size(); i++)
				{
					studentList.add((Student)database.searchUserTableID(a.get(i)));
				}
				objectOut.flush();
				objectOut.writeObject(studentList);
			}
		} 
		catch (ClassNotFoundException | IOException  | ClassCastException  e) {
			try {
				objectOut.flush();
				objectOut.writeObject(null);
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}	
	}

	/**
	 * Unenroll student in class by reading student enrollment from client 
	 * and deleting the enrollment from the database. Sends an updated 
	 * student list with the enrollment back to the client for displaying 
	 */
	public void unenrollStudent() {
		try {
			Student student = (Student)objectIn.readObject();
			int n = database.unEnroll(student.getId());
			database.delete(student.getId());
			ArrayList<Integer> a = database.searchStudentEnrollmentByStudent(n);
			ArrayList<Student> s = new  ArrayList<Student>();
			for(int i = 0; i < a.size(); i++)
			{
				s.add((Student)database.searchUserTableID(a.get(i)));
			}
			objectOut.flush();
			objectOut.writeObject(s); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Access assignments in database and send all assignments in the database 
	 * to the client as an arrayList.
	 */
	public void getAssignments() {
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		assignmentList = database.assignmentTableToList();
		try {
			objectOut.flush();
			objectOut.writeObject(assignmentList); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the grade and comment section in the database of a submission
	 * with comments and grade from the client 
	 */
	public void gradeSubmission() {
		try {
			String comments = stringIn.readLine();
			String grade = stringIn.readLine();
			int id = Integer.parseInt(stringIn.readLine());
			database.gradeSubmission(comments, grade, id);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets an arraylist of submissions that match the assignment id that is read from
	 * the client. The submission list is sent to the clinet for displaying.
	 */
	public void getSubmissions() {
		ArrayList<Dropbox> submissionList = new ArrayList<Dropbox>();
		try {
			int assignmentId = Integer.parseInt(stringIn.readLine());
			submissionList = database.searchAssignmentInSubmissions(assignmentId);
			for (int i = 0; i < submissionList.size(); i++) {
				System.out.println(submissionList.get(i));
			}
			objectOut.flush(); 
			objectOut.writeObject(submissionList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Activate the assignment by updating the active field in the database for the 
	 * selected assignment on the client side 
	 */
	public void activateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.activateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deactivate the assignment by updating the active field in the database for the 
	 * selected assignment on the client side 
	 */
	public void deactivateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.deactivateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Upload an assignment onto the server by receiving the file from the client and 
	 * saving the file contents into the assignments file path specified in the project folder. 
	 * Assignment information is added to the database. 
	 */
	public void uploadAssign() {
		try {
			AssignmentFileContainer container = (AssignmentFileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File("assignments/" + container.getFileName());
			
			if(!newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			
			
			Assignment assign = container.getAssignment();
			assign.setPath("assignments/" + container.getFileName());
			
			Random rand = new Random();
			assign.setId(rand.nextInt(9999));
			database.insertAssignment(assign);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}	
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a submission file to the client by reading the selected submission from the client 
	 * and retrieving the file linked to the submission path in the database.
	 */
	public void downloadSubmission() {
		try { 
			Dropbox submission = (Dropbox)objectIn.readObject();
			
			SubmissionFileContainer fileContainer = database.getSubmissionFile(submission);
			
			objectOut.flush();
			objectOut.writeObject(fileContainer);
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a email to all the students in the class by reading the email body from the
	 * client and calling the email helper to send the email.
	 */
	public void sendEmail() {
		try {
			Email email = (Email)objectIn.readObject();			
			ArrayList<String> emailList = database.getStudentEmails(email.getCourseId());
			
			EmailHelper emailHelper = new EmailHelper(email, emailList);
			boolean messageSent = emailHelper.sendEmail();
			if(messageSent) {
				stringOut.println("MESSAGE_SENT");
			}
			else {
				stringOut.println("MESSAGE_FAILED");
			}
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * sends a list of all students enrolled in a course to client by reading
	 * the course from the client and constructing an arraylist of students in the 
	 * course for sending.
	 */
	public void getAll(){
		ArrayList<Student> s = database.AllStudent();
		try {
			Course c = (Course)objectIn.readObject();
			ArrayList<Integer> i = database.searchStudentEnrollmentByStudent(c.getId());
			
			for(int j = 0 ; j < s.size(); j++)
			{
				for(int r = 0; r < i.size(); r++)
				{
					if((s.get(j).getId()).equals(i.get(r)))
					{
						s.remove(j);
						j--;
						break;
					}
				}
			}
			objectOut.flush();
			objectOut.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes all input and output streams and sets checkEnd to true 
	 * so that the run method can exit loop.
	 */
	public void exitThread() {
		try {
			objectOut.close();
			objectIn.close();
			stringIn.close();
			stringOut.close();
			checkEnd = true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
