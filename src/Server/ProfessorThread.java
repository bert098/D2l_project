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

import javax.swing.JOptionPane;

import Database.DatabaseHelper;
import Data.Assignment;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;
import Data.User;

public class ProfessorThread implements Constants {
	private String operation; 
	private DatabaseHelper database;
	
	private ObjectOutputStream objectOut; 
	
	//input stream to receive messages from client 
	private ObjectInputStream objectIn;
	
	private BufferedReader stringIn; 
	
	private PrintWriter stringOut;
	
	public ProfessorThread(BufferedReader sIn, PrintWriter sOut, ObjectOutputStream oOut, ObjectInputStream oIn, DatabaseHelper data) {
		stringIn = sIn; 
		stringOut = sOut; 
		objectOut = oOut; 
		objectIn = oIn;
		database = data;
	}
	
	public void run() {
		while (true) {
			try {
				operation = stringIn.readLine();
				System.out.println(operation);
				Thread.sleep(50);
				readOperation(); 
			}
			catch (IOException ex) {
				ex.printStackTrace();
			} 
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
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
		else if (operation.equals(GRADE_SUBMISSION)) {
			gradeSubmission();
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
		else if (operation.equals(SEND_EMAIL)) {
			sendEmail();
		}
		else {
			System.out.println(operation + " is incorrect");
		}
	}
	
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
	//this
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
	//this
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
	
	public void enrollStudent() {
		
			try {
				StudentEnrollment st = (StudentEnrollment)objectIn.readObject();
				
				Student s = (Student)database.searchUserTableID(st.getStudentId());
				if(s == null)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Student number.",
							"Error Message", JOptionPane.PLAIN_MESSAGE);
					objectOut.flush();
					objectOut.writeObject(null);
				}
				else
				{
					database.insertStudentEnrollment(st);
					ArrayList<Integer> a = database.searchStudentEnrollmentByStudent(st.getCourseId());
					ArrayList<Student> studentList = new  ArrayList<Student>();
					for(int i = 0; i < a.size(); i++)
					{
						studentList.add((Student)database.searchUserTableID(a.get(i)));
					}
					objectOut.flush();
					objectOut.writeObject(studentList);
				}
				} catch (ClassNotFoundException | IOException  | ClassCastException  e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid Student number.",
						"Error Message", JOptionPane.PLAIN_MESSAGE);
				try {
					objectOut.flush();
					objectOut.writeObject(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

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
	
	public void gradeSubmission() {
		//todo
	}
	
	public void activateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.activateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.deactivateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void uploadAssign() {
		try {
			FileContainer container = (FileContainer)objectIn.readObject();
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
			database.addAssignment(container.getAssignment());
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
	
	public void sendEmail() {
		try {
			Email email = (Email)objectIn.readObject();
			
			System.out.println(email.getCourseId());
			System.out.println(email.getFrom());
			System.out.println(email.getPassword());
			
			ArrayList<String> emailList = database.getStudentEmails(email.getCourseId());
			
			EmailHelper emailHelper = new EmailHelper(email, emailList);
			boolean messageSent = emailHelper.sendEmail();
			if(messageSent) {
				stringOut.println("MESSAGE_SENT");
			}
			else {
				stringOut.print("MESSAGE_FAILED");
			}
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
