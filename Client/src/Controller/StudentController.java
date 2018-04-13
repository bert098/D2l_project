package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Data.Course;
import Data.Constants;
import Model.StudentModel;
import View.StudentCourseView;
import View.StudentCoursesPanel;
import View.StudentView;

/**
 * Student controller controls the interactions between the student view and the student model.
 * Provides anonymous classes for buttons in the student view.
 * @author Justin, Robert, Magnus
 */
public class StudentController implements Constants{
	
	/**
	 * Student model for handling interactions between client and server 
	 */
	private StudentModel studentModel;
	
	/**
	 * The view for the student
	 */
	private StudentView view;
	
	/**
	 * Constructs the student controller with the student view and student model.
	 * Displays courses from the database by calling addCourses().
	 * @param view Student view
	 * @param model Student model
	 */
	public StudentController(StudentView view, StudentModel model)
	{
		studentModel = model;
		this.view = view;
		addCourses();
		addStudentViewListeners();
		addCloseWindowListener();
	}
	
	/**
	 * gets the course list from the database though the student model
	 */
	public void addCourses()
	{
		studentModel.sendOperation(STUDENT_COURSES);
		studentModel.sendStudentId(view.getUserId());
		ArrayList<Course> courseList = studentModel.getStudentCourseList();
		view.displayCourses(courseList);
	}
	
	/**
	 * Adds action listeners for the open course button
	 */
	public void addStudentViewListeners() 
	{
		StudentCoursesPanel panel = view.getStudentCoursesPanel();
		
		panel.addOpenCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panel.getSelectedCourse() != null) {
					view.setVisible(false);
					@SuppressWarnings("unused")
					StudentCourseController s = new StudentCourseController(new StudentCourseView(panel.getSelectedCourse(), view),
							studentModel, panel.getSelectedCourse().getId());					
				}
				
			}
		});
	}
	
	/**
	 * Adds action listeners for closing the window
	 */
	private void addCloseWindowListener() {
		view.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   studentModel.sendOperation(EXIT);
				   System.exit(0);
			   }
		});
	}
}
