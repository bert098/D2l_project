package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserEmailPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6727002174304116141L;
	
	private JTextField titleField = new JTextField();
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea messageArea = new JTextArea();
	private JButton sendButton = new JButton("Send");
	private JButton clearButton = new JButton("Clear");
	
	public String getTitle() {return titleField.getText();}
	public String getMessage() {return messageArea.getText();}
	
	public void addSendButtonActionListener(ActionListener a) {sendButton.addActionListener(a);}
	public void addClearButtonActionListener(ActionListener a) {clearButton.addActionListener(a);}
	
	public void clear()
	{
		messageArea.setText("");
		titleField.setText("");
	}
	
	public UserEmailPanel(String windowMessage)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel sendEmailLabel = new JLabel(windowMessage);
		sendEmailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_sendEmailLabel = new GridBagConstraints();
		gbc_sendEmailLabel.anchor = GridBagConstraints.WEST;
		gbc_sendEmailLabel.gridwidth = 2;
		gbc_sendEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sendEmailLabel.gridx = 0;
		gbc_sendEmailLabel.gridy = 0;
		add(sendEmailLabel, gbc_sendEmailLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.anchor = GridBagConstraints.WEST;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 1;
		add(titleLabel, gbc_titleLabel);
		
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.insets = new Insets(0, 0, 5, 0);
		gbc_titleField.gridwidth = 2;
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 0;
		gbc_titleField.gridy = 2;
		add(titleField, gbc_titleField);
		titleField.setColumns(10);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		JLabel messageLabel = new JLabel("Message:");
		scrollPane.setColumnHeaderView(messageLabel);
		
		messageArea.setLineWrap(true);
		scrollPane.setViewportView(messageArea);
		
		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.anchor = GridBagConstraints.WEST;
		gbc_sendButton.insets = new Insets(0, 0, 0, 5);
		gbc_sendButton.gridx = 0;
		gbc_sendButton.gridy = 4;
		add(sendButton, gbc_sendButton);
		
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.anchor = GridBagConstraints.EAST;
		gbc_clearButton.gridx = 1;
		gbc_clearButton.gridy = 4;
		add(clearButton, gbc_clearButton);
	}
}
