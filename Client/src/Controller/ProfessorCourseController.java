package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Data.Constants;
import Data.Assignment;
import Data.AssignmentFileContainer;
import Data.Course;
import Data.Email;
import Data.Student;
import Data.StudentEnrollment;

import Model.ProfessorModel;
import View.ProfessorAssignmentsPanel;
import View.ProfessorCourseView;
import View.ProfessorDropboxView;
import View.SearchStudentsPanel;
import View.UserEmailPanel;

/**
 * Class that connects ProfessorCourseView and ProfessorModel using action listeners.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 *
 */
public class ProfessorCourseController implements Constants {
	
	/** The course id of courseView. */
	private Integer courseId;
	/** Model used for communicating with the server. */
	private ProfessorModel professorModel; 
	/** ProfessorCourseView gui. */
	private ProfessorCourseView courseView;
	
	/**
	 * Constructor that initializes a new ProfessorCourseController.
	 * @param courseView Assigned to courseView.
	 * @param model Assigned to professorModel.
	 * @param id Assigned to courseId.
	 */
	public ProfessorCourseController(ProfessorCourseView courseView, ProfessorModel model, Integer id)
	{
		courseId = id;
		professorModel = model;
		this.courseView = courseView;
		displayAssignments(); 
		addProfessorCourseViewListeners();
	}
	
	/** Adds all of the action listeners for courseView. */
	private void addProfessorCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addSearchEnrolledStudentsPanelListeners();
		addSearchAllStudentsPanelListeners();
		addEmailPanelListeners();
	}
	
	/** Updates the assignments displayed in courseView. */
	public void displayAssignments() { 
		professorModel.sendOperation(GET_ASSIGN);
		ArrayList<Assignment> assignmentList = professorModel.readAssignmentList(); 
		courseView.displayAssignments(assignmentList, courseId);
	}
	
	/** Adds the action listeners for courseView's ProfessorAssignmentsPanel panel using anonymous classes. */
	private void addAssignmentPanelListeners()
	{
		ProfessorAssignmentsPanel panel = courseView.getProfessorAssignmentsPanel();
		
		/**
		 * Anonymous class for open dropbox button. If the selected assignment in courseView is not null a new ProfessorDropboxController
		 * and ProfessorDropboxView using the selected assignment. Does nothing otherwise.
		 */
		panel.addOpenDropboxButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Assignment assignment = panel.getSelectedAssignment();
				if (assignment == null) {
					return;
				}
				courseView.setVisible(false);
				new ProfessorDropboxController(new ProfessorDropboxView(assignment, courseView), professorModel, assignment);				
			}
		});
		
		/**
		 * Anonymous class for upload assignment button. Opens a file chooser to select a file and creates a new assignment and AssignmentFileContainer
		 * and calls uploadAssignment from professorModel. Then updates the assignments displayed in courseView.
		 */
		panel.addUploadButtonActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileBrowser = new JFileChooser();
				
				if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileBrowser.getSelectedFile();
					
					long length = selectedFile.length();
					byte[] content = new byte[(int)length];
					
					try
					{
						FileInputStream fis = new FileInputStream(selectedFile);
						BufferedInputStream bos = new BufferedInputStream(fis);
						bos.read(content, 0, (int)length);
						bos.close();
					}
					catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					String dueDate = (String)JOptionPane.showInputDialog("Enter the due date:");
					
					Assignment assign = new Assignment(null, courseView.getCourse(), selectedFile.getName(),
							null, false, dueDate);
					AssignmentFileContainer container = new AssignmentFileContainer(content, selectedFile.getName(), assign);
					
					professorModel.uploadAssignment(container);
					displayAssignments();
				}
				
				
			}
		});
		
		/**
		 * Anonymous class for activate assignment button. If an assignment is selected calls sendUpdateAssignStatus() with the ACTIVATE_ASSIGN
		 * operation from professorModel. Then updates the assignments displayed in courseView.
		 */
		panel.addActivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Assignment assignment = courseView.getProfessorAssignmentsPanel().getSelectedAssignment();
				if (assignment == null) {
					return;
				}
				professorModel.sendOperation(ACTIVATE_ASSIGN);
				professorModel.sendUpdateAssignStatus(assignment.getId());
				displayAssignments();
			}
		});
		
		/**
		 * Anonymous class for deactivate assignment button. If an assignment is selected calls sendUpdateAssignStatus() with the DEACTIVATE_ASSIGN
		 * operation from professorModel. Then updates the assignments displayed in courseView.
		 */
		panel.addDeactivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Assignment assignment = courseView.getProfessorAssignmentsPanel().getSelectedAssignment();
				if (assignment == null) {
					return;
				}
				professorModel.sendOperation(DEACTIVATE_ASSIGN);
				professorModel.sendUpdateAssignStatus(assignment.getId());
				displayAssignments();
			}
		});
	}
	
	/** Adds the action listeners for courseView's SearchStudentsPanel panel (enrolled) using anonymous classes. */
	private void addSearchEnrolledStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchEnrolledStudentsPanel();
		
		/**
		 * Anonymous class for search button. Calls searchStudent() in professorModel based on user input in courseView and panel.
		 * Updates courseView with the results from the method call.
		 */
		panel.addSearchButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = panel.getSearchText();
				if(panel.idSelected()) 
				{
				Course c = courseView.getCourse();
				ArrayList<Student> a = professorModel.searchStudent(c);
				panel.displayStudentsId(a,s);
				}
				else if(panel.lastNameSelected())
				{
					Course c = courseView.getCourse();
					ArrayList<Student> a = professorModel.searchStudent(c);
					panel.displayStudentsName(a,s);
				}

			}
		});
		
		/**
		 * Anonymous class for the unenroll button. If a student is selected in panel calls unEnroll() and searchAll() from professorModel based
		 * on user input in panel. Updates the search panels in courseView from the results of the method calls.
		 */
		panel.addUnenrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Course c = courseView.getCourse();
				Student s = panel.getSelectedStudent();
				if(s != null)
				{
					ArrayList<Student> a  = professorModel.unEnroll(s);
					ArrayList<Student> ar = professorModel.searchAll(c);
					courseView.getSearchAllStudentsPanel().displayAll(ar);
					panel.displayAll(a);
					courseView.selectSearchStudentAll();
				}
				
			}
		});
	}
	
	/** Adds the action listeners for courseView's SearchStudentsPanel panel (unenrolled) using anonymous classes. */
	private void addSearchAllStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchAllStudentsPanel();
		
		/**
		 * Anonymous class for search button. Calls searchStudent() in professorModel based on user input in courseView and panel.
		 * Updates courseView with the results from the method call.
		 */
		panel.addSearchButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = panel.getSearchText();
				if(panel.idSelected()) 
				{
				Course c = courseView.getCourse();
				ArrayList<Student> a = professorModel.searchAll(c);
				panel.displayStudentsId(a,s);
				}
				else if(panel.lastNameSelected())
				{
					Course c = courseView.getCourse();
					ArrayList<Student> a = professorModel.searchAll(c);
					panel.displayStudentsName(a,s);
				}
			}
		});
		
		/**
		 * Anonymous class for the enroll button. If a student is selected in panel calls Enroll() and searchAll() from professorModel based
		 * on user input in panel. Updates the search panels in courseView from the results of the method calls.
		 */
		panel.addEnrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Course c = courseView.getCourse();
				Student s = panel.getSelectedStudent();
				if(s != null)
				{
					StudentEnrollment st = new StudentEnrollment((int) Math.floor((Math.random() * 50) + 1), s.getId(), c.getId());
					ArrayList<Student> ar =	professorModel.enroll(st);
					ArrayList<Student> a = professorModel.searchAll(c);
					courseView.getSearchEnrolledStudentsPanel().displayAll(ar);
					courseView.getSearchAllStudentsPanel().displayAll(a);
					courseView.selectSearchStudentEnrolled();
				}
			}
		});
	}
	
	/** Adds the action listeners for courseView's UserEmailPanel panel using anonymous classes. */
	private void addEmailPanelListeners()
	{
		UserEmailPanel panel = courseView.getUserEmailPanel();
		
		/**
		 * Anonymous class for the send button. Creates a new Email from the information in panel and calls sendEmail() from professorModel.
		 * Displays a dialogue displaying if the email was sent successfully from the result of the method call.
		 */
		panel.addSendButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message = panel.getMessage();
				String title = panel.getTitle();
				String password = panel.getEmailPassword(courseView.getEmail());
				
				Email email = new Email(courseView.getEmail(), courseView.getCourse(), title, message, password);
				
				boolean messageSent = professorModel.sendEmail(email);
				if(messageSent) {
					JOptionPane.showMessageDialog(null, "Email sent successfully.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Email sending error.");
				}
			}
		});
		
		/**
		 * Anonymous class for the clear button. Calls clear() from panel.
		 */
		panel.addClearButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.clear();
			}
		});
	}
	
}
