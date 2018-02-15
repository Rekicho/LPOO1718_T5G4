import java.util.Arrays;
import java.util.Scanner;

public class Game 
{
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
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
		
		int xpos = 1;
		int ypos = 1;
		int xspeed = 0;
		int yspeed = 0;
		
		int flag = 1;
		
		while(flag != 0)
		{	
			System.out.print("\n\n\n\n\n\n\n");
			
			for(int i = 0; i < map.length; i++)
			{	
				System.out.println(Arrays.toString(map[i]));
			}
			
			System.out.print("\n\n\n\n\n\n\n");
			
			System.out.print("Enter a character to move: ");
			
			char dir = s.next().charAt(0);
			
			switch(dir)
			{
			case 'w':
			case 'W': xspeed = 0; yspeed = -1; break;
			
			case 'a':
			case 'A': xspeed = -1; yspeed = 0; break;
			
			case 's':
			case 'S': xspeed = 0; yspeed = 1; break;
			
			case 'd':
			case 'D': xspeed = 1; yspeed = 0; break;
			
			case 'q':
			case 'Q': flag = 0; continue;
			}
			
			if(!(xpos + xspeed < 0 || ypos + yspeed < 0)) 
				if (map[ypos + yspeed][xpos + xspeed] != 'X' && map[ypos + yspeed][xpos + xspeed] != 'I')
				{
					map[ypos][xpos] = ' ';
					map[ypos + yspeed][xpos + xspeed] = 'H';
					xpos += xspeed;
					ypos += yspeed;
				}
		}
	}
}