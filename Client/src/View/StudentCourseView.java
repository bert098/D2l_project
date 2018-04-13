package View;

import Data.Course;

/**
 * Class used in the student's gui for displaying information about a student's course.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class StudentCourseView extends UserCourseView{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -6321878176685601124L;
	
	/** Panel for displaying a student's assignments */
	private StudentAssignmentsPanel assignmentsPanel = new StudentAssignmentsPanel();
	/** Panel for displaying a student's grades. */
	private GradesPanel gradesPanel = new GradesPanel();
	
	/** @return assignmentsPanel */
	public StudentAssignmentsPanel getStudentAssignmentsPanel() {return assignmentsPanel;}
	/** @return gradesPanel */
	public GradesPanel getGradesPanel() {return gradesPanel;}
	
	/**
	 * Constructor for initializing a new StudentCourseView.
	 * @param course The course for this view.
	 * @param userView The StudentView used to open this view.
	 */
	public StudentCourseView(Course course, StudentView userView) {
		
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Professor");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Grades", null, gradesPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		
	}
}
