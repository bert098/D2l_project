package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class ProfessorCourseView extends JFrame{
	
	ProfessorAssignmentsPanel professorAssignmentsPanel = new ProfessorAssignmentsPanel();
	SearchStudentsPanel studentSearchPanel = new SearchStudentsPanel();
	ProfessorEmailPanel professorEmailView = new ProfessorEmailPanel();
	
	public ProfessorAssignmentsPanel getProfessorAssignmentsPanel() {return professorAssignmentsPanel;}
	public SearchStudentsPanel getSearchStudentsPanel() {return studentSearchPanel;}
	public ProfessorEmailPanel getProfessorEmailPanel() {return professorEmailView;}

	public ProfessorCourseView()
	{
		
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel courseLabel = new JLabel("courseName");
		courseLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		getContentPane().add(courseLabel, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Assignments", null, professorAssignmentsPanel, null);
		tabbedPane.addTab("Search Students", null, studentSearchPanel, null);
		tabbedPane.addTab("Send Email", null, professorEmailView, null);
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		ProfessorCourseView test = new ProfessorCourseView();
	}
}
