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
	
	public GuardDrunken(int xpos, int ypos)
	{
		super(xpos,ypos,Game.GUARD);
	}
	
	private int randomInt (Random rng, int max) 
	{
		int r = rng.nextInt(max);
		return r;
	}
	
	public boolean getAsleep() 
	{
		return asleep;
	}
	
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
	
	public boolean isAsleep() 
	{	
		boolean oldState = asleep;
		
		decideSleep();
		
		return (oldState == true && asleep == false);
	}
	
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
	
	public char getMove()
	{			
		changedDir = isAsleep();
		
		changeDir();
		
		return decideMove();
	}
	
	public void advanceGuard()
	{	
		if (!asleep)	
			gcounter += way;
	}
}
