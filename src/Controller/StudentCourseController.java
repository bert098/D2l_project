package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
