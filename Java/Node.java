/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

public class Node 
{
	public enum NodeType{ wall, space, start, end}

	private boolean visited = false;
	private NodeType typeOfNode;

	private int row;
	private int col;
	
	public Node(NodeType t, int x, int y)
	{
		typeOfNode = t;
		visited = false;
		row = y;
		col = x;
	}
		
	public void SetNode(NodeType nt) 
	{
		typeOfNode = nt;
	}
	
	public NodeType GetNodeType()
	{
		return typeOfNode;
	}
	
	public String GetNodeTypeName()
	{
		if(typeOfNode == NodeType.space)
		{
			return " ";	
		}
		if(typeOfNode == NodeType.wall)
		{
			return "%";
		}
		if(typeOfNode == NodeType.end)
		{
			return ".";
		}
		if(typeOfNode == NodeType.start)
		{
			return "P";
		}
		
		return "ERROR";
		
	}
	
	public boolean IsVisited() 
	{
		return visited;
	}

	public boolean IsWall()
	{
		return typeOfNode == NodeType.wall;
	}

	public void SetVisited(boolean b)
	{
		visited = b;
	}
	
	
	
	
	
}
