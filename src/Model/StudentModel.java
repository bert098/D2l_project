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
	

}
