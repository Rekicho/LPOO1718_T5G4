package dkeep.logic;

import java.util.Random;

public class GuardDrunken extends Enemy {
	
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	private final char[] movgreverse = {'s','d','w','w','w','w','d','d','d','d','d', 'd','w',
			'a','a','a','a','a','a','a','s','s','s','s'};
	
	private boolean asleep = false;
	private boolean reverse = false;
	private int way = 1;
	private boolean changedDir = false;
	
	public GuardDrunken(int xpos, int ypos)
	{
		super(xpos,ypos,'G');
	}
	
	public int randomInt (Random rng, int max) {
		int r = rng.nextInt(max);
		return r;
	}
	
	public boolean getAsleep() {
		return asleep;
	}
	
	public boolean isAsleep() {	
		
		boolean oldState;
		
		if (asleep == true) 
			oldState = true;
		else 
			oldState = false;
		
		Random rng = new Random();
		
		if (randomInt(rng, 10) > 6) {
			asleep = true;
			setCaracter('g');
		} else {
			asleep = false;
			setCaracter('G');
		}
		
		return (oldState == true && asleep == false);
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
		
		changedDir = isAsleep();
		
		Random rng = new Random();
		
		if (changedDir) {
			if (randomInt(rng, 2) == 0) {
				reverseMov();
				changedDir = false;
			}
		}
		
		if (!asleep) {
			if (gcounter<0)
				gcounter += movg.length;
			if (!reverse)
				ch = movg[gcounter%movg.length];
			else
				ch = movgreverse[gcounter%movg.length];
		}
	
		return ch;
	}
	
	public void advanceGuard()
	{
		
		if (!asleep) {	
			gcounter += way;
		}
	}
}
