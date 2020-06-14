/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

public class Node 
{
	public enum NodeType{ wall, space, start, end}

	private boolean visited = false;
	private NodeType typeOfNode;

		
	public void SetNode(NodeType nt) 
	{
		typeOfNode = nt;
	}
	public NodeType GetNodeType()
	{
		return typeOfNode;
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
