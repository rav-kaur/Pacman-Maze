/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

import java.util.ArrayList;

public class Maze 
{	

	ArrayList<ArrayList<Node>> maze = new ArrayList<ArrayList<Node>>();
	private Node start;
	private Node end;
	
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
		maze.get(y).get(x).SetVisited(true);
	}

	public void SetStart(Node start){
		this.start = start;
	}

	public Node GetStart(){
		return this.start;
	}

	public void SetEnd(Node end){
		this.end = end;
	}

	public Node GetEnd(){
		return this.end;
	}

<<<<<<< HEAD
=======
	public int getHeight(){
		return maze.size();
	}

	public int getWidth(){
		return maze.get(0).size();
	}

	public void setPath(Node current){
		current.SetNode(Node.NodeType.path);
	}

>>>>>>> Ravneet
	public ArrayList<Node> getNeighbours(Node currentNode, int x, int y){
		
		Node top, bottom, left, right;

		ArrayList <Node> neighbours = new ArrayList<Node>();

		if ((y-1) >= 0){
			top = maze.get(y-1).get(x);
			neighbours.add(top);
			top.setParent(currentNode);
		}
		if ((y+1) < maze.size()){
			bottom = maze.get(y+1).get(x);
			neighbours.add(bottom);
			bottom.setParent(currentNode);
		}
		if ((x-1) >= 0){
			left = maze.get(y).get(x-1);
			neighbours.add(left);
			left.setParent(currentNode);
		}
		if ((x+1) < maze.get(0).size()){
			right = maze.get(y).get(x+1);
			neighbours.add(right);
			right.setParent(currentNode);
		}
		return neighbours;
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
		System.out.println("");
	}
	
}