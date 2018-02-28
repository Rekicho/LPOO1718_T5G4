package dkeep.logic;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Game 
{
	private int level;
	private int flag;
	private boolean lever;
	private boolean club;
	private Map map;
	private Hero player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Weapon> ogreClubs;

	private static char getInput(Scanner s)
	{
		System.out.print("Hello there! \n1 - Rookie \n2 - Drunken \n3 - Suspicious \nSelect dificulty: ");

		return s.next().charAt(0);
	}

	public Game()
	{
		level = 1;
		setupLevel1();		
	}

	public boolean checkWin()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == 'S');
	}

	public boolean checkValidMove(Person p)
	{
		int[] pos = p.getPosition();
		int[] speed = p.getSpeed();
		char nextPos = map.position(pos[0] + speed[0],pos[1] + speed[1]);

		return (nextPos != 'X' && nextPos != 'I');
	}

	public boolean checkKey()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == 'k');
	}

	public void move(Person p)
	{
		int[] pos;
		int[] speed;

		pos = p.getPosition();
		speed = p.getSpeed();

		map.setPosition(pos[0], pos[1], ' ');
		map.setPosition(pos[0] + speed[0], pos[1] + speed[1], p.getCaracter());

		p.updatePos();
	}

	public boolean checkOverlap(Enemy en)
	{
		int[] playerPos = player.getPosition();
		int[] enemyPos = en.getPosition();
		int[] enemySpeed = en.getSpeed();

		return ((enemyPos[0] + enemySpeed[0] == playerPos[0]) 
									&& 
				(enemyPos[1] + enemySpeed[1] == playerPos[1]));
	}

	public boolean checkGameOver(Enemy en)
	{
		int[] playerPos = player.getPosition();
		int[] enemyPos = en.getPosition();

		if (!en.getAsleep()) {

			return ((Math.abs(enemyPos[1] - playerPos[1]) == 1 && enemyPos[0] == playerPos[0]) 
					|| 
					(Math.abs(enemyPos[0] - playerPos[0]) == 1 && enemyPos[1] == playerPos[1]));
		} else {
			return false;
		}
	}

	public void selectDifficulty() {
		Scanner s = new Scanner(System.in);
		char i = getInput(s);

		Enemy enemy;

		if (i == '1') 
			enemy = new GuardRookie(8,1);
		else if (i == '2') 
			enemy = new GuardDrunken(8,1);
		else if (i == '3')
			enemy = new GuardSuspicious(8,1);
		else {
			System.out.print("\n\n\nInvalid option! 1, 2 or 3, genius!\n\n\n");
			selectDifficulty();
			return;
		}

		enemies.add(enemy);
	}

	public void setupLevel1()
	{
		lever = false;
		map = new Map(level);
		player = new Hero(1,1);
		enemies = new ArrayList<Enemy>(1);
		selectDifficulty();
	}

	public int level1(char ch) 
	{
		Enemy enemy = enemies.get(0);
		flag = player.updateSpeed(ch);

		if(flag != -1)
			return flag;

		flag = 0;

		if(checkWin())
			flag = 2;

		enemy.updateSpeed(enemy.getMove());

		if (checkValidMove(player))
		{
			if(lever)
				lever = false;

			if(checkKey())
			{
				lever = true;

				map.openDoors();
			}

			if(!lever)
				move(player);

			if(checkOverlap(enemy))
			{
				flag = 1;
				return flag;
			}

			move(enemy);
			enemy.advanceGuard();
		}

		if (flag != 2 && checkGameOver(enemy))
			flag = 1;

		return flag;
	}

	public boolean movingToDoor()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == 'I');
	}

	public void openDoor()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		map.setPosition(pos[0] + speed[0], pos[1] + speed[1], 'S');
	}

	public boolean pickupKey()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == 'k');
	}

	public void clearClub(Weapon ogreClub)
	{
		int[] pos = ogreClub.getPosition();

		if (map.position(pos[0], pos[1]) == '$')
			map.setPosition(pos[0], pos[1], 'k');

		else map.setPosition(pos[0], pos[1], ' ');
	}

	public void setRandMov(Random rng, Enemy en)
	{
		int r1, r2;

		r1 = rng.nextInt(2);
		r2 = rng.nextInt(2);

		int mov = 1;

		if (r2 == 0)
			mov = -1;

		if (r1 == 0) 
			en.setSpeed(0,mov);

		else en.setSpeed(mov,0);	
	}

	public void moveOgre(Enemy og, int ref) 
	{
		int[] pos = og.getPosition();
		int[] speed = og.getSpeed();

		boolean unique = true;

		for(int i = 0; i < enemies.size(); i++)
		{
			if(i != ref && Arrays.equals(pos, enemies.get(i).getPosition()))
			{
				unique = false;
				break;
			}
		}

		if (map.position(pos[0], pos[1]) == '$')
		{
			if(unique)
				map.setPosition(pos[0], pos[1], 'k');
		}

		else if(unique)
			map.setPosition(pos[0], pos[1], ' ');
		
		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == '$')
		{
			og.updatePos();
			return;
		}

		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == 'k')
			map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], '$');

		else map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], '0');

		og.updatePos();
	}

	public void moveOgreClub(Weapon ogreClub) 
	{
		int[] pos = ogreClub.getPosition();
		int[] speed = ogreClub.getSpeed();
		
		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == 'k')
			map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], '$');

		else map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], '*');

		ogreClub.updatePos();
	}
	
	public boolean checkPossibleMov(Weapon ogreClub)
	{
		int[] pos = ogreClub.getPosition();
		char esquerda = map.position(pos[0] - 1, pos[1]);
		char direita = map.position(pos[0] + 1, pos[1]);
		char cima = map.position(pos[0], pos[1] - 1);
		char baixo = map.position(pos[0], pos[1] + 1);
		
		return (esquerda == 'K' || direita == 'K' || cima == 'K' || baixo == 'K'
												  ||
				esquerda == 'A' || direita == 'A' || cima == 'A' || baixo == 'A'
												  ||
				esquerda == ' ' || direita == ' ' || cima == ' ' || baixo == ' '
												  ||
				esquerda == 'k' || direita == 'k' || cima == 'k' || baixo == 'k');
	             
	}
	
	public boolean checkClubValidMove(Person p)
	{
		int[] pos = p.getPosition();
		int[] speed = p.getSpeed();
		char nextPos = map.position(pos[0] + speed[0],pos[1] + speed[1]);

		return (nextPos != 'X' && nextPos != 'I' && nextPos != '0' && nextPos != '*' && nextPos != '$');
	}

	public void setupLevel2()
	{
		Random rng = new Random();

		club = false;
		map = new Map(level);
		player = new Hero(1,7);

		player.setCaracter('A');

		int randomNum = rng.nextInt(3) + 1;

		enemies = new ArrayList<Enemy>(randomNum);
		ogreClubs = new ArrayList<Weapon>(randomNum);

		for(int i = 0; i < randomNum; i++)
		{
			Enemy enemy = new Ogre(4,1);
			enemies.add(enemy);
		}

		for(int i = 0; i < randomNum; i++)
		{
			Weapon weapon = new Weapon(0,0);
			ogreClubs.add(weapon);
		}
	}

	public int level2(char ch) 
	{
		Random rng = new Random();

		flag = player.updateSpeed(ch);

		if(flag != -1)
			return flag;

		flag = 0;

		if(checkWin())
			flag = 2;

		boolean waste = false;

		if (movingToDoor() && player.getCaracter() == 'K') 
		{
			waste = true;
			openDoor();
		}

		if (checkValidMove(player))
		{

			//Player Movement
			if(!waste)
			{
				if(pickupKey())
					player.setCaracter('K');

				move(player);
			}

			for (int i = 0; i < enemies.size(); i++) 
			{
				Enemy ogre = enemies.get(i);
				Weapon ogreClub = ogreClubs.get(i);

				if (flag != 2 && (checkGameOver(ogre) || (club && !Arrays.equals(ogre.getPosition(), ogreClub.getPosition()) && checkGameOver(ogreClub))))
				{
					flag = 1;
					return flag;
				}

				//Check ogre and club overpos
				if(club && !Arrays.equals(ogre.getPosition(), ogreClub.getPosition()))
					clearClub(ogreClub);

				boolean randomFlag = true;

				//Ogre movement

				while (randomFlag) 
				{
					setRandMov(rng,ogre);

					if (checkValidMove(ogre))
					{
						randomFlag = false;

						if(checkOverlap(ogre))
						{
							flag = 1;
							return flag;
						}

						moveOgre(ogre, i);
					}	
				}
			}

			for (int i = 0; i < enemies.size(); i++) 
			{
				Enemy ogre = enemies.get(i);
				Weapon ogreClub = ogreClubs.get(i);

				if (flag != 1 && checkGameOver(ogre))
				{
					flag = 1;
					return flag;
				}
				
				ogreClub.setPosition(ogre.getPosition());
				boolean randomFlag = checkPossibleMov(ogreClub);
				
				if(!randomFlag)
					ogreClub.setSpeed(0, 0);

				//Ogre Club movement
				while (randomFlag) 
				{
					setRandMov(rng,ogreClub);

					if (checkClubValidMove(ogreClub)) 
					{
						randomFlag = false;

						if(checkOverlap(ogreClub))
						{
							flag = 1;
							return flag;
						}

						moveOgreClub(ogreClub);
					}	
				}

				if (flag != 2 && checkGameOver(ogreClub) && !Arrays.equals(ogre.getPosition(),ogreClub.getPosition()))
				{
					flag = 1;
					return flag;
				}

			}

		}

		club = true;
		return flag;
	}

	public int gameLogic(char ch)
	{
		if (level == 1)
		{
			int proximo = level1(ch);

			if (proximo == 2)
			{
				level = 2;
				setupLevel2();
				proximo = 0;
			}

			return proximo;
		}

		if (level == 2)
			return level2(ch);

		return 0;		
	}

	public String toString()
	{
		return map.toString();
	}
}
