package dkeep.logic;

public abstract class Enemy extends Person
{
	private static final char NOT_A_MOVE = ' ';
	
	public Enemy(int xpos, int ypos, char ch)
	{
		super(xpos,ypos,ch);
	}
	
	public char getMove() {return NOT_A_MOVE;}
	public boolean getAsleep() {return false;}
	public boolean isAsleep() {return false;}
	public boolean isStun() {return false;};
	public void gotHit() {};
	public void advanceGuard() {}
}