package Model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.Dropbox;
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;
import Data.SubmissionFileContainer;

/**
 * Professor model handles interactions between the client and the 
 * server's professor thread. 
 * @author Justin, Magnus, Robert
 */
public class ProfessorModel implements Constants{
	
	/**
	 * Read string input from server 
	 */
	private BufferedReader stringIn; 
	
	/**
	 * Write string to server 
	 */
	private PrintWriter stringOut; 
	
	/**
	 * Read Object input from server 
	 */
	private ObjectInputStream objectIn; 
	
	/**
	 * Write object to server 
	 */
	private ObjectOutputStream objectOut; 
	
	/**
	 * Constructs professor model with input and output streams for 
	 * objects and strings
	 * @param in string in
	 * @param out string out 
	 * @param oIn object in
	 * @param oOut object out
	 */
	public ProfessorModel(BufferedReader in, PrintWriter out, ObjectInputStream oIn, ObjectOutputStream oOut) {
		stringIn = in; 
		stringOut = out; 
		objectIn = oIn; 
		objectOut = oOut; 
	}
	
	/**
	 * Send operation to professor thread to handle database interaction
	 * @param operation operation being sent
	 */
	public void sendOperation(String operation) {
		stringOut.flush();
		stringOut.println(operation);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read assignment list from server 
	 * @return ArrayList of assignments
	 */
	public ArrayList<Assignment> readAssignmentList() {
		try { 
			return (ArrayList<Assignment>) objectIn.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read course list from server 
	 * @return ArrayList of courses 
	 */
	public ArrayList<Course> readCourseList() {
		try {
			return (ArrayList<Course>) objectIn.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Deactivate assignment by sending id of assignment 
	 * being deactivated 
	 * @param assignId id of assignment
	 */
	public void sendDeactivateAssignment(Integer assignId) { 
		stringOut.flush();
		stringOut.println(assignId.toString());
	}
	
	/**
	 * Create course by sending course information to 
	 * server for inserting into database
	 * @param course course being inserted
	 */
	public void createCourse(Course course)
	{
		try
		{
			sendOperation(CREATE_COURSE);
			objectOut.flush();
			objectOut.writeObject(course);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
  
	/**
	 * Get arrayList of students by last name from a course
	 * @param course course the students are enrolled in
	 * @return arrayList of students
	 */
	public ArrayList<Student> searchStudentName(Course course)
	{
		ArrayList<Student> s = null;
		try
		{
			
			sendOperation(SEARCH_STUDENT_LASTNAME);
			objectOut.flush();
			objectOut.writeObject(course);
			 s = (ArrayList<Student>)objectIn.readObject();	
		} 
		
		catch(IOException e) {
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Get arrayList of students by id from a course
	 * @param course course the students are enrolled in
	 * @return arrayList of students
	 */
	public ArrayList<Student> searchStudent(Course course)
	{
		ArrayList<Student> s = null;
		try
		{
			
			sendOperation(SEARCH_STUDENT_ID);
			objectOut.flush();
			objectOut.writeObject(course);
			 s = (ArrayList<Student>)objectIn.readObject();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Get arrayList of all students from a course
	 * @param course course the students are enrolled in
	 * @return arrayList of students
	 */
	public ArrayList<Student> searchAll(Course c)
	{
		ArrayList<Student> s = null;
		try
		{
			sendOperation(ALL_STUDENTS);
			objectOut.flush();
			objectOut.writeObject(c);
			 s = (ArrayList<Student>)objectIn.readObject();
			
			
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * Unenroll a student from a course 
	 * @param student Student being unenrolled 
	 * @return ArrayList of students 
	 */
	public ArrayList<Student> unEnroll(Student student)
	{
		ArrayList<Student> s = null;
		try {
			sendOperation(UNENROLL_STUDENT);
			objectOut.flush();
			objectOut.writeObject(student);
			 s = (ArrayList<Student>)objectIn.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Enroll a student into a course 
	 * @param student Student being enrolled 
	 * @return ArrayList of students 
	 */
	public ArrayList<Student> enroll(StudentEnrollment st)
	{
		ArrayList<Student> s = null;
		try {
			sendOperation(ENROLL_STUDENT);
			objectOut.flush();
			objectOut.writeObject(st);
			s = (ArrayList<Student>)objectIn.readObject();
			
			if (s == null) {
				return null;
			}
			for (int i = 0; i < s.size(); i++) {
				System.out.println(s.get(i).toString());
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return s;
	}
	
	/**
	 * Activate a course specified by the course id
	 * @param courseId id of course
	 */
	public void activateCourse(Integer courseId)
	{
		try {
			sendOperation(ACTIVATE_COURSE);
			objectOut.flush();
			objectOut.writeObject(courseId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deactivate a course specified by the course id 
	 * @param courseId id of course 
	 */
	public void deactivateCourse(Integer courseId)
	{
		try {
			sendOperation(DEACTIVATE_COURSE);
			objectOut.flush();
			objectOut.writeObject(courseId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Upload assignment onto server and insert into database.
	 * Sends the assignment container to the server
	 * @param container Assignment file container 
	 */
	public void uploadAssignment(AssignmentFileContainer container)
	{
		try{
			sendOperation(UPLOAD_ASSIGN);
			objectOut.writeObject(container);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send email to the server for sending to all students
	 * @param email Email being sent
	 * @return 
	 */
	public boolean sendEmail(Email email)
	{
		try{
			sendOperation(SEND_EMAIL);
			objectOut.writeObject(email);
			objectOut.flush();
			
			String messageStatus = stringIn.readLine();
			if(messageStatus.equals("MESSAGE_SENT")) {
				return true;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void sendAssignmentId(Integer assignId) {
		stringOut.flush();
		stringOut.println(assignId.toString());
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dropbox> readSubmissionList() {
		try {
			return (ArrayList<Dropbox>) objectIn.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendComments(String comments) {
		stringOut.flush();
		stringOut.println(comments);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendGrade(String grade) {
		stringOut.flush();
		stringOut.println(grade);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendSubmissionId(String id) {
		stringOut.flush();
		stringOut.println(id);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadSubmission(Dropbox submission, String filePath) {
		try { 
			sendOperation(DOWNLOAD_SUB);
			
			objectOut.flush();
			objectOut.writeObject(submission);
			
			SubmissionFileContainer container = (SubmissionFileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File(filePath + "\\" + container.getFileName());
			
			if (!newFile.exists()) 
			{
				newFile.createNewFile();
			}
			
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bufferOutput = new BufferedOutputStream(writer);
			bufferOutput.write(content);
			bufferOutput.close();
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}
