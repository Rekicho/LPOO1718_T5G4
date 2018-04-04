package dkeep.logic;

public class Ogre extends Enemy
{
	private static final int STUNNED_TURNS = 3;
	
	private int sc; 
	
	public Ogre(int xpos, int ypos)
	{
		super(xpos,ypos,Game.OGRE);
		sc = STUNNED_TURNS;
	}
	
	public void gotHit() 
	{
		sc = 0;
		setCaracter(Game.STUNNED_OGRE);
	}
	
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