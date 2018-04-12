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
import java.util.Random;

import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.FileContainer;

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void downloadAssign(Assignment assign, String filepath)
	{
		try {
			
			sendOperation(DOWNLOAD_ASSIGN);
			
			objectOut.writeObject(assign);
			objectOut.flush();
			
			
			AssignmentFileContainer container = (AssignmentFileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File(filepath + "\\" + container.getFileName());
			
			if(!newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}
