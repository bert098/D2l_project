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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Data.Constants;
import Data.Assignment;
import Data.Course;
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
	private SearchStudentsPanel searchStudent;
	
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
		addSearchStudentsPanelListeners();
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
	
	private void addSearchStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchStudentsPanel();
		
		panel.addSearchButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = panel.getSearchText();
				if(panel.idSelected()) 
				{
				Course c = courseView.getCourse();
				ArrayList<Student> a = professorModel.SearchStudent(c);
				panel.displayStudentsId(a,s);
				}
				else if(panel.lastNameSelected())
				{
					Course c = courseView.getCourse();
					ArrayList<Student> a = professorModel.SearchStudent(c);
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
	
	private void addEmailPanelListeners()
	{
		UserEmailPanel panel = courseView.getUserEmailPanel();
		
		panel.addSendButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Send");
			}
		});
		
		panel.addClearButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Clear");
			}
		});
	}
	
}
