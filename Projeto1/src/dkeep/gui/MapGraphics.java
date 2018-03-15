package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import dkeep.logic.Map;

public class MapGraphics extends JPanel 
{
	Map mapa;
	
	MapGraphics(){}
	
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
		
		for(int x = 0; x < mapa.length(); x++)
		{
			for(int y = 0; y < mapa.length(); y++)
			{
				if(mapa.position(x, y) == 'X')
					g.fillRect(x*30, y*30, 30, 30);
				
				else if(mapa.position(x, y) == 'G')
				{
					g.setColor(Color.RED);
					g.fillRect(x*30, y*30, 30, 30);
					g.setColor(Color.BLACK);
				}
				
				else if(mapa.position(x, y) == 'H')
				{
					g.setColor(Color.BLUE);
					g.fillRect(x*30, y*30, 30, 30);
					g.setColor(Color.BLACK);
				}
			}
		}
	}
}
