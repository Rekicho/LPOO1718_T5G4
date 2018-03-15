package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Game;

public class TestKeepGameLogic {
	
	char[][] map2 = {{'X','X','X','X','X'},
					{'X','H',' ','O','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};

	int[] heroPos = {1,1};
	int[] enemyPos = {3,1};

	@Test
	public void testStunOgre() 
	{
		Game game = new Game(2,map2,heroPos,enemyPos);
		game.level2('d',false);
		assertEquals('8', game.getEnemyChar(0));
	}
	
	@Test
	public void testPickupKey() 
	{
		Game game = new Game(2,map2,heroPos,enemyPos);
		game.level2('s',false);
		game.level2('s',false);
		assertEquals('K', game.getPlayerChar());
	}
	
	@Test
	public void testLockedDoors() 
	{
		Game game = new Game(2,map2,heroPos,enemyPos);
		game.level2('s',false);
		game.level2('a',false);
		assertEquals('I', game.getPos(0, 2));
	}

}
