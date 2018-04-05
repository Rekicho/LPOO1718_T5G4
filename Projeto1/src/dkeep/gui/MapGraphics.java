package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.Map;

public class MapGraphics extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	private Map mapa;
	private BufferedImage hero;
	private BufferedImage wall;
	private BufferedImage guard;
	private BufferedImage door;
	private BufferedImage lever;
	private BufferedImage guardasleep;
	private BufferedImage matt;
	private BufferedImage ogre;
	private BufferedImage weapon;
	private BufferedImage armedhero;
	private BufferedImage overlap;
	private BufferedImage herokey;
	
	
	public MapGraphics() {
		
		try {
			wall = ImageIO.read(new File("img/wall.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			hero = ImageIO.read(new File("img/spody.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			guard = ImageIO.read(new File("img/guard.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			door = ImageIO.read(new File("img/door.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			lever = ImageIO.read(new File("img/lever.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			guardasleep = ImageIO.read(new File("img/guardasleep.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			matt = ImageIO.read(new File("img/matt.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			ogre = ImageIO.read(new File("img/ogre.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			weapon = ImageIO.read(new File("img/weapon.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			armedhero = ImageIO.read(new File("img/vader.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			overlap = ImageIO.read(new File("img/pokeball.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
		try {
			herokey = ImageIO.read(new File("img/hero.png"));
		} catch (IOException e) {
			System.exit(1);
		}
		
	}
	
	public void setMap(Map mapa)
	{
		this.mapa = mapa;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(mapa == null)
			return;
		
		g.setColor(Color.BLACK);
		
		for(int y = 0; y < mapa.length(); y++)
		{
			for(int x = 0; x < mapa.length(y); x++)
			{
				
				switch(mapa.position(x, y)) {
					case 'X':
						g.drawImage(wall, x*30, y*30, this);
						break;
					
					case 'G':
						g.drawImage(guard, x*30, y*30, this);
						break;
						
					case 'H':
						g.drawImage(hero, x*30, y*30, this);
						break;
					
					case 'k'	:
						g.drawImage(lever, x*30, y*30, this);
						break;
						
					case 'I'	:
						g.drawImage(door, x*30, y*30, this);
						break;
						
					case 'g':
					case '8':
						g.drawImage(guardasleep, x*30, y*30, this);
						break;
						
					case 'S'	:
						g.drawImage(matt, x*30, y*30, this);
						break;
						
					case '0':
						g.drawImage(ogre, x*30, y*30, this);
						break;
						
					case 'A':
						g.drawImage(armedhero, x*30, y*30, this);
						break;
						
					case '*':
						g.drawImage(weapon, x*30, y*30, this);
						break;
						
					case 'K':
						g.drawImage(herokey, x*30, y*30, this);
						break;
						
					case '$':
						g.drawImage(overlap, x*30, y*30, this);
						break;
						
						
					default:
						break;
				}
			}
		}
	}
}
