package dkeep.logic;

import java.util.Random;

public class GuardDrunken extends Enemy 
{
	private static final int NORMAL = 1;
	private static final int REVERSE = -1;
	
	private static final char NOT_A_MOVE = ' ';
	
	private static final int SLEEP_ODDS = 3;
	private static final int CHANGE_DIR_ODDS = 2;
	
	
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	private final char[] movgreverse = {'s','d','w','w','w','w','d','d','d','d','d', 'd','w',
			'a','a','a','a','a','a','a','s','s','s','s'};
	
	private boolean asleep = false;
	private boolean reverse = false;
	private int way = NORMAL;
	private boolean changedDir = false;
	
	/**
	* Drunken guard constructor. The drunken guard falls asleep randomly and when he wakes up he may start walking in the opposite direction.
	* @param xpos Initial position on the x axis
	* @param ypos Initial position on the y axis
	*/
	public GuardDrunken(int xpos, int ypos)
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
	* @return Asleep attribute (to check if the guard is currently asleep)
	*/
	public boolean getAsleep() 
	{
		return asleep;
	}
	
	/**
	 * This function determines if the guard will fall asleep (or keep sleeping) or be awake.
	 */
	private void decideSleep()
	{
		Random rng = new Random();
		
		if (randomInt(rng, SLEEP_ODDS) == 0) 
		{
			asleep = true;
			setCaracter(Game.SLEEPING);
		} 
		else 
		{
			asleep = false;
			setCaracter(Game.GUARD);
		}
	}
	
	/**
	* This function tells you if the guard just woke up.
	* @return It returns true if the guard was asleep and now is awake or else it returns false otherwise
	*/
	public boolean isAsleep() 
	{	
		boolean oldState = asleep;
		
		decideSleep();
		
		return (oldState == true && asleep == false);
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
	 * It decides if the guard will change he's direction.
	 */
	
	private void changeDir()
	{
		Random rng = new Random();
		
		if (changedDir) 
			if (randomInt(rng, CHANGE_DIR_ODDS) == 0) 
			{
				reverseMov();
				changedDir = false;
			}
	}
	
	/**
	 * If the guard is awake, it fetches values from movg or movgreverse if he's moving in the opposite way.
	 * @return 'a'-left, 'd'-right, 'w'-up, 's'-down or ' '-guard asleep and there's no movement
	 */
	
	private char decideMove()
	{
		char ch = NOT_A_MOVE;
		
		if (!asleep) 
		{
			if (gcounter<0)
				gcounter += movg.length;
			
			if (!reverse)
				ch = movg[gcounter%movg.length];
			
			else
				ch = movgreverse[gcounter%movg.length];
		}
	
		return ch;
	}
	
	/**
	* Function to determine next move.
	* @return 'a'-left, 'd'-right, 'w'-up, 's'-down or ' '-guard asleep and there's no movement
	*/
	public char getMove()
	{			
		changedDir = isAsleep();
		
		changeDir();
		
		return decideMove();
	}
	
	/**
	* Function to make the counter which determines the next move to increment unless the guard is asleep.
	*/
	public void advanceGuard()
	{	
		if (!asleep)	
			gcounter += way;
	}
}
