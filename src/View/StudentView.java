package View;

import java.util.ArrayList;

import Data.Course;
import Data.Student;

public class StudentView extends UserView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5373923137481277482L;
	
	private StudentCoursesPanel studentCoursesPanel = new StudentCoursesPanel();
	
	public StudentCoursesPanel getStudentCoursesPanel() {return studentCoursesPanel;}
	
	public StudentView(Student student) {
		
		super(student);
		
		tabbedPane.addTab("View Courses", null, studentCoursesPanel, null);
		
	}
	
	
	public void displayCourses(ArrayList<Course> courseList) {
		studentCoursesPanel.displayCourses(courseList);
	}
}
