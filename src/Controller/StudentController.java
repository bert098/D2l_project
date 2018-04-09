package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Data.Course;
import Data.Student;
import Model.StudentModel;
import View.StudentCourseView;
import View.StudentCoursesPanel;
import View.StudentView;

public class StudentController {
	private StudentModel studentModel;
	private StudentView view;
	private ArrayList<Course> courseList;
	
	public StudentController(StudentView view, StudentModel model)
	{
		studentModel = model;
		this.view = view;
		addCourses();
		addStudentViewListeners();
	}
	
	public void addCourses()
	{
		//todo
		//for testing
		ArrayList<Course> list = new ArrayList<Course>();
		list.add(new Course( 1, 1,  "name", true));
		
		view.displayCourses(list);
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
					new StudentCourseController(new StudentCourseView(panel.getSelectedCourse(), view),
							studentModel, panel.getSelectedCourse().getId());
				}
				
			}
		});
	}
}
