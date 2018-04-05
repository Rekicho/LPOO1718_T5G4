package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.Game;
import dkeep.logic.Map;

public class TestGame 
{
	char[][] map2 = {{'X','X','X','X','X'},
			{'X','H','X','0','X'},
			{'I',' ','X','X','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};

	int[] heroPos = {1,1};
	int[] enemyPos = {3,1};

	@Test
	public void testGame() 
	{
		Game game = new Game(3,'1');
		
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		
		int stillPlaying = 0;
		int i = 0;
		
		while(stillPlaying == 0 && i < 20)
		{
			if(i < 6)
				stillPlaying = game.gameLogic('d');
			
			else if(i < 12)
				stillPlaying = game.gameLogic('w');
			
			else if(i < 20)
				stillPlaying = game.gameLogic('a');
			
			i++;
		}
		
		assertNotEquals(0,stillPlaying);
	}
	
	@Test
	public void testEditedMap()
	{
		Game game = new Game(3,'1');
		//game.getMap().resize(5, 5);
		
		Map map = new Map(map2);
		assertTrue(map.checkValid());
		
		Map gameMap = map.clone();
		assertNotEquals(gameMap, map);
		
		game.setKeep(gameMap);
		
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('d'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('w'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(0,game.gameLogic('a'));
		
		
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('s'));
		assertEquals(0,game.gameLogic('a'));
		assertEquals(2,game.gameLogic('a'));
		
		
		
		
	}
}
