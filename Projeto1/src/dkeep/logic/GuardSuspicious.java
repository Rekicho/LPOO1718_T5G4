package dkeep.logic;

import java.util.Random;

public class GuardSuspicious extends Enemy
{
	
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	private final char[] movgreverse = {'d','w','w','w','w','d','d','d','d','d', 'd','w',
			'a','a','a','a','a','a','a','s','s','s','s','s'};
	
	private boolean reverse = false;
	private int way = 1;
	
	public GuardSuspicious(int xpos, int ypos)
	{
		super(xpos,ypos,'G');
	}
	
	public int randomInt (Random rng, int max) {
		int r = rng.nextInt(max);
		return r;
	}
	
	public void reverseMov() {
		if (reverse) {
			reverse = false;
			way = 1;
		}
		else {
			reverse = true;
			way = -1;
		}
		
		
	}
	
	public char getMove()
	{
		char ch = ' ';
		
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
		
		if (randomInt(rng, 2) == 0)
			reverseMov();
		else
			gcounter += way;
	}
}