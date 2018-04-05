package dkeep.logic;

public class Hero extends Person
{
	/**
	 * Hero constructor.
	 * @param xpos Initial position on the x axis
	 * @param ypos Initial position on the y axis
	 */
	public Hero(int xpos, int ypos)
	{
		super(xpos,ypos,Game.HERO);
	}
}
