package dkeep.logic;

public class GuardRookie extends Enemy
{
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	
	public GuardRookie(int xpos, int ypos)
	{
		super(xpos,ypos,Game.GUARD);
		gcounter = 0;
	}
	
	public char getMove()
	{
		char ch = movg[gcounter%movg.length];
		
		return ch;
	}
	
	public void advanceGuard()
	{
		gcounter++;
	}
	
}