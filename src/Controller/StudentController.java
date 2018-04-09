package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		addCloseWindowListener();
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
	
	private void addCloseWindowListener() {
		view.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   //todo
				   System.out.println("Exit");
				   System.exit(0);
			   }
		});
	}
	
	public static void main(String[] args)
	{
		StudentController controller = new StudentController(new StudentView(new Student(1, "BOB", "123", 'S', null, "BOB", "BOB")),
				new StudentModel());
	}
}
