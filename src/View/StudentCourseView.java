package View;

import Data.Course;
import Data.Student;

public class StudentCourseView extends UserCourseView{
	
	private StudentAssignmentsPanel assignmentsPanel = new StudentAssignmentsPanel();
	private GradesPanel gradesPanel = new GradesPanel();
	
	public StudentAssignmentsPanel getStudentAssignmentsPanel() {return assignmentsPanel;}
	
	public StudentCourseView(Course course, StudentView userView) {
		
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Professor");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Grades", null, gradesPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		
	}
	
	public static void main(String[] args) {
		
		StudentCourseView view = new StudentCourseView(new Course( 1, 1,  "name", true),
				new StudentView(new Student(1, "BOB", "123", 'S', null, "BOB", "BOB")));
	}
	
}
