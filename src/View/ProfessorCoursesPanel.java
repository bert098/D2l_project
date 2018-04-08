package View;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ProfessorCoursesPanel extends UserCoursesPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962664310003782620L;
	
	private JButton activateCourseButton = new JButton("Activate");
	private JButton deactivateCourseButton = new JButton("Deactivate");
	
	public void addActivateCourseButtonActionListener(ActionListener a) {activateCourseButton.addActionListener(a);}
	public void addDeactivateCourseButtonActionListener(ActionListener a) {deactivateCourseButton.addActionListener(a);}
	
	public ProfessorCoursesPanel()
	{
		super();
		
		GridBagConstraints gbc_activateCourseButton = new GridBagConstraints();
		gbc_activateCourseButton.anchor = GridBagConstraints.EAST;
		gbc_activateCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_activateCourseButton.gridx = 1;
		gbc_activateCourseButton.gridy = 2;
		add(activateCourseButton, gbc_activateCourseButton);
		
		GridBagConstraints gbc_deactivateCourseButton = new GridBagConstraints();
		gbc_deactivateCourseButton.anchor = GridBagConstraints.WEST;
		gbc_deactivateCourseButton.gridx = 2;
		gbc_deactivateCourseButton.gridy = 2;
		add(deactivateCourseButton, gbc_deactivateCourseButton);
	}
}
