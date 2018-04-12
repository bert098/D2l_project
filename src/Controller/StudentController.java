package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Data.Course;
import Data.Constants;
import Data.Student;
import Model.StudentModel;
import View.StudentCourseView;
import View.StudentCoursesPanel;
import View.StudentView;

public class StudentController implements Constants{
	private StudentModel studentModel;
	private StudentView view;
	private ArrayList<Course> courseList;
	
	public StudentController(StudentView view, StudentModel model)
	{
		studentModel = model;
		this.view = view;
		addCourses();
		addStudentViewListeners();
		addCloseWindowListener();
	}
	
	public void addCourses()
	{
		studentModel.sendOperation(STUDENT_COURSES);
		studentModel.sendStudentId(view.getUserId());
		courseList = studentModel.getStudentCourseList();
		view.displayCourses(courseList);
	}
	
	
	public void addStudentViewListeners() 
	{
		StudentCoursesPanel panel = view.getStudentCoursesPanel();
		
		panel.addOpenCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Open Course");
				if(panel.getSelectedCourse() != null) {
					view.setVisible(false);
					StudentCourseController s = new StudentCourseController(new StudentCourseView(panel.getSelectedCourse(), view),
							studentModel, panel.getSelectedCourse().getId());					
				}
				
			}
		});
	}
	
	private void addCloseWindowListener() {
		view.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   System.out.println("Exit");
				   studentModel.sendOperation(EXIT);
				   System.exit(0);
			   }
		});
	}
}
