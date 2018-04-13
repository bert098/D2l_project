package View;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Class for the student's gui used for displaying a student's assignments.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class StudentAssignmentsPanel extends UserAssignmentsPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 7561850948923475805L;
	/** Button used to download an assignment. */
	private JButton downloadAssignButton = new JButton("Download");
	
	/**
	 * Adds an action listener for downloadAssignmentButton.
	 * @param a Action listener added to downloadAssignmentButton.
	 */
	public void addDownloadAssignButtonActionListener(ActionListener a) {downloadAssignButton.addActionListener(a);}
	/**
	 * Constructor for initializing a new StudentAssignmentsPanel.
	 */
	StudentAssignmentsPanel()
	{
		super("Submit Assignment");
		
		GridBagConstraints gbc_deactivateAssignButton = new GridBagConstraints();
		gbc_deactivateAssignButton.gridx = 4;
		gbc_deactivateAssignButton.gridy = 2;
		add(downloadAssignButton, gbc_deactivateAssignButton);
	}
}
