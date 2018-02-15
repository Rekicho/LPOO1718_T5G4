import java.util.Arrays;

public class Game 
{
	public static void main(String[] args) 
	{
		char[][] map = { {'X','X','X','X','X','X','X','X','X','X'},
						 {'X','H',' ',' ','I',' ','X',' ','G','X'},
						 {'X','X','X',' ','X','X','X',' ',' ','X'},
						 {'X',' ','I',' ','I',' ','X',' ',' ','X'},
						 {'X','X','X',' ','X','X','X',' ',' ','X'},
						 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						 {'X','X','X',' ','X','X','X','X',' ','X'},
						 {'X',' ','I',' ','I',' ','X','k',' ','X'},
						 {'X','X','X','X','X','X','X','X','X','X'} };
		
		for(int i = 0; i < map.length; i++)
		{
			System.out.println(Arrays.toString(map[i]));
		}
	}
}