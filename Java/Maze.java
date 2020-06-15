/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

import java.util.ArrayList;

public class Maze 
{	
	ArrayList<ArrayList<Node>> maze = new ArrayList<ArrayList<Node>>();
	
	public void PushArrayNode(ArrayList<Node> nodeLine) 
	{
		maze.add(nodeLine);
	}
	
	public Node GetNodeAt(int x, int y)
	{
		return maze.get(y).get(x);
	}
	
	public void SetNodeVisited(int x, int y)
	{
		maze.get(y).get(x).SetVisited(true);;
	}
	
	public void PrintMaze()
	{
		for(int y = 0; y < maze.size(); y++) 
		{
			System.out.println("");
			
			for(int x = 0; x < maze.get(y).size(); x++) 
			{
				System.out.print(maze.get(y).get(x).GetNodeTypeName());
			}
			
			
		}
	}
	
}