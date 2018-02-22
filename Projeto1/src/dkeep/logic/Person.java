package dkeep.logic;

import java.util.Arrays;

public class Person 
{
	private int[] pos;
	private int[] speed;
	private char caracter;
	
	public Person(int xpos, int ypos, char ch) {
		pos = new int[2];
		speed = new int[2];
		
		pos[0] = xpos;
		pos[1] = ypos;
		
		speed[0] = 0;
		speed[1] = 0;
		
		caracter = ch;
	}
	
	public int updateSpeed(char dir)
	{
		switch(dir)
		{
		case 'w':
		case 'W': speed[0] = 0; speed[1] = -1; break;

		case 'a':
		case 'A': speed[0] = -1; speed[1] = 0; break;

		case 's':
		case 'S': speed[0] = 0; speed[1] = 1; break;

		case 'd':
		case 'D': speed[0] = 1; speed[1] = 0; break;

		case 'q':
		case 'Q': return 1;

		default: return 0;
		}

		return -1;
	}
	
	public int[] getPosition()
	{
		return pos;
	} 
	
	public int[] getSpeed()
	{
		return speed;
	}
	
	public void updatePos()
	{
		pos[0] += speed[0];
		pos[1] += speed[1];
	}
	
	public char getCaracter()
	{
		return caracter;
	}
	
	public void setCaracter(char ch)
	{
		caracter = ch;
	}
	
	public void setSpeed(int x, int y)
	{
		speed[0] = x;
		speed[1] = y;
	}
	
	public void setPosition(int[] position)
	{
		pos = Arrays.copyOf(position, position.length);
	}
}
