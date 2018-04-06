package Server;

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

import Database.DatabaseHelper;
import Data.Assignment;
import Data.Constants;
import Data.Course;
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;

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
		else if (operation.equals(GET_ASSIGN)) {
			getAssignments(); 
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
		else if (operation.equals(UPLOAD_ASSIGN)) {
			uploadAssign();
		}
		else {
			System.out.println(operation + " is incorrect");
		}
	}
	
	public void getProfessorCourses() {
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
		//todo
	}
	//this
	public void enrollStudent() {
		//todo
	}
	//this
	public void unenrollStudent() {
		//todo
	}
	
	public void getAssignments() {
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		assignmentList = database.assignmentTableToList();
		try {
			objectOut.flush();
			objectOut.writeObject(assignmentList); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gradeSubmission() {
		//todo
	}
	
	public void activateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.activateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivateAssignment() {
		try {
			int assignId = Integer.parseInt(stringIn.readLine());
			database.deactivateAssignment(assignId);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void uploadAssign() {
		try {
			FileContainer container = (FileContainer)objectIn.readObject();
			byte[] content = container.getFileArr();
			
			File newFile = new File("assignments/" + container.getFileName());
			
			if(!newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			
			
			Assignment assign = container.getAssignment();
			assign.setPath("assignments/" + container.getFileName());
			
			Random rand = new Random();
			assign.setId(rand.nextInt(9999));
			database.addAssignment(container.getAssignment());
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
}
