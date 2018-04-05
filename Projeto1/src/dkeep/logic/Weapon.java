package dkeep.logic;

public class Weapon extends Enemy
{
	/**
	 * Weapon constructor.
	 * @param xpos Initial position on the x axis
	 * @param ypos Initial position on the y axis
	 */
	public Weapon(int xpos, int ypos)
	{
		super(xpos,ypos,Game.OGRE_CLUB);
	}
}