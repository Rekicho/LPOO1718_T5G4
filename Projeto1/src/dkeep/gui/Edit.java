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
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Edit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private MapGraphics mapgui;
	private JComboBox comboBox;
	private JLabel lblSize;
	private JLabel lblX;
	
	private Map mapa;

	
	private void createFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void createMapGUI()
	{
		mapgui = new MapGraphics();
		mapgui.setMap(mapa);
		
		mapgui.setBounds(6, 68, 300, 300);
		contentPane.add(mapgui);
	}
	
	private void createComboBox()
	{
		String[] options = {"Hero", "Ogre", "Key", "Wall", "Exit Door"};
		comboBox = new JComboBox(options);
		comboBox.setBounds(382, 12, 137, 19);
		contentPane.add(comboBox);
	}
	
	private void createSizeLabel()
	{
		lblSize = new JLabel("Size");
		lblSize.setBounds(10, 11, 31, 20);
		contentPane.add(lblSize);
	}
	
	private void createXLabel()
	{
		lblX = new JLabel("x");
		lblX.setBounds(83, 11, 9, 19);
		contentPane.add(lblX);
	}
	
	private void createTextFields()
	{
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
		
		textField_2 = new JTextField();
		textField_2.setBounds(473, 43, 46, 29);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
				
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(473, 79, 46, 29);
		contentPane.add(textField_3);
	}
	
	private void createButtonCreateField()
	{
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
	}
	
	/**
	 * Create the frame.
	 */
	public Edit(Map mapa) {
		createFrame();
		this.mapa = mapa;
		createMapGUI();
		createComboBox();
		createSizeLabel();
		createXLabel();
		createTextFields();
		createButtonCreateField();
		
		JLabel lblXPosition = new JLabel("X Position");
		lblXPosition.setBounds(392, 48, 74, 19);
		contentPane.add(lblXPosition);
		
		JLabel lblYPosition = new JLabel("Y Position");
		lblYPosition.setBounds(392, 83, 74, 19);
		contentPane.add(lblYPosition);
		
		JLabel lblNewLabel = new JLabel("You can edit the level!");
		lblNewLabel.setBounds(16, 377, 290, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int x = Integer.parseInt(textField_2.getText());
				int y = Integer.parseInt(textField_3.getText());
				
				if((x == 0 || y == 0 || x == mapa.length(0) - 1 || y == mapa.length() - 1) 
					&& 
				   (comboBox.getSelectedItem() != "Exit Door" && comboBox.getSelectedItem() != "Wall"))
				{
					lblNewLabel.setText("Only door and wall can be added on the map ends!");
					return;
				}
				
				if(comboBox.getSelectedItem() == "Hero")
				{
					mapa.setPosition(x, y, 'H');
				}
				
				else if(comboBox.getSelectedItem() == "Ogre")
				{
					mapa.setPosition(x, y, '0');
				}
				
				else if(comboBox.getSelectedItem() == "Key")
				{
					mapa.setPosition(x, y, 'k');
				}
				
				else if(comboBox.getSelectedItem() == "Wall")
				{
					mapa.setPosition(x, y, 'X');
				}
				
				else if(comboBox.getSelectedItem() == "Exit Door")
				{
					if(x == 0 && y == 0 || x == mapa.length(0) - 1 && y == 0 || x == 0 && y == mapa.length() - 1 || x == mapa.length(0) - 1 && y == mapa.length() - 1) 
					{
						lblNewLabel.setText("That place is unreacheable!");
						return;
					}	
					
					
					mapa.setPosition(x, y, 'I');
				}
				
				contentPane.repaint();
			}
		});
		btnAdd.setBounds(402, 114, 117, 29);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(textField_2.getText());
				int y = Integer.parseInt(textField_3.getText());
				
				if(x == 0 || y == 0 || x == mapa.length(0) - 1 || y == mapa.length() - 1)
					mapa.setPosition(x, y, 'X');
				
				else mapa.setPosition(x, y, ' ');
				
				contentPane.repaint();
			}
		});
		btnRemove.setBounds(402, 143, 117, 29);
		contentPane.add(btnRemove);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(402, 370, 117, 29);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				closeFrame();
			}
		});
		contentPane.add(btnFinish);
	}
	
	public void closeFrame()
	{
		super.dispose();
	}
}
