/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

public class Node 
{
	public enum NodeType{ wall, space, start, end}

	private boolean visited = false;
	private NodeType typeOfNode;
	
	public Node(NodeType t)
	{
		typeOfNode = t;
		visited = false;
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
			return "X";
		}
		if(typeOfNode == NodeType.end)
		{
			return "E";
		}
		if(typeOfNode == NodeType.start)
		{
			return "S";
		}
		
		return "ERROR";
		
	}
	
	public boolean IsVisited() 
	{
		return visited;
	}
	public void SetVisited(boolean b)
	{
		visited = b;
	}
	
	
	
	
	
}
