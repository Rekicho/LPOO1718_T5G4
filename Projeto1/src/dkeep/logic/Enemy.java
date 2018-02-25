package dkeep.logic;

public class Enemy extends Person
{
	public Enemy(int xpos, int ypos, char ch)
	{
		super(xpos,ypos,ch);
	}
	
	public char getMove() {return ' ';}
	public boolean getAsleep() {return false;}
	public boolean isAsleep() {return false;}
	public void advanceGuard() {}
}