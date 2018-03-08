package dkeep.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Hero;

public class TestDungeonGameLogic {

	char[][] map1 = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	char[][] map2 = {{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	int[] heroPos = {1,1};
	int[] enemyPos = {3,1};
	
	@Test
	public void test()  {
		Game game1 = new Game(1,map1,heroPos,enemyPos);
		assertEquals(game1.getEnemies().size(), 1);
		Game game3 = new Game(1,map1,heroPos,enemyPos);
		game3.selectDifficulty('1');
		Game game4 = new Game(1,map1,heroPos,enemyPos);
		game4.selectDifficulty('2');
		Game game5 = new Game(1,map1,heroPos,enemyPos);
		game5.selectDifficulty('3');
		Game game6 = new Game(1,map1,heroPos,enemyPos);
		game6.selectDifficulty('4');
		Game game7 = new Game();
		game7.setupLevel2();
		game7.level2('H', false);
		Game game2 = new Game();
		game2.setupLevel2();
		game2.level2('H', true);
	}
	
	@Test
	public void testMoveHeroIntoToFreeCell() 
	{
		Game game = new Game(1,map1,heroPos,enemyPos);
		Hero hero = game.getHero();
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('s');
		game.move(hero);
		heroPos[1] = 2;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('d');
		game.move(hero);
		heroPos[0] = 2;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('w');
		game.move(hero);
		heroPos[1] = 1;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('a');
		game.move(hero);
		heroPos[0] = 1;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed(' ');
		game.move(hero);
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		assertEquals(1, game.level1('q'));
	}
	
	@Test
	public void testMoveIntoWall() 
	{
		Game game = new Game(1,map1,heroPos,enemyPos);
		Hero hero = game.getHero();
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('a');
//		if (game.checkValidMove(hero))
//			game.move(hero);
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('w');
//		if (game.checkValidMove(hero))
//			game.move(hero);
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
	}
	
	@Test
	public void testLoseNextToGuard() 
	{
		Game game1 = new Game(1,map1,heroPos,enemyPos);
		assertEquals(1, game1.level1('d'));
		Game game2 = new Game(1,map1,heroPos,enemyPos);
		game2.level1('s');
		game2.level1('d');
		assertEquals(1, game2.level1('d'));
	}
	
	@Test
	public void testAgainstClosedDoors() 
	{
		Game game = new Game(1,map1,heroPos,enemyPos);
		Hero hero = game.getHero();
		
		hero.updateSpeed('s');
		if (game.checkValidMove(hero))
			game.move(hero);
		
		hero.updateSpeed('a');
//		if (game.checkValidMove(hero))
//			game.move(hero);
		
		heroPos[1] = 2;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
	}
	
	@Test
	public void testopenDoors() 
	{
		Game game = new Game(1,map1,heroPos,enemyPos);
		game.level1('s');
		game.level1('s');
		
		assertEquals('S', game.getPos(0,2));
		assertEquals('S', game.getPos(0,3));
	}
	
	@Test
	public void testWin() 
	{
		Game game = new Game(1,map1,heroPos,enemyPos);
		assertEquals('G',game.getPos(3,1));
		game.level1('s');
		assertEquals('G',game.getPos(2,1));
		assertEquals(1,game.level1('s'));
		assertEquals('G',game.getPos(2,2));
		
		assertEquals(2,game.level1('a'));
		assertEquals('G',game.getPos(2,3));
	}
	
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

