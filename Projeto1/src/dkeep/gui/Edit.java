package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Edit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public Edit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] options = {"Hero", "Ogre", "Key", "Wall", "Exit Door"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(319, 59, 141, 20);
		contentPane.add(comboBox);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(10, 11, 46, 19);
		contentPane.add(lblSize);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(95, 11, 46, 19);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setBounds(116, 10, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(39, 10, 46, 20);
		contentPane.add(textField_1);
	}
}
