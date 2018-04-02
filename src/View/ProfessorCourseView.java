package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class ProfessorCourseView extends JFrame{

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
		
		ProfessorAssignmentsPanel professorAssignmentsPanel = new ProfessorAssignmentsPanel();
		tabbedPane.addTab("Assignments", null, professorAssignmentsPanel, null);
		
		SearchStudentsPanel studentSearchPanel = new SearchStudentsPanel();
		tabbedPane.addTab("Search Students", null, studentSearchPanel, null);
		
		ProfessorEmailPanel professorEmailView = new ProfessorEmailPanel();
		tabbedPane.addTab("Send Email", null, professorEmailView, null);
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		ProfessorCourseView test = new ProfessorCourseView();
	}
}
