package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Game;

public class TestGuardPersonality {

	@Test
	public void testDrunkenSleep() {
		Game game = new Game(1,'2');
		boolean asleep = false;
		int i = 0;
		char guard;
		
		while(!asleep && i < 100) {
			
			if(i % 2 == 0)
				game.level1('d');
			else
				game.level1('a');
			
			guard = game.getEnemyChar(0);
			
			if (guard == 'g')
				asleep = true;
			
			i++;
		}
		
		assertTrue(asleep);	
	}
	
	@Test
	public void testDrunkenTrunsBack() {
		
		Game game = new Game(1,'2');
		boolean flag = false, asleep = false;
		int i = 0;
		char guard;
		int[] lastPos = game.getEnemies().get(0).getPosition().clone();
		
		while(!flag && i < 100) {
			
			if(i % 2 == 0)
				game.level1('d');
			else
				game.level1('a');
			

			guard = game.getEnemyChar(0);
			
			if (guard == 'g')
				asleep = true;
			else {
				if (asleep) {
					if (lastPos[0] == game.getEnemies().get(0).getPosition()[0] && lastPos[1] == game.getEnemies().get(0).getPosition()[1])
						flag = true;
					lastPos = game.getEnemies().get(0).getPosition().clone();
					asleep = false;
				}
			}
			
			i++;
		}
		
		assertTrue(flag);	
	}
	
	@Test
	public void testSuspiciousTrunsBack() {
		
		Game game = new Game(1,'3');
		boolean flag = false;
		int i = 0;
		int[] lastPos = game.getEnemies().get(0).getPosition().clone();
		
		while(!flag && i < 100) {
			
			if(i % 2 == 0) {
				game.level1('d');
				lastPos = game.getEnemies().get(0).getPosition().clone();
			}
			else
				game.level1('a');
			
			if (lastPos[0] == game.getEnemies().get(0).getPosition()[0] && lastPos[1] == game.getEnemies().get(0).getPosition()[1])
				flag = true;
			
			i++;
		}
		
		assertTrue(flag);	
	}

}
