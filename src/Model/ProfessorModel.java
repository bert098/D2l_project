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
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;
import Data.SubmissionFileContainer;

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
	
	public void sendUpdateAssignStatus(Integer assignId) { 
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
  
	public ArrayList<Student> searchStudentName(Course course)
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
	
	public ArrayList<Student> searchStudent(Course course)
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
	public ArrayList<Student> searchAll(Course c)
	{
		ArrayList<Student> s = null;
		try
		{
			sendOperation(ALL_STUDENTS);
			objectOut.flush();
			objectOut.writeObject(c);
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
	
	public ArrayList<Student> enroll(StudentEnrollment st)
	{
		ArrayList<Student> s = null;
		try {
			sendOperation(ENROLL_STUDENT);
			objectOut.flush();
			objectOut.writeObject(st);
			s = (ArrayList<Student>)objectIn.readObject();
			
			if (s == null) {
				return null;
			}
			for (int i = 0; i < s.size(); i++) {
				System.out.println(s.get(i).toString());
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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
	
	public void uploadAssignment(AssignmentFileContainer container)
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
	
	public void sendAssignmentId(Integer assignId) {
		stringOut.flush();
		stringOut.println(assignId.toString());
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dropbox> readSubmissionList() {
		try {
			return (ArrayList<Dropbox>) objectIn.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendComments(String comments) {
		stringOut.flush();
		stringOut.println(comments);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendGrade(String grade) {
		stringOut.flush();
		stringOut.println(grade);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendSubmissionId(String id) {
		stringOut.flush();
		stringOut.println(id);
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadSubmission(Dropbox submission, String filePath) {
		try { 
			sendOperation(DOWNLOAD_SUB);
			
			objectOut.flush();
			objectOut.writeObject(submission);
			
			SubmissionFileContainer container = (SubmissionFileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File(filePath + "\\" + container.getFileName());
			
			if (!newFile.exists()) 
			{
				newFile.createNewFile();
			}
			
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bufferOutput = new BufferedOutputStream(writer);
			bufferOutput.write(content);
			bufferOutput.close();
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}
