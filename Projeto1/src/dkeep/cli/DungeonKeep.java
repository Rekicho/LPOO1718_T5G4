package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class DungeonKeep 
{
	private static char getInput(Scanner s)
	{
		System.out.print("Enter a character to move: ");

		return s.next().charAt(0);
	}

	private static void printGame(Game game)
	{
		System.out.println(game.toString());
	}
	
	private static void tryAgain()
	{
		System.out.print("\n\n\nInvalid option! 1, 2 or 3, genius!\n\n\n");
	}
	
	private static char getDifficulty(Scanner s)
	{
		System.out.print("Hello there! \n1 - Rookie \n2 - Drunken \n3 - Suspicious \nSelect dificulty: ");

		char diff = s.next().charAt(0);
		
		while(!(diff == Game.ROOKIE || diff == Game.DRUNKEN || diff == Game.SUSPICIOUS))
		{
			tryAgain();			
			System.out.print("Hello there! \n1 - Rookie \n2 - Drunken \n3 - Suspicious \nSelect dificulty: ");
			diff = s.next().charAt(0);
		}
		
		return diff;
	}
	
	public static int gameloop(Game game, Scanner s)
	{
		int gameover = Game.PLAYING;
		
		while (gameover == Game.PLAYING)
		{
			printGame(game);
			char ch = getInput(s);
			gameover = game.gameLogic(ch);		
		}
		
		return gameover;
	}
	
	private static void printGameOverMessage(int gameover) 
	{
		if(gameover == Game.GAMEOVER)
			System.out.println("GameOver!");
		
		else if(gameover == Game.WIN)
			System.out.println("Fuerte chico!");
	}

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		Game game = new Game();
		
		game.selectDifficulty(getDifficulty(s));
		
		int gameover = gameloop(game, s);

		printGame(game);
		
		printGameOverMessage(gameover);

		s.close();
	}

}
