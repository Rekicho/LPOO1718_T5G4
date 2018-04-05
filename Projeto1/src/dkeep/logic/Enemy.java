package dkeep.logic;

public abstract class Enemy extends Person
{
	private static final char NOT_A_MOVE = ' ';
	
	/**
	* Enemy constructor.
	* @param xpos Initial position on the x axis
	* @param ypos Initial position on the y axis
	* @param ch enemy's char
	*/
	public Enemy(int xpos, int ypos, char ch)
	{
		super(xpos,ypos,ch);
	}
	
	/**
	 * Overridden function
	 * @return NULL
	 */
	public char getMove() {return NOT_A_MOVE;}
	
	/**
	 * Overridden function
	 * @return false
	 */
	public boolean getAsleep() {return false;}
	
	/**
	 * Overridden function
	 * @return false
	 */
	public boolean isAsleep() {return false;}
	
	/**
	 * Overridden function
	 * @return false
	 */
	public boolean isStun() {return false;};
	
	/**
	 * Overridden function
	 */
	public void gotHit() {};
	
	/**
	 * Overridden function
	 */
	public void advanceGuard() {}
}