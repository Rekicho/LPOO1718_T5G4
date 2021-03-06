package dkeep.logic;

import java.util.Arrays;

public abstract class Person 
{
	private static final int VALID_MOVE = -1;
	private static final int INVALID_MOVE = 0;
	private static final int QUIT = 1;
	
	private int[] pos;
	private int[] speed;
	private char caracter;
	
	/**
	* Person constructor.
	* @param xpos Initial position on the x axis
	* @param ypos Initial position on the y axis
	* @param ch characters's char
	*/
	public Person(int xpos, int ypos, char ch) 
	{
		pos = new int[2];
		speed = new int[2];
		
		pos[0] = xpos;
		pos[1] = ypos;
		
		speed[0] = 0;
		speed[1] = 0;
		
		caracter = ch;
	}
	
	/**
	 * It changes the next movement direction.
	 * @param dir direction
	 * @return -1 for valid move, 0 for invalid move and 1 to exit
	 */
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
		
		case ' ': speed[0] = 0; speed[1] = 0; break;

		case 'q':
		case 'Q': return QUIT;

		default: return INVALID_MOVE;
		}

		return VALID_MOVE;
	}
	
	/**
	 * @return Person's position
	 */
	public int[] getPosition()
	{
		return pos;
	} 
	
	/**
	 * @return Person's speed (movement direction)
	 */
	public int[] getSpeed()
	{
		return speed;
	}
	
	/**
	 * Changes the person's position to next one according to it's speed
	 */
	public void updatePos()
	{
		pos[0] += speed[0];
		pos[1] += speed[1];
	}
	
	/**
	 * @return Person's character
	 */
	public char getCaracter()
	{
		return caracter;
	}
	
	/**
	 * Sets person's character.
	 * @param ch character
	 */
	public void setCaracter(char ch)
	{
		caracter = ch;
	}
	
	/**
	 * Sets person's speed
	 * @param x x-speed (0 or 1)
	 * @param y y-speed (0 or 1)
	 */
	public void setSpeed(int x, int y)
	{
		speed[0] = x;
		speed[1] = y;
	}
	
	/**
	 * Sets person's position
	 * @param position [x, y] position in the map's matrix
	 */
	public void setPosition(int[] position)
	{
		pos = Arrays.copyOf(position, position.length);
	}
}
