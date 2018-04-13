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

import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.Dropbox;
import Data.StudentEnrollment;
import Data.SubmissionFileContainer;
import Database.DatabaseHelper;

/**
 * Student thread runs the server operations for the student client.
 * Client sends a operation which acts as a command for the thread to execute the proper method 
 * to communicate with the database and client.
 * @author Justin, Magnus, Robert
 */
public class StudentThread implements Constants {
	
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
	public StudentThread(BufferedReader sIn, PrintWriter sOut, ObjectOutputStream oOut, ObjectInputStream oIn, DatabaseHelper data) {
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
			try {
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
		if (operation.equals(STUDENT_COURSES)) {
			getStudentCourses(); 
		}
		else if (operation.equals(GET_ASSIGN)) {
			getStudentAssignments();
		}
		else if (operation.equals(DOWNLOAD_ASSIGN)) { 
			downloadAssignment(); 
		}
		else if (operation.equals(SUBMIT_ASSIGN)) {
			submitAssignment(); 
		}
		else if (operation.equals(GET_GRADES)) {
			getGrades();
		}
		else if (operation.equals(SEND_EMAIL)) {
			sendEmail(); 
		}
		else if (operation.equals(EXIT)) {
			exitThread(); 
		}
		else {
			System.out.println("wrong operation");
		}
	}
	
	/**
	 * Reads the course id from the client and constructs an arraylist of students 
	 * enrolled in the course and sends it back to the client.
	 */
	public void getStudentCourses() {
		try {
			String id = stringIn.readLine();
			ArrayList<Integer> courseIdList = database.searchCoursesForStudent(Integer.parseInt(id));
			ArrayList<Course> courseList = new ArrayList<Course>(); 
			for (int i = 0; i < courseIdList.size(); i++) {
				courseList.add(database.searchCourse(courseIdList.get(i)));
			}
			for(int i = 0 ; i < courseList.size(); i++)
			{
				if(courseList.get(i).getActive() ==  false)
				{
					courseList.remove(i);
					i--;
				}
			}
			objectOut.flush();
			objectOut.writeObject(courseList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the course from the client and constructs an arraylist of assignments 
	 * enrolled in the course and sends it back to the client.
	 */
	public  void getStudentAssignments() { 
		try {
			Course c = (Course)objectIn.readObject();
			 System.out.println("Got course on server");
			ArrayList<Assignment> a = database.assignmentList(c.getId());
			for(int i = 0 ; i < a.size(); i++)
			{
				if(a.get(i).getActive() ==  false)
				{
					a.remove(i);
					i--;
				}
			}
			objectOut.flush();
			objectOut.writeObject(a);
		} 
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the selected assignment from the client and retrieves the 
	 * assignment file from the server assignment files and sends it as a file container
	 * to the client.
	 */
	public void downloadAssignment() { 
		
		try {
			Assignment assign = (Assignment)objectIn.readObject();
			
			AssignmentFileContainer fileContainer = database.getAssignFile(assign);
			
			objectOut.flush();
			objectOut.writeObject(fileContainer);
		} 	
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Uploads an assignment from the client by receiving a submission file and storing it 
	 * in the servers submissions file path. Server constructs a submission from the file and
	 * inserts it into the database.
	 */
	public void submitAssignment() { 
		try {
			SubmissionFileContainer container = (SubmissionFileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File("submissions/" + container.getFileName());
			
			if(!newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			
			Dropbox submission = container.getSubmission();
			submission.setPath("submissions/" + container.getFileName());
			
			Random rand = new Random();
			submission.setId(rand.nextInt(9999));
			database.addSubmission(submission);
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
	 * Read the student enrollment from the client and construct a list of 
	 * dropbox submissions for the assignment. the dropbox submissions
	 * are sent back to the client to display grades. 
	 */
	public void getGrades() { 
		try {
			StudentEnrollment c = (StudentEnrollment)objectIn.readObject();
			ArrayList<Assignment> grades = database.assignmentList(c.getCourseId());
			ArrayList<Dropbox> d = new ArrayList<Dropbox>();
			for(int i = 0 ; i< grades.size(); i++)
			{
				d.add(database.getGrades(c.getStudentId(), grades.get(i)));
			}
			objectOut.flush();
			objectOut.writeObject(d);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Read email from the client and constructs a email helper to send emails to the 
	 * professor.
	 */
	public void sendEmail() {
		try {
			Email email = (Email)objectIn.readObject();		
			ArrayList<String> emailList = new ArrayList<String>();
			emailList.add(database.getProfessorEmail(email.getCourse().getProfId()));
			
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
	 * Exits the thread by closing all input output streams.
	 * Sets checkEnd to true;
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
