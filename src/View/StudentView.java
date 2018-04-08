package View;

import java.util.ArrayList;

import Data.Course;
import Data.Student;

public class StudentView extends UserView{
	
	private StudentCoursesPanel studentCoursesPanel = new StudentCoursesPanel();
	
	public StudentCoursesPanel getStudentCoursesPanel() {return studentCoursesPanel;}
	
	public StudentView(Student student) {
		
		super(student);
		
		tabbedPane.addTab("View Courses", null, studentCoursesPanel, null);
		
	}
	
	
	public void displayCourses(ArrayList<Course> courseList) {
		studentCoursesPanel.displayCourses(courseList);
	}
	
	public static void main(String[] args) {
		StudentView sView = new StudentView(new Student(1, "BOB", "123", 'S', null, "BOB", "BOB"));
		
	}
}
