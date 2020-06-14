/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

public class Maze 
{	
	private Node[][] mazeMap;
	
	public Maze(int x, int y) 
	{
		mazeMap = new Node[x][y];
	}
	
	public void SetNode(int x, int y, Node n) 
	{
		mazeMap[x][y] = n;
	}
	
	
}