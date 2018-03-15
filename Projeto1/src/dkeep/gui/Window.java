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
		frame.setBounds(100, 100, 584, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MapGraphics mapa = new MapGraphics();
		mapa.setBounds(6, 68, 300, 300);
		frame.getContentPane().add(mapa);
		
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
		btnExit.setBounds(399, 366, 117, 29);
		frame.getContentPane().add(btnExit);
		
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
				mapa.setMap(game.getMap());
				frame.repaint();
			}
		});
		btnLeft.setBounds(327, 164, 84, 29);
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
				frame.repaint();
			}
		});
		btnUp.setBounds(407, 124, 84, 29);
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
				frame.repaint();
			}
		});
		btnRight.setBounds(490, 164, 84, 29);
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
				frame.repaint();
			}
		});
		btnDown.setBounds(407, 204, 84, 29);
		frame.getContentPane().add(btnDown);
		
		lblState.setBounds(6, 379, 333, 16);
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
				mapa.setMap(game.getMap());
				lblState.setText("Click a direction button to move the hero!");
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnUp.setEnabled(true);
				frame.repaint();
			}
		});
		
		btnNewGame.setBounds(399, 32, 117, 29);
		frame.getContentPane().add(btnNewGame);
	}
}
