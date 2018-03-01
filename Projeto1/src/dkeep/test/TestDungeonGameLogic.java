package dkeep.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Hero;

public class TestDungeonGameLogic {

	char[][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	int[] heroPos = {1,1};
	int[] enemyPos = {3,1};
	
	@Test
	public void testMoveHeroIntoToFreeCell() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		Hero hero = game.getHero();
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('s');
		game.move(hero);
		heroPos[1] = 2;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
	}
	
	@Test
	public void testMoveIntoWall() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		Hero hero = game.getHero();
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
		hero.updateSpeed('a');
		if (game.checkValidMove(hero))
			game.move(hero);
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
	}
	
	@Test
	public void testLoseNextToGuard() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		assertEquals(1, game.level1('d'));
	}
	
	@Test
	public void testAgainstClosedDoors() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		Hero hero = game.getHero();
		
		hero.updateSpeed('s');
		if (game.checkValidMove(hero))
			game.move(hero);
		
		hero.updateSpeed('a');
		if (game.checkValidMove(hero))
			game.move(hero);
		
		heroPos[1] = 2;
		assertTrue(Arrays.equals(heroPos, hero.getPosition()));
	}
	
	@Test
	public void testopenDoors() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		game.level1('s');
		game.level1('s');
		
		assertEquals('S', game.getPos(0,2));
		assertEquals('S', game.getPos(0,3));
	}
	
	@Test
	public void testWin() 
	{
		Game game = new Game(1,map,heroPos,enemyPos);
		assertEquals('G',game.getPos(3,1));
		game.level1('s');
		assertEquals('G',game.getPos(2,1));
		assertEquals(1,game.level1('s'));
		assertEquals('G',game.getPos(2,2));
		
		assertEquals(2,game.level1('a'));
		assertEquals('G',game.getPos(2,3));
	}
	
	

}
