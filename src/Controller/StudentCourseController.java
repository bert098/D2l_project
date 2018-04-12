package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Data.Email;
import Data.Assignment;
import Data.Course;
import Data.Dropbox;
import Data.Grade;
import Model.StudentModel;
import View.GradesPanel;
import View.ProfessorDropboxView;
import View.StudentAssignmentsPanel;
import View.StudentCourseView;
import View.UserEmailPanel;

public class StudentCourseController {

	private Integer courseId;
	private StudentModel studentModel;
	private StudentCourseView courseView;
	
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
	
	public void displayAssignments()
	{
		Course c = courseView.getCourse();
		ArrayList<Assignment> a = studentModel.displayAssign(c);

		
		
		
		StudentAssignmentsPanel panel = courseView.getStudentAssignmentsPanel();
		panel.displayAssignments(a);
		
	}
	public void displayGrades()
	{
		Course c = courseView.getCourse();
		Integer id = courseView.getView().getUserId();
		ArrayList<Dropbox> g = studentModel.displayGrades(c,id);
		GradesPanel panel = courseView.getGradesPanel();
		panel.displaySubmissions(g);
	}
	
	public void addStudentCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addEmailPanelListeners();
	}
	
	private void addAssignmentPanelListeners()
	{
		StudentAssignmentsPanel panel = courseView.getStudentAssignmentsPanel();
		
		panel.addOpenDropboxButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Submit assignment");		
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
	
	private void addGradesPanelListeners()
	{
		GradesPanel panel = courseView.getGradesPanel();
		
		panel.addAssignDetailsButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Assign deets");
			}
		});
	}
}
