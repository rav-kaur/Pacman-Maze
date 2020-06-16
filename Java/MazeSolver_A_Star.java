/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

import java.util.Scanner;
import java.util.ArrayList;

public class MazeSolver_A_Star
{
	public static void main(String[] args) 
	{
		
		Maze m = new Maze();
		
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("");
		

		ArrayList<Node> nL = new ArrayList<Node>();
		while(sc.hasNext()) 
		{
			
			
			String next = sc.next();
			
			if(next.equals("\n"))
			{
				m.PushArrayNode(nL);
				nL = new ArrayList<Node>();
			}
			else 
			{
				if(next.equals(" ")) 
				{
					nL.add(new Node(Node.NodeType.space));
				} 
				else if(next.equals("%")) 
				{
					nL.add(new Node(Node.NodeType.wall));
				}
				else if(next.equals("P")) 
				{
					nL.add(new Node(Node.NodeType.start));
				}
				else if(next.equals(".")) 
				{
					nL.add(new Node(Node.NodeType.end));
				}
				
			}
			
			
		}
		
		m.PrintMaze();
		sc.close();
		
	}
	
	void SolveMaze_AStar(Maze m)
	{
		
	}

	
}
