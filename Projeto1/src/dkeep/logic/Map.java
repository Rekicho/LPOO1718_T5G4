package dkeep.logic;

public class Map 
{
	private char[][] map;
	
	public Map(int level)
	{
		if (level == 1)
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
		
		if (level == 2)
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
	}
	
	public Map(char[][] map)
	{
		this.map = map;
	}
	
	public void resize(int x, int y) {
		map = new char[y][x];
		
		for(int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				if(j == 0 || j == y - 1 || i == 0 || i == x - 1)
					map[j][i] = 'X';
				
				else map[j][i] = ' ';
			}
		}
	}
	
	public String toString()
	{
		String temp = "";
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++) {
			temp += " " + map[i][j] + " ";
			}
		temp += "\n";
		}
		
		return temp;
	}
	
	public char position(int x, int y)
	{
		return map[y][x];
	}
	
	public void openDoors()
	{
		for(int i = 0; i < map.length; i++)
			if(map[i][0] == 'I')
				map[i][0] = 'S';
	}
	
	public void setPosition(int x, int y, char ch)
	{
		map[y][x] = ch;
	}
	
	public int length()
	{
		return map.length;
	}
	
	public int length(int y)
	{
		return map[y].length;
	}
	
	public boolean checkValid()
	{
		int hero = 0;
		int ogre = 0;
		boolean exitDoor = false;
		boolean key = false;
		
		for(int j = 0; j < map.length; j++)
		{
			for(int i = 0; i < map[j].length; i++) 
			{
				if(map[j][i] == 'H')
					hero++;
				
				else if(map[j][i] == '0')
					ogre++;
				
				else if(map[j][i] == 'I')
					exitDoor = true;
				
				else if(map[j][i] == 'k')
					key = true;
			}
		}
		
		return (hero == 1) && (ogre > 0 || ogre <= 5) && exitDoor && key;
	}

	public int[] findHero() 
	{
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == 'H')
				{
					int[] heropos = {i, j};
					return heropos;
				}
		
		return null;
	}
	
	public int countOgres()
	{
		int ogreNum = 0;
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == '0')
					ogreNum++;
		
		return ogreNum;
	}
	
	public Map clone()
	{
		Map cloned = new Map(map);
		
		cloned.resize(map[0].length,map.length);
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				cloned.setPosition(i, j, map[j][i]);
		
		return cloned;		
	}

	public int[] getOgrePos(int ogreNumber) 
	{
		int[] ogrePos = new int[ogreNumber * 2];
		
		int ogre = 0;
		
		for(int j = 0; j < map.length; j++)
			for(int i = 0; i < map[j].length; i++) 
				if(map[j][i] == '0')
				{
					ogrePos[ogre] = i;
					ogrePos[ogre + 1] = j;
					ogre+= 2;
				}
		
		return ogrePos;
	}
}
