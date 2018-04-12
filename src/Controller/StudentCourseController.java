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

import Data.Email;
import Data.Assignment;
import Data.Course;
import Data.Dropbox;
import Data.SubmissionFileContainer;
import Model.StudentModel;
import View.GradesPanel;
import View.StudentAssignmentsPanel;
import View.StudentCourseView;
import View.UserEmailPanel;

public class StudentCourseController {

	/**
	 * Id of selected course
	 */
	@SuppressWarnings("unused")
	private Integer courseId;
	
	/**
	 * Student model for interacting with server 
	 */
	private StudentModel studentModel;
	
	/**
	 * Course view for student
	 */
	private StudentCourseView courseView;
	
	/**
	 * Constructs controller with course view, model and the selected course id.
	 * Displays assignments and grades.
	 * @param courseView Course view for student 
	 * @param model Model for student 
	 * @param id Selected course id
	 */
	public StudentCourseController(StudentCourseView courseView, StudentModel model, Integer id)
	{
		courseId = id;
		studentModel = model;
		this.courseView = courseView;
		displayAssignments();
		displayGrades();
		addStudentCourseViewListeners();
		addGradesPanelListeners();
	}
	
	/**
	 * Display assignments by calling the student model
	 */
	public void displayAssignments()
	{
		Course c = courseView.getCourse();
		ArrayList<Assignment> a = studentModel.displayAssign(c);
		StudentAssignmentsPanel panel = courseView.getStudentAssignmentsPanel();
		panel.displayAssignments(a);
	}
	
	/**
	 * Display grades by calling the student model
	 */
	public void displayGrades()
	{
		Course c = courseView.getCourse();
		Integer id = courseView.getView().getUserId();
		ArrayList<Dropbox> g = studentModel.displayGrades(c,id);
		GradesPanel panel = courseView.getGradesPanel();
		panel.displaySubmissions(g);
	}
	
	/**
	 * Calls methods to add action listeners for buttons in course view
	 */
	public void addStudentCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addEmailPanelListeners();
	}
	
	/**
	 * Adds action listeners for assignment panel buttons
	 */
	private void addAssignmentPanelListeners()
	{
		StudentAssignmentsPanel panel = courseView.getStudentAssignmentsPanel();
		
		panel.addOpenDropboxButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(panel.getSelectedAssignment() != null) {
					JFileChooser fileBrowser = new JFileChooser();
					
					if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						File selectedFile = fileBrowser.getSelectedFile();
						
						long length = selectedFile.length();
						byte[] content = new byte[(int)length];
						
						try
						{
							FileInputStream fis = new FileInputStream(selectedFile);
							@SuppressWarnings("resource")
							BufferedInputStream bos = new BufferedInputStream(fis);
							bos.read(content, 0, (int)length);
						}
						catch (FileNotFoundException e)
						{
							e.printStackTrace();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						
						Dropbox submission = new Dropbox(null, panel.getSelectedAssignment().getId(), courseView.getView().getUserId(),
								null, -1, "", selectedFile.getName(), null);
						
						SubmissionFileContainer container = new SubmissionFileContainer(content, selectedFile.getName(), submission);
						
						studentModel.submitAssignment(container);
						JOptionPane.showMessageDialog(null, "Assignment submitted.");
					}
				}
			}
		});
		
		panel.addDownloadAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Assignment assign = panel.getSelectedAssignment();
				
				JFileChooser fileBrowser = new JFileChooser();
				fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File file = fileBrowser.getCurrentDirectory();
					File currentDir = fileBrowser.getSelectedFile();
					String filepath = file.getAbsolutePath() + "\\" + currentDir.getName();
					studentModel.downloadAssign(assign, filepath);
				}
				
			}
		});
	}
	
	/**
	 * Adds action listeners for email panel buttons
	 */
	private void addEmailPanelListeners()
	{
		UserEmailPanel panel = courseView.getUserEmailPanel();
		
		panel.addSendButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String message = panel.getMessage();
				String title = panel.getTitle();
				String password = panel.getEmailPassword(courseView.getEmail());
				
				Email email = new Email(courseView.getEmail(), courseView.getCourse(), title, message, password);
				
				boolean messageSent = studentModel.sendEmail(email);
				if(messageSent) {
					JOptionPane.showMessageDialog(null, "Email sent successfully.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Email sending error.");
				}
			}
		});
		
		panel.addClearButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Clear");
				panel.clear();
			}
		});
	}
	
	/**
	 * Adds actions listeners for grades panel buttons
	 */
	private void addGradesPanelListeners()
	{
		GradesPanel panel = courseView.getGradesPanel();
		
		panel.addAssignDetailsButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panel.getSubmission() == null)
				{
					return;
				}
				else
				{
				JOptionPane.showMessageDialog(null,panel.getSubmission().getComment() ,
						"Comments", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
	}
}
