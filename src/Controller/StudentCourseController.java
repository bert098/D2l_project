package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Data.Email;
import Data.Assignment;
import Data.Course;

import Model.StudentModel;
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
		addStudentCourseViewListeners();
	}
	
	public void displayAssignments()
	{
		Course c = courseView.getCourse();
		ArrayList<Assignment> a = studentModel.displayAssign(c);

		StudentAssignmentsPanel panel = courseView.getStudentAssignmentsPanel();
		panel.displayAssignments(a);
		
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
				// TODO Auto-generated method stub
				System.out.println("Download");				
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
}
