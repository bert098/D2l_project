package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Data.Dropbox;

public class GradesPanel extends JPanel{
	
	private ArrayList<Dropbox> submissions;
	
	private DefaultListModel<Dropbox> submissionModel = new DefaultListModel<>();
	private JList<Dropbox> submissionList = new JList<Dropbox>();
	private JScrollPane scrollPane = new JScrollPane();
	
	public void setSubmissions(ArrayList<Dropbox> submissions) {this.submissions = submissions;}
	
	public GradesPanel()
	{
		submissionList.setModel(submissionModel);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel gradesLabel = new JLabel("Grades");
		gradesLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_gradesLabel = new GridBagConstraints();
		gbc_gradesLabel.anchor = GridBagConstraints.WEST;
		gbc_gradesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_gradesLabel.gridx = 0;
		gbc_gradesLabel.gridy = 0;
		add(gradesLabel, gbc_gradesLabel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JLabel lblSubmission = new JLabel("Submission:");
		scrollPane.setColumnHeaderView(lblSubmission);
		
		scrollPane.setViewportView(submissionList);
	}
	
	public void displaySubmissions(ArrayList<Dropbox> dropboxArrayList)
	{
		submissionModel.removeAllElements();
		for(int i = 0; i < dropboxArrayList.size(); i++)
		{
			submissionModel.addElement(submissionModel.get(i));
		}
		setVisible(true);
	}

}
