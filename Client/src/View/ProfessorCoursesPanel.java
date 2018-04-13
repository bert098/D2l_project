package View;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class used in the professor gui for displaying a professor's courses.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class ProfessorCoursesPanel extends UserCoursesPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 3962664310003782620L;
	
	/** Button for activating a course. */
	private JButton activateCourseButton = new JButton("Activate");
	/** Button for deactivating a course. */
	private JButton deactivateCourseButton = new JButton("Deactivate");
	
	/**
	 * Adds an action listener for activateCourseButton.
	 * @param a Action listener added to activateCourseButton.
	 */
	public void addActivateCourseButtonActionListener(ActionListener a) {activateCourseButton.addActionListener(a);}
	/**
	 * Adds an action listener for deactivateCourseButton.
	 * @param a Action listener added to deactivateCourseButton.
	 */
	public void addDeactivateCourseButtonActionListener(ActionListener a) {deactivateCourseButton.addActionListener(a);}
	
	/**
	 * Constructor for initializing a new ProfessorCoursesPanel.
	 */
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
