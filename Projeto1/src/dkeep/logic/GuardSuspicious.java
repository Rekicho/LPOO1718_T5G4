package dkeep.logic;

import java.util.Random;

public class GuardSuspicious extends Enemy
{
	private static final int NORMAL = 1;
	private static final int REVERSE = -1;
	
	private static final char NOT_A_MOVE = ' ';
	
	private static final int CHANGE_DIR_ODDS = 3;
	
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	private final char[] movgreverse = {'d','w','w','w','w','d','d','d','d','d', 'd','w',
			'a','a','a','a','a','a','a','s','s','s','s','s'};
	
	private boolean reverse = false;
	private int way = NORMAL;
	
	/**
	* Suspicious guard constructor. The suspicious guard starts walking in the opposite direction randomly.
	* @param xpos Initial position on the x axis
	* @param ypos Initial position on the y axis
	*/
	public GuardSuspicious(int xpos, int ypos)
	{
		super(xpos,ypos,Game.GUARD);
	}
	
	/**
	* Function the generate a random number.
	* @param rng Random object
	* @param max Maximum value of the random number
	* @return random integer smaller then the max argument
	*/
	private int randomInt (Random rng, int max) 
	{
		int r = rng.nextInt(max);
		return r;
	}
	
	/**
	* This function changes the guard's direction by changing the way attribute to its symmetric value 
	*/
	private void reverseMov() 
	{
		if (reverse) 
		{
			reverse = false;
			way = NORMAL;
		}
		else 
		{
			reverse = true;
			way = REVERSE;
		}
	}
	
	/**
	* Function to determine next move. It controls the guards movement. Fetches values from movg or movgreverse if he's moving in the opposite way.
	* @return 'a'-left, 'd'-right, 'w'-up or 's'-down
	*/
	public char getMove()
	{
		char ch = NOT_A_MOVE;
		
		if (gcounter<0)
			gcounter += movg.length;
		
		if (!reverse)
			ch = movg[gcounter%movg.length];
		
		else
			ch = movgreverse[gcounter%movg.length];
	
		return ch;
	}
	
	/**
	* Function to make the counter which determines the next move to increment unless the guard is asleep. Also determines randomly if the guard will change direction.
	*/
	public void advanceGuard()
	{
		Random rng = new Random();
		
		if (randomInt(rng, CHANGE_DIR_ODDS) == 0)
			reverseMov();
		else
			gcounter += way;
	}
}