package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

import dkeep.logic.Game;
import dkeep.logic.Ogre;

import org.junit.Test;

public class TestOgreMovement {

	@Test
	public void testOgreBehavior() {
		Game game = new Game(1,2);
		int[] ogrePos, ogreCurrentPos;
		boolean left = false, right = false, up = false, down = false;
		int i = 0;
		while(!(left && right && up && down) && i < 100) {
			ogrePos = game.getEnemies().get(0).getPosition().clone();
			
			if(i % 2 == 0)
				game.level2('d',true);
			else
				game.level2('a',true);
			
			i++;
			
			ogreCurrentPos = game.getEnemies().get(0).getPosition();
			
			assertFalse(ogrePos == ogreCurrentPos);
			assertFalse(Arrays.equals(ogrePos,ogreCurrentPos));
			
			if (ogrePos[0] == ogreCurrentPos[0] + 1)
				right = true;
			else if (ogrePos[0] == ogreCurrentPos[0] - 1)
				left = true;
			else if (ogrePos[1] == ogreCurrentPos[1] + 1)
				down = true;
			else if (ogrePos[1] == ogreCurrentPos[1] - 1)
				up = true;
		}
		
		assertTrue(left && right && up && down);
	}
	
	@Test
	public void testClubBehavior() {
		Game game = new Game(1,2);
		int[] ogrePos, clubPos;
		boolean left = false, right = false, up = false, down = false;
		int i = 0;
		while(!(left && right && up && down) && i < 100) {
			ogrePos = game.getEnemies().get(0).getPosition();
			
			if(i % 2 == 0)
				game.level2('d',true);
			else
				game.level2('a',true);
			
			i++;
			
			clubPos = game.getWeapons().get(0).getPosition();
	
			assertFalse(Arrays.equals(ogrePos,clubPos));
			
			if (ogrePos[0] == clubPos[0] + 1)
				right = true;
			else if (ogrePos[0] == clubPos[0] - 1)
				left = true;
			else if (ogrePos[1] == clubPos[1] + 1)
				down = true;
			else if (ogrePos[1] == clubPos[1] - 1)
				up = true;
		}
		
		assertTrue(left && right && up && down);
	}

}