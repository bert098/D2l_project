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
import Data.Dropbox;
import Data.Grade;
import Data.StudentEnrollment;

public class StudentModel implements Constants{
	
	private BufferedReader stringIn; 
	private PrintWriter stringOut; 
	private ObjectInputStream objectIn; 
	private ObjectOutputStream objectOut; 
	
	public StudentModel (BufferedReader in, PrintWriter out, ObjectInputStream oIn, ObjectOutputStream oOut) {
		stringIn = in; 
		stringOut = out; 
		objectIn = oIn; 
		objectOut = oOut; 
	}
	public ArrayList<Assignment> displayAssign(Course c)
	{
		ArrayList<Assignment> a = null;
		try {
			stringOut.println(GET_ASSIGN);
			objectOut.flush();
			objectOut.writeObject(c);
		 a = (ArrayList<Assignment>)objectIn.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	public ArrayList<Dropbox> displayGrades(Course c, Integer n)
	{
		ArrayList<Dropbox> g = null;
		stringOut.flush();
		stringOut.println(GET_GRADES);
		try {
			StudentEnrollment temp = new StudentEnrollment(100, n, c.getId());
			objectOut.flush();
			objectOut.writeObject(temp);
			g = (ArrayList<Dropbox>)objectIn.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
		
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
	
	public void sendStudentId(Integer id) {
		stringOut.flush(); 
		stringOut.println(id.toString());
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 
	public ArrayList<Course> getStudentCourseList() {
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

}
