package View;

import Data.Assignment;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserAssignmentsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4042173038192790908L;

	private JScrollPane scrollPane = new JScrollPane();
	protected JList<Assignment> assignmentList = new JList<Assignment>();
	protected DefaultListModel<Assignment> assignmentModel = new DefaultListModel<>(); 
	
	protected JButton openDropboxButton;// = new JButton("Open Dropbox");
	
	public void addOpenDropboxButtonActionListener(ActionListener a) {openDropboxButton.addActionListener(a);}
	
	public Assignment getSelectedAssignment() {return assignmentList.getSelectedValue();}
	
	public UserAssignmentsPanel(String dropboxButtonText)
	{
		assignmentList.setModel(assignmentModel);
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
		
		openDropboxButton  = new JButton(dropboxButtonText);
		GridBagConstraints gbc_openDropboxButton = new GridBagConstraints();
		gbc_openDropboxButton.insets = new Insets(0, 0, 0, 5);
		gbc_openDropboxButton.gridx = 0;
		gbc_openDropboxButton.gridy = 2;
		add(openDropboxButton, gbc_openDropboxButton);
		
		scrollPane.setViewportView(assignmentList);
	}
	public void displayAssignments(ArrayList<Assignment> assignmentArrayList )
	{
		assignmentModel.removeAllElements();
		for (int i = 0; i < assignmentArrayList.size(); i++)
		{
			if(assignmentArrayList.get(i).getActive() == true)
			{
				assignmentModel.addElement(assignmentArrayList.get(i));
			}
		}
		setVisible(true);
	}
			
}
