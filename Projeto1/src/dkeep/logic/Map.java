package dkeep.logic;

public class Map 
{
	private char[][] map;
	
	private void setLevel1Map()
	{
		char[][] level1 = { {'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'} };
		
		map = level1;
	}
	
	private void setLevel2Map()
	{
		char[][] level2 = { {'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ','0',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','H',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'} };

		map = level2;
	}
	
	/**
	* Default map constructor.
	* @param level Determines which level's map will create
	*/
	public Map(int level)
	{
		if (level == 1)
			setLevel1Map();
		
		if (level == 2)
			setLevel2Map();
	}
	
	/**
	* Personalized map constructor.
	* @param map Char matrix that'll be used as a map
	*/
	public Map(char[][] map)
	{
		this.map = map;
	}
	
	public void resize(int x, int y) 
	{
		map = new char[y][x];
		
		for(int j = 0; j < y; j++) 
			for (int i = 0; i < x; i++) 
			{
				if(j == 0 || j == y - 1 || i == 0 || i == x - 1)
					map[j][i] = Game.WALL;
				
				else map[j][i] = Game.NOTHING;
			}
	}
	
	/**
	* This function is used to convert the map matrix to text so that it can be printed.
	* @return string of the map
	*/
	public String toString()
	{
		String temp = "";
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++) 
				temp += " " + map[i][j] + " ";
		
			temp += "\n";
		}
		
		return temp;
	}
	
	/**
	* This function lets you know what char is on the [x,y] position. 
	* @param x Position on the x axis
	* @param y Position on the y axis
	* @return [x,y] postion's char
	*/
	public char position(int x, int y)
	{
		return map[y][x];
	}
	
	/**
	 * This function opens the doors of the dungeon's left wall.
	 */
	public void openDoors()
	{
		for(int i = 0; i < map.length; i++)
			if(map[i][0] == Game.DOOR)
				map[i][0] = Game.STAIR;
	}
	
	/**
	 * It puts the char ch on the map[x,y] position.
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @param ch char to set on the map
	 */
	public void setPosition(int x, int y, char ch)
	{
		map[y][x] = ch;
	}
	
	/**
	 * @return map's number of columns
	 */
	public int length()
	{
		return map.length;
	}
	
	/**
	 * @param y column number
	 * @return column's number of rows 
	 */
	public int length(int y)
	{
		return map[y].length;
	}
	
	/**
	 * This function check if the next move is valid. This means that you can't walk the hero through a wall, for example. 
	 * @return true if valid / false if invalid
	 */
	public boolean checkValid()
	{		
		int hero = 0;
		int ogre = 0;
		boolean exitDoor = false;
		boolean key = false;
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
			{
				if(map[j][i] == Game.HERO)
					hero++;
				
				else if(map[j][i] == Game.OGRE)
					ogre++;
				
				else if(map[j][i] == Game.DOOR)
					exitDoor = true;
				
				else if(map[j][i] == Game.KEY)
					key = true;
			}
		
		return (hero == 1) && (ogre > 0 || ogre <= Game.MAX_OGRE) && exitDoor && key;
	}

	/**
	 * @return hero's position
	 */
	public int[] findHero() 
	{
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == Game.HERO)
				{
					int[] heropos = {i, j};
					return heropos;
				}
		
		return null;
	}
	
	/**
	 * @return number of ogres in the dungeon
	 */
	public int countOgres()
	{
		int ogreNum = 0;
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == Game.OGRE)
					ogreNum++;
		
		return ogreNum;
	}
	
	/**
	 * This function returns a copy of the map.
	 * @return cloned Map
	 */
	public Map clone()
	{
		Map cloned = new Map(map);
		
		cloned.resize(map[0].length,map.length);
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				cloned.setPosition(i, j, map[j][i]);
		
		return cloned;		
	}

	/**
	 * 
	 * @param ogreNumber ogre's number in the enemies array
	 * @return ogre position
	 */
	public int[] getOgrePos(int ogreNumber) 
	{
		int[] ogrePos = new int[ogreNumber * 2];
		
		int ogre = 0;
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == Game.OGRE)
				{
					ogrePos[ogre] = i;
					ogrePos[ogre + 1] = j;
					ogre+= 2;
				}
		
		return ogrePos;
	}
}
