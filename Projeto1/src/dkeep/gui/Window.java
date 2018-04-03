package dkeep.gui;

import dkeep.logic.Game;
import dkeep.logic.Map;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblState;
	private Edit edit;
	private MapGraphics mapa;
	private JLabel lblNumberOfOgres;
	private JLabel lblGuardPersonality;
	private JComboBox comboBox;
	private JButton btnExit;
	private JButton btnNewGame;
	private JButton btnEditKeepLevel;
	
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	
	private Game game;
	private int state;
	private Map level2map;
	private Map level2validmap;
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
		level2map = new Map(2);
		state = -1;
		initialize();
	}
	
	private void checkGameState()
	{
		if(state != 0) 
		{
			if (state == 1) {
				lblState.setText("You Lost, Bitch!");
			}
			if (state == 2) {
				lblState.setText("You Won!");
			}
			
		game = null;
		btnDown.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		}
		mapa.setMap(game.getMap());
		frame.repaint();
		frame.requestFocusInWindow();
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
		
		lblState = new JLabel("You can start a new game!");
		
		edit = new Edit(level2map);
		edit.setVisible(false);
		edit.addWindowListener(new WindowAdapter() 
		{
	            public void windowClosed(WindowEvent e) 
	            {
	            	if(level2map.checkValid())
	            	{
	            		level2validmap = level2map.clone();
	            		
	            		if(game == null)
	            			lblState.setText("Keep map edited!");
	            		
	            		else if(game.getLevel() != 2)
	            		{
	            			game.setKeep(level2validmap.clone());
	            			lblState.setText("Keep map edited!");
	            		}
	            		
	            		else lblState.setText("Can't edit Keep while playing it, it will be changed for next game!");
	            	}
	            	
	            	else lblState.setText("Edited map isn't valid! Keep map wasn't changed!");
	                frame.setVisible(true);
	                frame.requestFocusInWindow();
	            }

	        });
		
		
		mapa = new MapGraphics();
		mapa.setBounds(6, 68, 300, 300);
		frame.getContentPane().add(mapa);
		
		btnUp = new JButton("Up");
		btnDown = new JButton("Down");
		btnLeft = new JButton("Left");
		btnRight = new JButton("Right");
		
		btnDown.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		
		lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(6, 17, 132, 16);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setBounds(123, 17, 37, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(6, 45, 132, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		String[] options = {"Rookie","Drunken","Suspicious"};
		
		comboBox = new JComboBox(options);
		comboBox.setBounds(123, 45, 132, 16);
		frame.getContentPane().add(comboBox);
		
		btnExit = new JButton("Exit");
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
				checkGameState();
				mapa.setMap(game.getMap());
			}
		});
		btnLeft.setBounds(327, 164, 84, 29);
		frame.getContentPane().add(btnLeft);
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('w');
				checkGameState();
			}
		});
		btnUp.setBounds(407, 124, 84, 29);
		frame.getContentPane().add(btnUp);
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('d');
				checkGameState();
			}
		});
		btnRight.setBounds(490, 164, 84, 29);
		frame.getContentPane().add(btnRight);
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = game.gameLogic('s');
				checkGameState();
			}
		});
		btnDown.setBounds(407, 204, 84, 29);
		frame.getContentPane().add(btnDown);
		
		lblState.setBounds(6, 379, 383, 16);
		frame.getContentPane().add(lblState);

		
		btnNewGame = new JButton("New Game");
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
				if(level2validmap != null)
					game.setKeep(level2validmap.clone());
				state = 0;
				mapa.setMap(game.getMap());
				lblState.setText("You can move!");
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnUp.setEnabled(true);
				frame.repaint();
				frame.requestFocusInWindow();
			}
		});
		
		btnNewGame.setBounds(399, 32, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		btnEditKeepLevel = new JButton("Edit Keep");
		btnEditKeepLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				edit.setVisible(true);
			}
		});
		btnEditKeepLevel.setBounds(399, 68, 117, 29);
		frame.getContentPane().add(btnEditKeepLevel);
		
		frame.addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) 
			{
				if(state != 0)
					return;
				
				int key = e.getKeyCode();
				
				switch(key)
				{
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					state = game.gameLogic('w');
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					state = game.gameLogic('a');
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					state = game.gameLogic('d');
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					state = game.gameLogic('s');
					break;
				
				default: return;
				}

				if(state != 0) 
				{
					if (state == 1) {
						lblState.setText("You Lost!");
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
	}
}
