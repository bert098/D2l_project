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
import javax.swing.JPasswordField;

import Data.Constants;
import Data.Assignment;
import Data.Course;
import Data.Email;
import Data.FileContainer;
import Data.Student;
import Data.StudentEnrollment;

import Model.ProfessorModel;
import View.ProfessorAssignmentsPanel;
import View.ProfessorCourseView;
import View.ProfessorDropboxView;
import View.SearchStudentsPanel;
import View.UserEmailPanel;
import Data.Assignment;

public class ProfessorCourseController implements Constants {
	
	private Integer courseId;
	private ProfessorModel professorModel; 
	private ProfessorCourseView courseView;
	
	public ProfessorCourseController(ProfessorCourseView courseView, ProfessorModel model, Integer id)
	{
		courseId = id;
		professorModel = model;
		this.courseView = courseView;
		displayAssignments(); 
		addProfessorCourseViewListeners();
	}
	
	private void addProfessorCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addSearchEnrolledStudentsPanelListeners();
		addSearchAllStudentsPanelListeners();
		addEmailPanelListeners();
	}
	
	public void displayAssignments() { 
		professorModel.sendOperation(GET_ASSIGN);
		ArrayList<Assignment> assignmentList = professorModel.readAssignmentList(); 
		courseView.displayAssignments(assignmentList, courseId);
	}
	
	private void addAssignmentPanelListeners()
	{
		ProfessorAssignmentsPanel panel = courseView.getProfessorAssignmentsPanel();
		
		panel.addOpenDropboxButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Open Dropbox");
				courseView.setVisible(false);
				new ProfessorDropboxController(new ProfessorDropboxView(courseView));				
			}
		});
		
		panel.addUploadButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Upload");
				
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
					FileContainer container = new FileContainer(content, selectedFile.getName(), assign);
					
					professorModel.uploadAssignment(container);
					displayAssignments();
				}
				
				
			}
		});
		
		panel.addActivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Activate");
				professorModel.sendOperation(ACTIVATE_ASSIGN);
				Integer assignId = courseView.getProfessorAssignmentsPanel().getSelectedAssignment().getId();
				professorModel.sendDeactivateAssignment(assignId);
				System.out.println("odsifja");
				displayAssignments();
			}
		});
		
		panel.addDeactivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Deactivate");
				professorModel.sendOperation(DEACTIVATE_ASSIGN);
				Integer assignId = courseView.getProfessorAssignmentsPanel().getSelectedAssignment().getId();
				professorModel.sendDeactivateAssignment(assignId);
				displayAssignments();
			}
		});
	}
	
	private void addSearchEnrolledStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchEnrolledStudentsPanel();
		
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
		
		panel.addEnrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Course c = courseView.getCourse();
				Integer num = Integer.parseInt(JOptionPane.showInputDialog("Enter an Integer Number: "));
				StudentEnrollment st = new StudentEnrollment((int) Math.floor((Math.random() * 50) + 1), num, c.getId());
				ArrayList<Student> a = professorModel.enroll(st);
				panel.displayAll(a);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please enter a number.",
							"Error Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		panel.addUnenrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Student s = panel.getSelectedStudent();
				ArrayList<Student> a  = professorModel.unEnroll(s);
				panel.displayAll(a);
			}
		});
	}
	
	private void addSearchAllStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchAllStudentsPanel();
		
		panel.addSearchButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//todo
				System.out.println("search");
			}
		});
		
		panel.addEnrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//todo
				System.out.println("enroll");
			}
		});
		
		panel.addUnenrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//todo
				System.out.println("unenroll");
			}
		});
	}
	
	private void addEmailPanelListeners()
	{
		UserEmailPanel panel = courseView.getUserEmailPanel();
		
		panel.addSendButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub\
				System.out.println("Send");
				String message = panel.getMessage();
				String title = panel.getTitle();
				
				JPasswordField passwordField = new JPasswordField(20);
				
				//EmailPasswordPanel passwordPanel = new EmailPasswordPanel();
				JOptionPane.showConfirmDialog(null, passwordField,
						"Please enter password for: " + courseView.getEmail(), JOptionPane.PLAIN_MESSAGE);
				
				String password = new String(passwordField.getPassword());
				System.out.println(password);
				
				Email email = new Email(courseView.getEmail(), courseView.getCourse().getId(), title, message, password);
				
				boolean messageSent = professorModel.sendEmail(email);
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
	
}
