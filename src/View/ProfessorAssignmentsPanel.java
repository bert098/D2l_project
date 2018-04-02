package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfessorAssignmentsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -158630035274134969L;
	
	JScrollPane scrollPane = new JScrollPane();
	JList assignmentList = new JList();
	JButton openDropboxButton = new JButton("Open Dropbox");
	JButton uploadAssignmentButton = new JButton("Upload New Assignment");
	JButton activateAssignButton = new JButton("Activate");
	JButton deactivateAssignButton = new JButton("Deactivate");
	
	public void addOpenDropboxButtonActionListener(ActionListener a) {openDropboxButton.addActionListener(a);}
	public void addUploadButtonActionListener(ActionListener a) {uploadAssignmentButton.addActionListener(a);}
	public void addActivateAssignButtonActionListener(ActionListener a) {activateAssignButton.addActionListener(a);}
	public void addDeactivateAssignButtonActionListener(ActionListener a) {deactivateAssignButton.addActionListener(a);}
	
	
	public ProfessorAssignmentsPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Assignments");
		titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.anchor = GridBagConstraints.WEST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(assignmentList);
		
		GridBagConstraints gbc_openDropboxButton = new GridBagConstraints();
		gbc_openDropboxButton.insets = new Insets(0, 0, 0, 5);
		gbc_openDropboxButton.gridx = 0;
		gbc_openDropboxButton.gridy = 2;
		add(openDropboxButton, gbc_openDropboxButton);
		
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

}
