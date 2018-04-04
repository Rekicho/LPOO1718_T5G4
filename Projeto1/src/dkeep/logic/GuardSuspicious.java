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
	
	public GuardSuspicious(int xpos, int ypos)
	{
		super(xpos,ypos,Game.GUARD);
	}
	
	private int randomInt (Random rng, int max) 
	{
		int r = rng.nextInt(max);
		return r;
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
	
	public void advanceGuard()
	{
		Random rng = new Random();
		
		if (randomInt(rng, CHANGE_DIR_ODDS) == 0)
			reverseMov();
		else
			gcounter += way;
	}
}