package View;

import Data.Course;

public class StudentCourseView extends UserCourseView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6321878176685601124L;
	
	private StudentAssignmentsPanel assignmentsPanel = new StudentAssignmentsPanel();
	private GradesPanel gradesPanel = new GradesPanel();
	
	public StudentAssignmentsPanel getStudentAssignmentsPanel() {return assignmentsPanel;}
	public GradesPanel getGradesPanel() {return gradesPanel;}
	
	public StudentCourseView(Course course, StudentView userView) {
		
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Professor");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Grades", null, gradesPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		
	}
}
