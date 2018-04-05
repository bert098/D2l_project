package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Database.DatabaseHelper;
import Data.Constants;
import Data.Course;

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
				readOperation(); 
			}
			catch (IOException ex) {
				ex.printStackTrace();
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
		else if (operation.equals(GET_ASSIGN_SUBMISSIONS)) {
			getAssignmentSubmissions(); 
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
		else {
			System.out.println(operation + " is incorrect");
		}
	}
	
	public void getProfessorCourses() {
		System.out.println("test");
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = database.courseTableToList();
		try {
			objectOut.reset();
			objectOut.writeObject(courseList); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void createCourse() {
		//todo
	}
	
	public void activateCourse() {
		//todo
	}
	
	public void deactivateCourse() {
		//todo
	}
	
	public void searchStudentId() {
		//todo
	}
	
	public void searchStudentLastName() {
		//todo
	}
	
	public void enrollStudent() {
		//todo
	}
	
	public void unenrollStudent() {
		//todo
	}
	
	public void getAssignmentSubmissions() {
		//todo
	}
	
	public void gradeSubmission() {
		//todo
	}
	
	public void activateAssignment() {
		//todo
	}
	
	public void deactivateAssignment() {
		//todo
	}	
}
