package View;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import Data.Assignment;

public class ProfessorAssignmentsPanel extends UserAssignmentsPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -158630035274134969L;
	
	private JButton uploadAssignmentButton = new JButton("Upload New Assignment");
	private JButton activateAssignButton = new JButton("Activate");
	private JButton deactivateAssignButton = new JButton("Deactivate");
	
	public void addUploadButtonActionListener(ActionListener a) {uploadAssignmentButton.addActionListener(a);}
	public void addActivateAssignButtonActionListener(ActionListener a) {activateAssignButton.addActionListener(a);}
	public void addDeactivateAssignButtonActionListener(ActionListener a) {deactivateAssignButton.addActionListener(a);}
	
	
	public ProfessorAssignmentsPanel()
	{
		super();
		
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
	
	public void displayAssignments(ArrayList<Assignment> assignmentList, Integer id) { 
		assignmentModel.removeAllElements();
		System.out.println("course id:" + id);
		System.out.println("assignmnet c id:" + assignmentList.get(1).getCourseId());
		for (int i = 0; i < assignmentList.size(); i++) {
			if (assignmentList.get(i).getCourseId().equals(id)) {
				assignmentModel.addElement(assignmentList.get(i));
			}
		}
		setVisible(true);
	}
}
