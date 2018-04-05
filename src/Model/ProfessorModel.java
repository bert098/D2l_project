package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Data.Constants;
import Data.Course;

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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(operation);
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
  
	public void SearchStudent(Course course)
	{
		try
		{
			sendOperation(SEARCH_STUDENT_ID);
			objectOut.flush();
			objectOut.writeObject(course);
			
			//System.out.println(course.toString());
			
		} 

		catch(IOException e) {
		}
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
	
	public void uploadAssignment(byte[] byteArr)
	{
		try{
			sendOperation(UPLOAD_ASSIGN);
			objectOut.writeObject(byteArr);
			objectOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
