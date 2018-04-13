package View;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import Data.Assignment;

/**
 * Class used in the professor gui for displaying a course's assignments.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class ProfessorAssignmentsPanel extends UserAssignmentsPanel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -158630035274134969L;
	
	/** Button for uploading a new assignment. */
	private JButton uploadAssignmentButton = new JButton("Upload New Assignment");
	/** Button for activating an assignment. */
	private JButton activateAssignButton = new JButton("Activate");
	/** Button for deactivating an assignment. */
	private JButton deactivateAssignButton = new JButton("Deactivate");
	
	/**
	 * Adds an action listener for uploadAssignmentButton.
	 * @param a Action listener added to uploadAssignmentButton.
	 */
	public void addUploadButtonActionListener(ActionListener a) {uploadAssignmentButton.addActionListener(a);}
	/**
	 * Adds an action listener for activateAssignButton.
	 * @param a Action listener added to activateAssignButton.
	 */
	public void addActivateAssignButtonActionListener(ActionListener a) {activateAssignButton.addActionListener(a);}
	/**
	 * Adds an action listener for deactivateAssignButtonv.
	 * @param a Action listener added to deactivateAssignButton.
	 */
	public void addDeactivateAssignButtonActionListener(ActionListener a) {deactivateAssignButton.addActionListener(a);}
	
	/** Constructor for initializing a new ProfessorAssignmentsPanel. */
	public ProfessorAssignmentsPanel()
	{
		super("Open Dropbox");
		
		GridBagConstraints gbc_uploadAssignmentButton = new GridBagConstraints();
		gbc_uploadAssignmentButton.insets = new Insets(0, 0, 0, 5);
		gbc_uploadAssignmentButton.gridx = 1;
		gbc_uploadAssignmentButton.gridy = 2;
		add(uploadAssignmentButton, gbc_uploadAssignmentButton);
		
		GridBagConstraints gbc_activateAssignButton = new GridBagConstraints();
		gbc_activateAssignButton.insets = new Insets(0, 0, 0, 5);
		gbc_activateAssignButton.gridx = 3;
		gbc_activateAssignButton.gridy = 2;
		add(activateAssignButton, gbc_activateAssignButton);
		
		GridBagConstraints gbc_deactivateAssignButton = new GridBagConstraints();
		gbc_deactivateAssignButton.gridx = 4;
		gbc_deactivateAssignButton.gridy = 2;
		add(deactivateAssignButton, gbc_deactivateAssignButton);
	}
	
	/**
	 * Updates the contents of assignmentModel.
	 * @param assignmentList Contents added to assignmentModel.
	 * @param id Used to update assignmentModel.
	 */
	public void displayAssignments(ArrayList<Assignment> assignmentList, Integer id) { 
		assignmentModel.removeAllElements();
		for (int i = 0; i < assignmentList.size(); i++) {
			if (assignmentList.get(i).getCourseId().equals(id)) {
				assignmentModel.addElement(assignmentList.get(i));
			}
		}
		setVisible(true);
	}
}
