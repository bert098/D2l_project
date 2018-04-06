package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Data.Assignment;
import Data.Constants;
import Data.Course;
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;

public class ProfessorModel implements Constants{
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 
	private ObjectInputStream objectIn; 
	private ObjectOutputStream objectOut; 
	
	public ProfessorModel(BufferedReader in, PrintWriter out, ObjectInputStream oIn, ObjectOutputStream oOut) {
		stringIn = in; 
		stringOut = out; 
		objectIn = oIn; 
		objectOut = oOut; 
	}
	
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
	
	public void sendDeactivateAssignment(Integer assignId) { 
		stringOut.flush();
		stringOut.println(assignId.toString());
	}
	
	public void createCourse(Course course)
	{
		try
		{
			sendOperation(CREATE_COURSE);
			objectOut.flush();
			objectOut.writeObject(course);
			//System.out.println(course.toString());
			
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
  
	public ArrayList<Student> SearchStudentName(Course course)
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
	
	public ArrayList<Student> SearchStudent(Course course)
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
	
	public  ArrayList<Student> enroll(StudentEnrollment st)
	{
		ArrayList<Student> s = null;
		try {
			sendOperation(ENROLL_STUDENT);
			objectOut.flush();
			objectOut.writeObject(st);
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
	
	public void uploadAssignment(FileContainer container)
	{
		try{
			sendOperation(UPLOAD_ASSIGN);
			objectOut.writeObject(container);
			objectOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
