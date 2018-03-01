package dkeep.logic;

public class Ogre extends Enemy
{
	
	private int sc; 
	
	public Ogre(int xpos, int ypos)
	{
		super(xpos,ypos,'0');
		sc = 3;
	}
	
	public void gotHit() {
		sc = 0;
		setCaracter('8');
	}
	
	public boolean isStun() {
		sc++;
		if (sc < 3) {
			return true;
		} else if (sc == 3) {
			setCaracter('0');
			return false;
		} else 
			return false;		
	}
	
}