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
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	CreateCourseView createCourseView = new CreateCourseView();
	ProfessorCoursesView professorCoursesView = new ProfessorCoursesView();
	
	public ProfessorView() {
		
		frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel mainLabel = new JLabel("D4L");
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel.add(mainLabel);
		
		JLabel professorLabel = new JLabel("Professor Label");
		panel.add(professorLabel);
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("View Courses", null, professorCoursesView, null);
		tabbedPane.addTab("Create New Course", null, createCourseView, null);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ProfessorView professorView = new ProfessorView();
	}
}
