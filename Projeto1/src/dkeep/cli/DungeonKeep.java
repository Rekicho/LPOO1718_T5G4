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

	public static void printGame(Game game)
	{
		System.out.println(game.toString());
	}
	
	public static char getDifficulty(Scanner s)
	{
		System.out.print("Hello there! \n1 - Rookie \n2 - Drunken \n3 - Suspicious \nSelect dificulty: ");

		return s.next().charAt(0);
	}
	
	public static void tryAgain()
	{
		System.out.print("\n\n\nInvalid option! 1, 2 or 3, genius!\n\n\n");
	}

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		int gameover = 0; //0 inside game, 1 gameover, 2 win
		Game game = new Game();
		
		char diff = getDifficulty(s);
		
		while(!(diff == '1' || diff == '2' || diff == '3'))
		{
			tryAgain();			
			diff = getDifficulty(s);
		}
		
		game.selectDifficulty(diff);

		while (gameover == 0)
		{
			printGame(game);
			char ch = getInput(s);
			gameover = game.gameLogic(ch);		
		}

		printGame(game);

		if(gameover == 1)
			System.out.println("GameOver!");
		
		if(gameover == 2)
			System.out.println("Fuerte chico!");

		s.close();
	}

}
