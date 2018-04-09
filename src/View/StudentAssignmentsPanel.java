package View;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StudentAssignmentsPanel extends UserAssignmentsPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7561850948923475805L;
	
	private JButton downloadAssignButton = new JButton("Download");
	
	public void addDownloadAssignButtonActionListener(ActionListener a) {downloadAssignButton.addActionListener(a);}
	
	StudentAssignmentsPanel()
	{
		super();
		
		GridBagConstraints gbc_deactivateAssignButton = new GridBagConstraints();
		gbc_deactivateAssignButton.gridx = 4;
		gbc_deactivateAssignButton.gridy = 2;
		add(downloadAssignButton, gbc_deactivateAssignButton);
	}
}
