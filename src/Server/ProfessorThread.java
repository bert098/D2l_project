package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Database.DatabaseHelper;
import Data.Constants;
import Data.Course;
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
	//this
	public  void enrollStudent() {
		
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
				ArrayList a = database.searchStudentEnrollmentByStudent(st.getCourseId());
				objectOut.flush();
				objectOut.writeObject(a);
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
	//this
	public void unenrollStudent() {
		try {
			Student student = (Student)objectIn.readObject();
			int n = database.UnEnroll(student.getId());
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
