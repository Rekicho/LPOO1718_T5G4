package dkeep.gui;

import dkeep.logic.Game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private Game game;
	private int state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 562, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblState = new JLabel("You can start a new game!");
		
		JButton btnUp = new JButton("Up");
		JButton btnDown = new JButton("Down");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		
		btnDown.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(6, 17, 132, 16);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setBounds(123, 17, 37, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(6, 45, 132, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		String[] options = {"Rookie","Drunken","Suspicious"};
		
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(123, 45, 132, 16);
		frame.getContentPane().add(comboBox);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(416, 248, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JTextArea txtrSpace = new JTextArea();
		txtrSpace.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtrSpace.setEditable(false);
		txtrSpace.setBounds(6, 92, 272, 185);
		frame.getContentPane().add(txtrSpace);
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('a');
				if(state != 0) 
				{
					if (state == 1) {
						lblState.setText("You Lost, Bitch!");
					}
					if (state == 2) {
						lblState.setText("You Won!");
					}
					
				btnDown.setEnabled(false);
				btnRight.setEnabled(false);
				btnLeft.setEnabled(false);
				btnUp.setEnabled(false);
				}
				txtrSpace.setText(game.toString());
			}
		});
		btnLeft.setBounds(372, 164, 84, 29);
		frame.getContentPane().add(btnLeft);
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('w');
				if(state != 0) 
				{
					if (state == 1) {
						lblState.setText("You Lost, Bitch!");
					}
					if (state == 2) {
						lblState.setText("You Won!");
					}
					
				btnDown.setEnabled(false);
				btnRight.setEnabled(false);
				btnLeft.setEnabled(false);
				btnUp.setEnabled(false);
				}
				txtrSpace.setText(game.toString());
			}
		});
		btnUp.setBounds(425, 139, 84, 29);
		frame.getContentPane().add(btnUp);
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('d');
				if(state != 0) 
				{
					if (state == 1) {
						lblState.setText("You Lost, Bitch!");
					}
					if (state == 2) {
						lblState.setText("You Won!");
					}
					
				btnDown.setEnabled(false);
				btnRight.setEnabled(false);
				btnLeft.setEnabled(false);
				btnUp.setEnabled(false);
				}
				txtrSpace.setText(game.toString());
			}
		});
		btnRight.setBounds(472, 164, 84, 29);
		frame.getContentPane().add(btnRight);
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('s');
				if(state != 0) 
				{
					if (state == 1) {
						lblState.setText("You Lost, Bitch!");
					}
					if (state == 2) {
						lblState.setText("You Won!");
					}
					
				btnDown.setEnabled(false);
				btnRight.setEnabled(false);
				btnLeft.setEnabled(false);
				btnUp.setEnabled(false);
				}
				txtrSpace.setText(game.toString());
			}
		});
		btnDown.setBounds(425, 192, 84, 29);
		frame.getContentPane().add(btnDown);
		
		lblState.setBounds(6, 306, 333, 16);
		frame.getContentPane().add(lblState);

		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ons = textField.getText();
				int on = Integer.parseInt(ons);
				
				if (on < 0 || on > 5) {
					return;
				}
				
				String difs = (String) comboBox.getSelectedItem();
				char difc;
				
				if (difs=="Rookie") {
					difc = '1';
				} else if (difs == "Drunken") {
					difc = '2';
				} else {
					difc = '3';
				}
				
				game = new Game(on, difc);
				state = 0;
				txtrSpace.setText(game.toString());
				lblState.setText("Click a direction button to move the hero!");
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnUp.setEnabled(true);
			}
		});
		
		btnNewGame.setBounds(416, 84, 117, 29);
		frame.getContentPane().add(btnNewGame);
	}
}
