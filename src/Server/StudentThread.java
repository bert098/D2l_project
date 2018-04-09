package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Data.Constants;
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
	}
	
	public void getStudentCourses() {
		//todo 
	}
	
	public void getStudentAssignments() { 
		//todo
	}
	
	public void downloadAssignment() { 
		//todo
	}
	
	public void submitAssignment() { 
		//todo
	}
	
	public void getGrades() { 
		//todo
	}
	
	public void sendEmail() {
		//todo
	}

}
