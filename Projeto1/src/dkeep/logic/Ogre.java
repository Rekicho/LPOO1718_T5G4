package dkeep.logic;

public class Ogre extends Enemy
{
	private static final int STUNNED_TURNS = 3;
	
	private int sc; 
	
	/**
	 * Ogre constructor.
	 * @param xpos Initial position on the x axis
	 * @param ypos Initial position on the y axis
	 */
	public Ogre(int xpos, int ypos)
	{
		super(xpos,ypos,Game.OGRE);
		sc = STUNNED_TURNS;
	}
	/**
	 * If the armed hero moves next to the ogre it gets stunted. This function hits the ogre and changes he's char form '0' to '8'. Alon resets the stunt count variable.
	 */
	public void gotHit() 
	{
		sc = 0;
		setCaracter(Game.STUNNED_OGRE);
	}
	
	/**
	 * This function checks if the ogre is currently stunted and if he's stunted "time" is over. If it is over, it changes he's char back to '0'.
	 */
	public boolean isStun() 
	{
		sc++;
		
		if (sc < STUNNED_TURNS) 
			return true;
		
		else if (sc == STUNNED_TURNS) 
			setCaracter(Game.OGRE);
		
		return false;		
	}
	
}