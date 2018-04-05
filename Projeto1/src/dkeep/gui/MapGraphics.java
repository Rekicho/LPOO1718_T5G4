package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;
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


	private void readImages() throws IOException
	{
		wall = ImageIO.read(new File("img/wall.png"));
		hero = ImageIO.read(new File("img/spody.png"));
		guard = ImageIO.read(new File("img/guard.png"));
		door = ImageIO.read(new File("img/door.png"));
		lever = ImageIO.read(new File("img/lever.png"));
		guardasleep = ImageIO.read(new File("img/guardasleep.png"));
		matt = ImageIO.read(new File("img/matt.png"));
		ogre = ImageIO.read(new File("img/ogre.png"));
		weapon = ImageIO.read(new File("img/weapon.png"));
		armedhero = ImageIO.read(new File("img/vader.png"));
		overlap = ImageIO.read(new File("img/pokeball.png"));
		herokey = ImageIO.read(new File("img/hero.png"));
	}

	public MapGraphics() 
	{
		try {readImages();}
		catch (IOException e) {System.exit(1);}			
	}

	public void setMap(Map mapa)
	{
		this.mapa = mapa;
	}

	private BufferedImage chooseImage(char ch)
	{
		switch(ch) 
		{
		case Game.WALL: return wall;
		case Game.GUARD: return guard;
		case Game.HERO: return hero;
		case Game.KEY: return lever;
		case Game.DOOR: return door;
		case Game.SLEEPING:
		case Game.STUNNED_OGRE: return guardasleep;
		case Game.STAIR: return matt;
		case Game.OGRE: return ogre;
		case Game.ARMED_HERO: return armedhero;
		case Game.OGRE_CLUB: return weapon;
		case Game.HERO_WITH_KEY: return herokey;
		case Game.HIDDEN_KEY: return overlap;
		}
		
		return null;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if(mapa == null)
			return;

		g.setColor(Color.BLACK);

		for(int y = 0; y < mapa.length(); y++)
			for(int x = 0; x < mapa.length(y); x++)
				g.drawImage(chooseImage(mapa.position(x, y)), x*30, y*30, this);
	}
}
