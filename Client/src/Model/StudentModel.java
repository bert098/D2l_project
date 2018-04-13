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

import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Constants;
import Data.Course;
import Data.Email;
import Data.Dropbox;
import Data.StudentEnrollment;
import Data.SubmissionFileContainer;

/**
 * Student model class provides methods to communicate with the 
 * server and receive data from the database. 
 * @author Justin, Robert, Magnus
 */
public class StudentModel implements Constants{
	
	/**
	 * Read string input from server
	 */
	private BufferedReader stringIn; 
	
	/**
	 * Write string output to server
	 */
	private PrintWriter stringOut; 
	
	/**
	 * Write object to server 
	 */
	private ObjectInputStream objectIn; 
	
	/**
	 * Read object from server 
	 */
	private ObjectOutputStream objectOut; 
	
	/**
	 * Construct student model with input and output streams 
	 * for string and object
	 * @param in String in
	 * @param out String out 
	 * @param oIn Object in
	 * @param oOut Object out
	 */
	public StudentModel (BufferedReader in, PrintWriter out, ObjectInputStream oIn, ObjectOutputStream oOut) {
		stringIn = in; 
		stringOut = out; 
		objectIn = oIn; 
		objectOut = oOut; 
	}
	
	/**
	 * Send selected course to server and 
	 * read arrayList of assignments from server and return 
	 * @param c Course being sent 
	 * @return ArrayList of assignments 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Assignment> displayAssign(Course c)
	{
		ArrayList<Assignment> a = null;
		try {
			sendOperation(GET_ASSIGN);
			objectOut.flush();
			objectOut.writeObject(c);
		 a = (ArrayList<Assignment>)objectIn.readObject();
		 System.out.println("Got array of assignments on model");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return a;
		
	}
	
	/**
	 * Send Selected course and selected student id and read ArrayList 
	 * of submissions from server 
	 * @param c Selected course being sent 
	 * @param n Student id being sent 
	 * @return ArrayList of submissions
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Dropbox> displayGrades(Course c, Integer n)
	{
		ArrayList<Dropbox> g = null;
		sendOperation(GET_GRADES);
		try {
			StudentEnrollment temp = new StudentEnrollment(100, n, c.getId());
			objectOut.flush();
			objectOut.writeObject(temp);
			g = (ArrayList<Dropbox>)objectIn.readObject();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return g;
	}
	
	/**
	 * Send operation to server
	 * @param operation operation being sent
	 */
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
	
	/**
	 * Sent student id to server 
	 * @param id student id
	 */
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
	 
	/**
	 * Get student course list from server
	 * @return array list of courses from server
	 */
	@SuppressWarnings("unchecked")
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
	
	/**
	 * Send email to server and read message status
	 * @param email Email being sent
	 * @return true if email sent, false if not
	 */
	public boolean sendEmail(Email email)
	{
		try{
			sendOperation(SEND_EMAIL);
			objectOut.flush();
			objectOut.writeObject(email);
			
			String messageStatus = stringIn.readLine();
			if(messageStatus.equals("MESSAGE_SENT")) {
				return true;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Download assignment file from server by sending the selected 
	 * assignment and reading the file bytes from the server. The 
	 * assignment is then saved into the specified file path.
	 * @param assign Selected assignment 
	 * @param filepath File path being saved into
	 */
	public void downloadAssign(Assignment assign, String filepath)
	{
		try {
			sendOperation(DOWNLOAD_ASSIGN);
			
			objectOut.flush();
			objectOut.writeObject(assign);	
			
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
	
	/**
	 * Uploads assignment to server by sending 
	 * a submission file container.
	 * @param container SubmissionFileContainer holding the file
	 */
	public void submitAssignment(SubmissionFileContainer container)
	{
		try {
			sendOperation(SUBMIT_ASSIGN);
			objectOut.writeObject(container);
			objectOut.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
