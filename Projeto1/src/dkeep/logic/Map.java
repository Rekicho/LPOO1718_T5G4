package dkeep.logic;

import java.util.Arrays;

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
	
	public String toString()
	{
		String temp = "\n\n\n\n\n\n\n";
		
		for(int i = 0; i < map.length; i++)
		{	
			temp += Arrays.toString(map[i]);
			temp += "\n";
		}
		
		temp += "\n\n\n\n\n\n\n";
		
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
}
