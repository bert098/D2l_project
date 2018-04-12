package Server;

import java.io.BufferedReader;
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
import Data.FileContainer;
import Database.DatabaseHelper;

public class StudentThread implements Constants {
	private String operation; 
	private DatabaseHelper database;
	
	private ObjectOutputStream objectOut; 
	
	//input stream to receive messages from client 
	private ObjectInputStream objectIn;
	
	private BufferedReader stringIn; 
	
	private PrintWriter stringOut;
	
	public StudentThread(BufferedReader sIn, PrintWriter sOut, ObjectOutputStream oOut, ObjectInputStream oIn, DatabaseHelper data) {
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
		else {
			System.out.println("wrong operation");
		}
	}
	
	public void getStudentCourses() {
		try {
			String id = stringIn.readLine();
			ArrayList<Integer> courseIdList = database.searchCoursesForStudent(Integer.parseInt(id));
			ArrayList<Course> courseList = new ArrayList<Course>(); 
			for (int i = 0; i < courseIdList.size(); i++) {
				courseList.add(database.searchCourse(courseIdList.get(i)));
			}
			objectOut.flush();
			objectOut.writeObject(courseList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getStudentAssignments() { 
		try {
			Course c = (Course)objectIn.readObject();
			ArrayList<Assignment> a = database.assignmentList(c);
			objectOut.flush();
			objectOut.writeObject(a);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public void submitAssignment() { 
		//todo
	}
	
	public void getGrades() { 
		//todo
	}
	
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

}
