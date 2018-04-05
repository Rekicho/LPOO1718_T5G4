package dkeep.logic;

public class GuardRookie extends Enemy
{
	private int gcounter;
	private final char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
								'd','d','d','d','d','d','d','w','w','w','w','w'};
	
	/**
	* Rookie guard constructor. The rookie guard always makes the same circuit.
	* @param xpos Initial position on the x axis
	* @param ypos Initial position on the y axis
	*/
	public GuardRookie(int xpos, int ypos)
	{
		super(xpos,ypos,Game.GUARD);
		gcounter = 0;
	}
	
	/**
	* Function to determine next move from the circuit saved in the movg array.
	* @return 'a'-left, 'd'-right, 'w'-up or 's'-down
	*/
	public char getMove()
	{
		char ch = movg[gcounter%movg.length];
		
		return ch;
	}
	
	/**
	* Function to make the counter which determines the next move to increment.
	*/
	public void advanceGuard()
	{
		gcounter++;
	}
	
}