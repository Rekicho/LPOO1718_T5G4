package dkeep.logic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Game 
{
	private static final char ARMED_HERO = 'A';
	private static final char HERO_WITH_KEY = 'K';
	private static final char OGRE = '0';
	private static final char STUNNED_OGRE = '8';
	private static final char STAIR = 'S';
	private static final char DOOR = 'I';
	private static final char WALL = 'X';
	private static final char KEY = 'k';
	private static final char HIDDEN_KEY = '$';
	private static final char OGRE_CLUB = '*';
	private static final char NOTHING = ' ';
	
	public static final char ROOKIE = '1';
	public static final char DRUNKEN = '2';
	public static final char SUSPICIOUS = '3';
	
	public static final int GUARD_X = 8;
	public static final int GUARD_Y = 1;
	
	public static final int HERO_L1_X = 1;
	public static final int HERO_L1_Y = 1;
	
	public static final int HERO_L2_X = 1;
	public static final int HERO_L2_Y = 7;
	
	public static final int OGRE_X = 4;
	public static final int OGRE_Y = 1;
	
	public static final int DEFAULT_MAX_OGRE_NUM = 3;
	
	public static final int VALID_MOVEMENT = -1;
	public static final int PLAYING = 0;
	public static final int GAMEOVER = 1;
	public static final int WIN = 2;
	
	private int level;
	private int flag;
	private boolean lever;
	private boolean club;
	private Map map;
	private Hero player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Weapon> ogreClubs;
	private int ogreNumber;
	private Map newKeep;
	
	public void setKeep(Map keep)
	{
		newKeep = keep;
	}
	
	public int getLevel()
	{
		return level;
	}

	public Hero getHero()
	{
		return player;
	}
	
	public Map getMap()
	{
		return map;
	}

	public char getPos(int x, int y)
	{
		return map.position(x, y);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public char getEnemyChar(int i)
	{
		return enemies.get(i).getCaracter();
	}
	
	public char getPlayerChar()
	{
		return player.getCaracter();
	}

	public Game()
	{
		Random rng = new Random();
		
		ogreNumber = rng.nextInt(DEFAULT_MAX_OGRE_NUM) + 1;
		level = 1;
		setupLevel1();		
	}

	public Game(int on, char dif)
	{
		ogreNumber = on;
		level = 1;
		setupLevel1();
		selectDifficulty(dif);
	}
	
	private void customLevel1Game(char[][] map, int[] heroPos, int[] enemyPos)
	{
		lever = false;
		this.map = new Map(map);
		player = new Hero(heroPos[0],heroPos[1]);
		enemies = new ArrayList<Enemy>(1);
		Enemy enemy = new GuardRookie(enemyPos[0],enemyPos[1]);
		enemies.add(enemy);
	}
	
	private void customLevel2Game(char[][] map, int[] heroPos, int[] enemyPos)
	{
		ogreNumber = 1;
		club = false;
		this.map = new Map(map);
		player = new Hero(heroPos[0],heroPos[1]);
		player.setCaracter(ARMED_HERO);
		this.map.setPosition(heroPos[0],heroPos[1],ARMED_HERO);
		enemies = new ArrayList<Enemy>(1);
		Enemy enemy = new Ogre(enemyPos[0],enemyPos[1]);
		enemies.add(enemy);
	}
	
	public Game(int level, char[][] map, int[] heroPos, int[] enemyPos)
	{
		this.level = level;
		if(level == 1)
			customLevel1Game(map,heroPos,enemyPos);

		if(level == 2)
			customLevel2Game(map,heroPos,enemyPos);
	}

	private boolean checkWin()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == STAIR);
	}

	public boolean checkValidMove(Person p)
	{
		int[] pos = p.getPosition();
		int[] speed = p.getSpeed();
		char nextPos = map.position(pos[0] + speed[0],pos[1] + speed[1]);

		return (nextPos != WALL && nextPos != DOOR);
	}

	private boolean checkKey()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == KEY);
	}

	public void move(Person p)
	{
		int[] pos;
		int[] speed;

		pos = p.getPosition();
		speed = p.getSpeed();

		map.setPosition(pos[0], pos[1], NOTHING);
		map.setPosition(pos[0] + speed[0], pos[1] + speed[1], p.getCaracter());

		p.updatePos();
	}

	private boolean checkOverlap(Enemy en)
	{
		int[] playerPos = player.getPosition();
		int[] enemyPos = en.getPosition();
		int[] enemySpeed = en.getSpeed();

		return ((enemyPos[0] + enemySpeed[0] == playerPos[0]) 
				&& 
				(enemyPos[1] + enemySpeed[1] == playerPos[1]));
	}

	private boolean checkGameOver(Enemy en)
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
	
	public void selectDifficulty(char i) {
		Enemy enemy;

		if (i == ROOKIE) 
			enemy = new GuardRookie(GUARD_X,GUARD_Y);
		else if (i == DRUNKEN) 
			enemy = new GuardDrunken(GUARD_X,GUARD_Y);
		else if (i == SUSPICIOUS)
			enemy = new GuardSuspicious(GUARD_X,GUARD_Y);
		
		else return;

		enemies.add(enemy);
	}
	
	private void setupLevel1()
	{
		lever = false;
		map = new Map(level);
		player = new Hero(HERO_L1_X,HERO_L1_Y);
		enemies = new ArrayList<Enemy>(1);
	}

	private int level1Movement(Enemy guard)
	{
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

			if(checkOverlap(guard))
			{
				flag = GAMEOVER;
				return flag;
			}

			move(guard);
			guard.advanceGuard();
		}
		
		return flag;
	}
	
	public int level1(char ch) 
	{
		Enemy enemy = enemies.get(0);
		flag = player.updateSpeed(ch);

		if(flag != VALID_MOVEMENT)
			return flag;

		flag = PLAYING;

		if(checkWin())
			flag = WIN;

		enemy.updateSpeed(enemy.getMove());
		
		if(level1Movement(enemy) == GAMEOVER)
			return flag;

		if (flag != WIN && checkGameOver(enemy))
			flag = GAMEOVER;

		return flag;
	}

	private boolean movingToDoor()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == DOOR);
	}

	private void openDoor()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		map.setPosition(pos[0] + speed[0], pos[1] + speed[1], STAIR);
	}

	private boolean pickupKey()
	{
		int[] pos = player.getPosition();
		int[] speed = player.getSpeed();

		return (map.position(pos[0] + speed[0], pos[1] + speed[1]) == KEY);
	}

	private void clearClub(Weapon ogreClub)
	{
		int[] pos = ogreClub.getPosition();

		if (map.position(pos[0], pos[1]) == HIDDEN_KEY)
			map.setPosition(pos[0], pos[1], KEY);

		else map.setPosition(pos[0], pos[1], NOTHING);
	}

	private void setRandMov(Random rng, Enemy en)
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
	
	private void checkOgreOverlap(Enemy og, int[] pos, int ref, Boolean unique, Boolean anyStun, Boolean anyActive)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			if(i != ref && Arrays.equals(pos, enemies.get(i).getPosition()))
			{
				unique = false;

				if(og.getCaracter() == STUNNED_OGRE)
					anyStun = true;

				else anyActive = true;
			}
		}
	}
	
	private void placeOnOgreLastPos(int[] pos, boolean unique, boolean anyStun, boolean anyActive)
	{
		if (map.position(pos[0], pos[1]) == HIDDEN_KEY)
		{
			if(unique)
				map.setPosition(pos[0], pos[1], KEY);
		}

		else if(!unique)
		{
			if(!anyActive && anyStun)
				map.setPosition(pos[0], pos[1], STUNNED_OGRE);
		}	

		else map.setPosition(pos[0], pos[1], NOTHING);
	}
	
	private void placeOnOgreNextPos(Enemy og, int[] pos, int[] speed)
	{
		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == HIDDEN_KEY)
			return;

		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == KEY)
			map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], HIDDEN_KEY);

		else map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], OGRE);
	}

	private void moveOgre(Enemy og, int ref) 
	{
		int[] pos = og.getPosition();
		int[] speed = og.getSpeed();

		Boolean unique = true;
		Boolean anyStun = false;
		Boolean anyActive = false;

		checkOgreOverlap(og, pos, ref, unique, anyStun, anyActive);
		
		placeOnOgreLastPos(pos, unique, anyStun, anyActive);
		
		placeOnOgreNextPos(og, pos, speed);

		og.updatePos();
	}

	private void moveOgreClub(Weapon ogreClub) 
	{
		int[] pos = ogreClub.getPosition();
		int[] speed = ogreClub.getSpeed();

		if(map.position(pos[0]+ speed[0], pos[1] + speed[1]) == KEY)
			map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], HIDDEN_KEY);

		else map.setPosition(pos[0]+ speed[0], pos[1] + speed[1], OGRE_CLUB);

		ogreClub.updatePos();
	}

	private boolean checkClubPossibleMov(Weapon ogreClub)
	{
		int[] pos = ogreClub.getPosition();
		char esquerda = map.position(pos[0] - 1, pos[1]);
		char direita = map.position(pos[0] + 1, pos[1]);
		char cima = map.position(pos[0], pos[1] - 1);
		char baixo = map.position(pos[0], pos[1] + 1);

		return (esquerda == HERO_WITH_KEY || direita == HERO_WITH_KEY || cima == HERO_WITH_KEY || baixo == HERO_WITH_KEY
				||
				esquerda == ARMED_HERO || direita == ARMED_HERO || cima == ARMED_HERO || baixo == ARMED_HERO
				||
				esquerda == NOTHING || direita == NOTHING || cima == NOTHING || baixo == NOTHING
				||
				esquerda == KEY || direita == KEY || cima == KEY || baixo == KEY);

	}
	
	private boolean checkOgrePossibleMov(Enemy ogre)
	{
		int[] pos = ogre.getPosition();
		char esquerda = map.position(pos[0] - 1, pos[1]);
		char direita = map.position(pos[0] + 1, pos[1]);
		char cima = map.position(pos[0], pos[1] - 1);
		char baixo = map.position(pos[0], pos[1] + 1);

		return (esquerda != WALL || direita != WALL || cima != WALL || baixo != WALL);

	}

	private boolean checkClubValidMove(Person p)
	{
		int[] pos = p.getPosition();
		int[] speed = p.getSpeed();
		char nextPos = map.position(pos[0] + speed[0],pos[1] + speed[1]);

		return (nextPos != WALL && nextPos != DOOR && nextPos != OGRE && nextPos != STUNNED_OGRE && nextPos != OGRE_CLUB && nextPos != HIDDEN_KEY);
	}

	private boolean checkStun(Enemy enemy) {
		int[] pos = enemy.getPosition();
		char esquerda = map.position(pos[0] - 1, pos[1]);
		char direita = map.position(pos[0] + 1, pos[1]);
		char cima = map.position(pos[0], pos[1] - 1);
		char baixo = map.position(pos[0], pos[1] + 1);

		return(esquerda == HERO_WITH_KEY || direita == HERO_WITH_KEY || cima == HERO_WITH_KEY || baixo == HERO_WITH_KEY
				||
				esquerda == ARMED_HERO || direita == ARMED_HERO || cima == ARMED_HERO || baixo == ARMED_HERO);
	}

	private void setupOgresandClubs()
	{
		enemies = new ArrayList<Enemy>(ogreNumber);
		ogreClubs = new ArrayList<Weapon>(ogreNumber);

		for(int i = 0; i < ogreNumber; i++)
		{
			Enemy enemy = new Ogre(OGRE_X,OGRE_Y);
			enemies.add(enemy);
		}

		for(int i = 0; i < ogreNumber; i++)
		{
			Weapon weapon = new Weapon(OGRE_X,OGRE_Y);
			ogreClubs.add(weapon);
		}
	}

	public void setupLevel2()
	{
		club = false;
		map = new Map(level);
		player = new Hero(HERO_L2_X,HERO_L2_Y);

		player.setCaracter(ARMED_HERO);
		map.setPosition(HERO_L2_X, HERO_L2_Y, ARMED_HERO);
		
		setupOgresandClubs();
	}
	
	private void setupEditedOgresandClubs() 
	{
		ogreNumber = map.countOgres();

		enemies = new ArrayList<Enemy>(ogreNumber);
		ogreClubs = new ArrayList<Weapon>(ogreNumber);
		
		int[] ogresPos = map.getOgrePos(ogreNumber);

		for(int i = 0; i < ogreNumber * 2; i+= 2)
		{
			Enemy enemy = new Ogre(ogresPos[i],ogresPos[i+1]);
			enemies.add(enemy);
		}

		for(int i = 0; i < ogreNumber * 2; i+= 2)
		{
			Weapon weapon = new Weapon(ogresPos[i],ogresPos[i+1]);
			ogreClubs.add(weapon);
		}
	}
	
	private void setupEditedKeep()
	{
		club = false;
		map = newKeep;
		
		int[] playerPos = map.findHero();
		player = new Hero(playerPos[0],playerPos[1]);

		player.setCaracter(ARMED_HERO);
		map.setPosition(playerPos[0], playerPos[1], ARMED_HERO);
		
		setupEditedOgresandClubs();
	}

	//TODO: Refactor
	public int level2(char ch, boolean ogreNeedsMove) 
	{
		Random rng = new Random();

		flag = player.updateSpeed(ch);

		if(flag != -1)
			return flag;

		flag = 0;

		if(checkWin())
			flag = 2;

		boolean waste = false;

		if (movingToDoor() && player.getCaracter() == HERO_WITH_KEY) 
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
					player.setCaracter(HERO_WITH_KEY);

				move(player);
			}

			for (int i = 0; i < enemies.size(); i++) 
			{
				Enemy ogre = enemies.get(i);
				Weapon ogreClub = ogreClubs.get(i);

				if (flag != 2 && (club && !Arrays.equals(ogre.getPosition(), ogreClub.getPosition()) && checkGameOver(ogreClub)))
				{
					flag = 1;
					return flag;
				}

				//Check ogre and club overpos
				if(club && !Arrays.equals(ogre.getPosition(), ogreClub.getPosition()))
					clearClub(ogreClub);
			}

			for (int i = 0; ogreNeedsMove && i < enemies.size(); i++) 
			{
				Enemy ogre = enemies.get(i);

				//Ogre movement

				if(checkStun(ogre)) {
					int[] pos = ogre.getPosition();
					ogre.gotHit();
					map.setPosition(pos[0], pos[1], STUNNED_OGRE);
				}

				if(!ogre.isStun()) {
					boolean randomFlag = checkOgrePossibleMov(ogre);
					
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

							if(checkStun(ogre)) {
								int[] pos = ogre.getPosition();
								ogre.gotHit();
								if(map.position(pos[0], pos[1]) != HIDDEN_KEY)
									map.setPosition(pos[0], pos[1], STUNNED_OGRE);
							}
						}	
					}
				}
			}

			for (int i = 0; ogreNeedsMove && i < enemies.size(); i++) 
			{
				Enemy ogre = enemies.get(i);
				Weapon ogreClub = ogreClubs.get(i);

				ogreClub.setPosition(ogre.getPosition());
				boolean randomFlag = checkClubPossibleMov(ogreClub);

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
	
	private int playandCheckNextLevel(char ch)
	{
		int proximo = level1(ch);

		if (proximo == WIN)
		{
			level = 2;
			if(newKeep == null)
				setupLevel2();
			
			else setupEditedKeep();
			proximo = PLAYING;
		}

		return proximo;
	}

	public int gameLogic(char ch)
	{
		if (level == 1)
			return playandCheckNextLevel(ch);

		if (level == 2)
			return level2(ch, true);

		return 0;		
	}

	public String toString()
	{
		return map.toString();
	}
}
