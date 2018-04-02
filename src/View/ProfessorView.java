package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProfessorView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498875227121382873L;
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	CreateCourseView createCourseView = new CreateCourseView();
	ProfessorCoursesPanel professorCoursesView = new ProfessorCoursesPanel();
	
	public ProfessorView()
	{
		
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel mainLabel = new JLabel("D4L");
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel.add(mainLabel);
		
		JLabel professorLabel = new JLabel("Professor Label");
		panel.add(professorLabel);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("View Courses", null, professorCoursesView, null);
		tabbedPane.addTab("Create New Course", null, createCourseView, null);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ProfessorView professorView = new ProfessorView();
	}
}
