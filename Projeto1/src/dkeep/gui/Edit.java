package dkeep.gui;

import dkeep.logic.Map;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Edit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Map mapa;

	/**
	 * Create the frame.
	 */
	public Edit(Map mapa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.mapa = mapa;
		MapGraphics mapgui = new MapGraphics();
		mapgui.setMap(mapa);
		
		mapgui.setBounds(6, 68, 300, 300);
		contentPane.add(mapgui);
		
		String[] options = {"Hero", "Ogre", "Key", "Wall", "Exit Door"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(382, 12, 137, 19);
		contentPane.add(comboBox);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(10, 11, 31, 20);
		contentPane.add(lblSize);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(83, 11, 9, 19);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setText("9");
		textField.setBounds(90, 10, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("9");
		textField_1.setColumns(10);
		textField_1.setBounds(39, 10, 46, 20);
		contentPane.add(textField_1);
		
		JButton btnCreateField = new JButton("Create Field");
		btnCreateField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(textField.getText());
				int y = Integer.parseInt(textField_1.getText());
				
				if(x >= 5 && x <= 10 && y >= 5 && y <= 10) {
					mapa.resize(x,y);
				}
				
				contentPane.repaint();
			}
		});
		btnCreateField.setBounds(139, 7, 117, 29);
		contentPane.add(btnCreateField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(473, 43, 46, 29);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblXPosition = new JLabel("X Position");
		lblXPosition.setBounds(392, 48, 74, 19);
		contentPane.add(lblXPosition);
		
		JLabel lblYPosition = new JLabel("Y Position");
		lblYPosition.setBounds(392, 83, 74, 19);
		contentPane.add(lblYPosition);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(473, 79, 46, 29);
		contentPane.add(textField_3);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(402, 114, 117, 29);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(402, 143, 117, 29);
		contentPane.add(btnRemove);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(402, 302, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(402, 273, 117, 29);
		contentPane.add(btnFinish);
	}
}
