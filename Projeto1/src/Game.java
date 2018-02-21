import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game 
{
	public static void printMap(char[][] map)
	{
		System.out.print("\n\n\n\n\n\n\n");

		for(int i = 0; i < map.length; i++)
		{	
			System.out.println(Arrays.toString(map[i]));
		}

		System.out.print("\n\n\n\n\n\n\n");
	}

	public static int updateSpeed(int[] speed, char dir)
	{
		switch(dir)
		{
		case 'w':
		case 'W': speed[0] = 0; speed[1] = -1; break;

		case 'a':
		case 'A': speed[0] = -1; speed[1] = 0; break;

		case 's':
		case 'S': speed[0] = 0; speed[1] = 1; break;

		case 'd':
		case 'D': speed[0] = 1; speed[1] = 0; break;

		case 'q':
		case 'Q': return 0;

		default: return 1;
		}

		return -1;
	}

	public static boolean checkWin(char[][] map, int[] pos, int[] speed)
	{
		return (map[pos[1] + speed[1]][pos[0] + speed[0]] == 'S');
	}

	public static boolean checkValidMove(char[][] map, int[] pos, int[] speed)
	{
		return (map[pos[1] + speed[1]][pos[0] + speed[0]] != 'X' 
				&& 
				map[pos[1] + speed[1]][pos[0] + speed[0]] != 'I');
	}

	public static boolean checkKey(char[][] map, int[] pos, int[] speed)
	{
		return (map[pos[1] + speed[1]][pos[0] + speed[0]] == 'k');
	}

	public static void openDoors(char[][] map)
	{
		for(int i = 0; i < map.length; i++)
			if(map[i][0] == 'I')
				map[i][0] = 'S';
	}

	public static void move(char[][] map, int[] pos, int[] speed, char caracter)
	{
		map[pos[1]][pos[0]] = ' ';

		map[pos[1] + speed[1]][pos[0] + speed[0]] = caracter;

		pos[0] += speed[0];
		pos[1] += speed[1];
	}

	public static boolean checkOverlap(int[] playerPos, int[] enemyPos, int[] enemySpeed)
	{
		return ((enemyPos[0] + enemySpeed[0] == playerPos[0]) 
				&& 
				(enemyPos[1] + enemySpeed[1] == playerPos[1]));
	} 

	public static boolean checkGameOver(int[] playerPos, int[] enemyPos)
	{
		return ((Math.abs(enemyPos[1] - playerPos[1]) == 1 && enemyPos[0] == playerPos[0]) 
				|| 
				(Math.abs(enemyPos[0] - playerPos[0]) == 1 && enemyPos[1] == playerPos[1]));
	} 

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

		//Guard Moves
		char[] movg = {'a','s','s','s','s','a','a','a','a','a','a','s',
				'd','d','d','d','d','d','d','w','w','w','w','w'};

		int[] playerPos = {1,1}; //x,y
		int[] playerSpeed = {0,0}; //xspeed, yspeed

		int[] guardPos = {8,1};
		int[] guardSpeed = {0,0};

		int flag = -1;
		int gcounter = 0;
		boolean lever = false;

		while(flag == -1)
		{	
			printMap(map);
			System.out.print("Enter a character to move: ");

			flag = updateSpeed(playerSpeed,s.next().charAt(0)); //Returns 0 if the player wants to quit, 1 if not valid key, -1 otherwise

			if (flag == 0)
				continue;

			if (flag == 1)
			{
				flag = -1;
				continue;
			}

			updateSpeed(guardSpeed,movg[gcounter%movg.length]);

			if(checkWin(map,playerPos,playerSpeed))
				flag = 1;

			if (checkValidMove(map,playerPos,playerSpeed))
			{
				if(lever)
					lever = false;

				if(checkKey(map,playerPos,playerSpeed))
				{
					lever = true;

					openDoors(map);
				}

				if(!lever)
					move(map,playerPos,playerSpeed,'H');

				if(checkOverlap(playerPos,guardPos,guardSpeed))
				{
					flag = 0;
					break;
				}

				move(map,guardPos,guardSpeed,'G');

				gcounter++;
			}

			if (flag != 1 && checkGameOver(playerPos,guardPos))
				flag = 0;
		}

		printMap(map);

		if(flag == 0)
			System.out.println("GameOver!");

		return flag;
	}

	public static boolean movingToDoor(char[][] map, int[] pos, int[] speed)
	{
		return (map[pos[1] + speed[1]][pos[0] + speed[0]] == 'I');
	} 

	public static void openDoor(char[][] map, int[] pos, int[] speed)
	{
		map[pos[1] + speed[1]][pos[0] + speed[0]] = 'S';
	} 

	public static boolean pickupKey(char[][] map, int[] pos, int[] speed)
	{
		return (map[pos[1] + speed[1]][pos[0] + speed[0]] == 'k');
	} 

	public static void setRandMov(Random rng, int[] speed)
	{
		int r1, r2;

		r1 = rng.nextInt(2);
		r2 = rng.nextInt(2);

		int mov = 1;

		if (r2 == 0)
			mov = -1;

		if (r1 == 0) 
		{
			speed[0] = 0;
			speed[1] = mov;
		}

		else
		{
			speed[0] = mov;
			speed[1] = 0;
		}	
	}

	public static void moveOgre(char[][] map, int[] pos, int[] speed) 
	{
		if (map[pos[1]][pos[0]] == '$')
			map[pos[1]][pos[0]] = 'k';

		else map[pos[1]][pos[0]] = ' ';

		if(map[pos[1] + speed[1]][pos[0]+ speed[0]] == 'k')
			map[pos[1] + speed[1]][pos[0]+ speed[0]] = '$';

		else map[pos[1] + speed[1]][pos[0]+ speed[0]] = '0';

		pos[0] += speed[0];
		pos[1] += speed[1];
	}

	public static void moveOgreClub(char[][] map, int[] pos, int[] speed) 
	{
		if(map[pos[1] + speed[1]][pos[0]+ speed[0]] == 'k')
			map[pos[1] + speed[1]][pos[0]+ speed[0]] = '$';

		else map[pos[1] + speed[1]][pos[0]+ speed[0]] = '*';

		pos[0] += speed[0];
		pos[1] += speed[1];
	}

	public static void clearClub(char[][] map, int[] pos)
	{
		if (map[pos[1]][pos[0]] == '$')
			map[pos[1]][pos[0]] = 'k';

		else map[pos[1]][pos[0]] = ' ';
	}

	public static int level2(Scanner s) 
	{
		Random rng = new Random();

		char[][] map = { {'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ','0',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','H',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'} };

		int[] playerPos = {1,7};
		int[] playerSpeed = {0,0};

		int[] ogrePos = {4,1};
		int[] ogreSpeed = {0,0}; //Can be initialized with any value

		int[] ogreClubPos = {0,0}; //Can be initialized with any value
		int[] ogreClubSpeed = {0,0}; //Can be initialized with any value

		char player = 'H';
		int flag = -1;
		boolean club = false;

		while(flag == -1)
		{	
			printMap(map);
			System.out.print("Enter a character to move: ");

			flag = updateSpeed(playerSpeed,s.next().charAt(0)); //Returns 0 if the player wants to quit, 1 if not valid key, -1 otherwise

			if (flag == 0)
				continue;

			if (flag == 1)
			{
				flag = -1;
				continue;
			}

			if(checkWin(map,playerPos,playerSpeed))
				flag = 1;

			boolean waste = false;

			if (movingToDoor(map,playerPos,playerSpeed) && player == 'K' /*hasKey(player)*/) 
			{
				waste = true;
				openDoor(map,playerPos,playerSpeed);
			}

			if (checkValidMove(map,playerPos,playerSpeed))
			{
				//Player Movement
				if(!waste)
				{
					if(pickupKey(map, playerPos, playerSpeed))
						player = 'K';

					move(map,playerPos,playerSpeed,player);
				}

				if (flag != 1 && (checkGameOver(playerPos,ogrePos) || checkGameOver(playerPos,ogreClubPos)))
				{
					flag = 0;
					break;
				}

				if(!club)
					club = true;

				else clearClub(map, ogreClubPos);

				boolean randomFlag = true;

				//Ogre movement
				while (randomFlag) 
				{
					setRandMov(rng,ogreSpeed);

					if (checkValidMove(map,ogrePos,ogreSpeed)) 
					{
						randomFlag = false;

						if(checkOverlap(playerPos,ogrePos,ogreSpeed))
						{
							flag = 0;
							break;
						}

						moveOgre(map,ogrePos,ogreSpeed);
					}	
				}

				if(flag == 0)
					break;

				if (flag != 1 && (checkGameOver(playerPos,ogrePos) || checkGameOver(playerPos,ogreClubPos)))
				{
					flag = 0;
					break;
				}

				randomFlag = true;

				//Ogre Club movement
				while (randomFlag) 
				{
					setRandMov(rng,ogreClubSpeed);

					ogreClubPos = Arrays.copyOf(ogrePos, ogrePos.length);

					if (checkValidMove(map,ogreClubPos,ogreClubSpeed)) 
					{
						randomFlag = false;

						if(checkOverlap(playerPos,ogreClubPos,ogreClubSpeed))
						{
							flag = 0;
							break;
						}

						moveOgreClub(map,ogreClubPos,ogreClubSpeed);

						flag += 0;
					}	
				}

				if (flag != 1 && (checkGameOver(playerPos,ogrePos) || checkGameOver(playerPos,ogreClubPos)))
				{
					flag = 0;
					break;
				}

			}
		}

		printMap(map);

		if(flag == 0)
			System.out.println("GameOver!");

		return flag;
	}

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);

		//if (level1(s) == 1)
		if (level2(s) == 1)
			System.out.println("¡Fuerte chico!");

		s.close();
	}
}