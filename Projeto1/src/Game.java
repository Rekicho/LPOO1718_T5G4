import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game 
{

	public static int level1(Scanner s) 
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

		char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};

		int xpos = 1;
		int ypos = 1;
		int xspeed = 0;
		int yspeed = 0;

		int gxpos = 8;
		int gypos = 1;
		int gxspeed = 0;
		int gyspeed = 0;

		int flag = -1;
		int gcounter = 0;
		boolean lever = false;

		while(flag == -1)
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

			default: continue;
			}

			switch(movg[gcounter%movg.length])
			{
			case 'w': gxspeed = 0; gyspeed = -1; break;
			case 'a': gxspeed = -1; gyspeed = 0; break;
			case 's': gxspeed = 0; gyspeed = 1; break;
			case 'd': gxspeed = 1; gyspeed = 0; break;
			}

			if (map[ypos + yspeed][xpos + xspeed] == 'S')
				flag = 1;

			if (map[ypos + yspeed][xpos + xspeed] != 'X' && map[ypos + yspeed][xpos + xspeed] != 'I')
			{
				if(lever)
					lever = false;

				if(map[ypos + yspeed][xpos + xspeed] == 'k')
				{
					lever = true;

					for(int i = 0; i < map.length; i++)
						if(map[i][0] == 'I')
							map[i][0] = 'S';
				}

				if(!lever)
				{
					map[ypos][xpos] = ' ';

					map[ypos + yspeed][xpos + xspeed] = 'H';

					xpos += xspeed;
					ypos += yspeed;
				}

				if((gxpos + gxspeed == xpos) && (gypos + gyspeed == ypos))
				{
					flag = 0;
					break;
				}
				
				map[gypos][gxpos] = ' ';
				map[gypos + gyspeed][gxpos + gxspeed] = 'G';

				gxpos += gxspeed;
				gypos += gyspeed;

				gcounter++;

			}

			if (flag != 1 && ((Math.abs(gypos - ypos) == 1 && gxpos == xpos) || (Math.abs(gxpos - xpos) == 1 && gypos == ypos)))
				flag = 0;
		}

		System.out.print("\n\n\n\n\n\n\n");

		for(int i = 0; i < map.length; i++) {	
			System.out.println(Arrays.toString(map[i]));
		}

		System.out.print("\n\n\n\n\n\n\n");

		if(flag == 0)
			System.out.println("GameOver!");

		return flag;
	}

	public static int level2(Scanner s) {

		Random rng = new Random();

		char[][] map = { 
				{'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ','0',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','H',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'} 
		};

		int flag = -1;

		int xpos = 1;
		int ypos = 7;
		int xspeed = 0;
		int yspeed = 0;

		int oxpos = 4;
		int oypos = 1;

		char player = 'H';

		while(flag == -1)
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

			default: continue;
			}

			if (map[ypos + yspeed][xpos + xspeed] == 'S')
				flag = 1;

			boolean waste = false;

			if (map[ypos + yspeed][xpos + xspeed] == 'I' && player == 'K') 
			{
				waste = true;
				map[ypos + yspeed][xpos + xspeed] = 'S';
			}

			if (map[ypos + yspeed][xpos + xspeed] != 'X' && map[ypos + yspeed][xpos + xspeed] != 'I')
			{
				if(!waste)
				{
					if(map[ypos + yspeed][xpos + xspeed] == 'k')
						player = 'K';

					map[ypos][xpos] = ' ';
					map[ypos + yspeed][xpos + xspeed] = player;

					xpos += xspeed;
					ypos += yspeed;
				}

				int r1;
				int r2;

				boolean randomFlag = true;

				while (randomFlag) {

					r1 = rng.nextInt(2);
					r2 = rng.nextInt(2);

					int mov = 1;

					if (r2 == 0)
						mov = -1;

					if (r1 == 0) {
						if (map[oypos + mov][oxpos] != 'I' && map[oypos + mov][oxpos] != 'X') {

							randomFlag = false;
							
							if((oxpos == xpos) && (oypos + mov == ypos))
							{
								flag = 0;
								break;
							}

							
							if (map[oypos][oxpos] == '$')
								map[oypos][oxpos] = 'k';
							
							else map[oypos][oxpos] = ' ';
							
							if(map[oypos + mov][oxpos] == 'k')
								map[oypos + mov][oxpos] = '$';
							
							else map[oypos + mov][oxpos] = '0';
							
							oypos += mov;

						}	
					} else {
						if (map[oypos][oxpos + mov] != 'I' && map[oypos][oxpos + mov] != 'X') {

							randomFlag = false;
							
							if((oxpos + mov == xpos) && (oypos == ypos))
							{
								flag = 0;
								break;
							}

							if (map[oypos][oxpos] == '$')
								map[oypos][oxpos] = 'k';
							
							else map[oypos][oxpos] = ' ';
							
							if(map[oypos][oxpos + mov] == 'k')
								map[oypos][oxpos + mov] = '$';
							
							else map[oypos][oxpos + mov] = '0';
							
							oxpos += mov;

						}
					}
				}

				if (flag != 1 && ((Math.abs(oypos - ypos) == 1 && oxpos == xpos) || (Math.abs(oxpos - xpos) == 1 && oypos == ypos)))
					flag = 0;

			}

		}

		System.out.print("\n\n\n\n\n\n\n");

		for(int i = 0; i < map.length; i++) {	
			System.out.println(Arrays.toString(map[i]));
		}

		System.out.print("\n\n\n\n\n\n\n");

		if(flag == 0)
			System.out.println("GameOver!");

		return flag;

	}

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);

		if (level1(s) == 1)
			if (level2(s) == 1)
				System.out.println("¡Fuerte chico!");

		s.close();
	}
}