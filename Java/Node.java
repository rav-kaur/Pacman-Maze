/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

 //import java.util.ArrayList;

public class Node 
{
	public enum NodeType{ wall, space, start, end, path}

	private boolean visited = false;
	private NodeType typeOfNode;

	private int row;
	private int col;
	private Node parent;
	
	public Node(NodeType t, int x, int y)
	{
		typeOfNode = t;
		visited = false;
		this.row = y;
		this.col = x;
		this.parent = null;
	}

	public void setParent(Node parent){
		this.parent = parent;
	}

	public Node getParent(){
		return this.parent;
	}

	public int getRow(){
		return this.row;
	}

	public int getCol(){
		return this.col;
	}
		
	public void SetNode(NodeType nt) 
	{
		typeOfNode = nt;
	}
	
	public NodeType GetNodeType()
	{
		return typeOfNode;
	}


	public boolean isValidLocation( Maze m) {
		int row = this.getRow();
		int col = this.getCol();

        if (row < 0 || row >= m.getHeight() || col < 0 || col >= m.getWidth()) {
            return false;
        }
        return true;
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

		if (typeOfNode == NodeType.path){
			return ".";
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
