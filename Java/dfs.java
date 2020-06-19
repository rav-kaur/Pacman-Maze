import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import java.util.*;



public class dfs {

    public static void main(String[]args) throws Exception {
        Maze m = new Maze();
        Scanner sc = new Scanner(System.in);
        
        String fileName;

        System.out.println("Input File Name: ");
        fileName = sc.nextLine().trim();
        String filePath = (System.getProperty("user.dir")+"/"+fileName);
        File file = new File(filePath);

        MazeParser parser = new MazeParser(file);
        parser.readFile(m);

        sc.close();
        SolveMaze_DFS(m);
        m.PrintMaze();

    }

    public static void SolveMaze_DFS(Maze m)
	{
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();
        Node start = m.GetStart();

        Stack<Node> nodeStack = new Stack<>();
        Set<Node> explored = new HashSet<Node>();

        nodeStack.add(start);
        while(!nodeStack.isEmpty()) {
            Node current = nodeStack.pop();

            if (!current.IsVisited()){
                
                if (current.GetNodeType() == Node.NodeType.end){
                    System.out.println("Explored: " + explored.size());
                    rebuildPath(current, path);
                } else {
                    explored.add(current);
                    ArrayList<Node> neighbors = m.getNeighbours(current, current.getCol(), current.getRow());
                    for (Node neighbor : neighbors){
                        if (!explored.contains(neighbor) && neighbor.GetNodeType() != Node.NodeType.wall){
                            nodeStack.add(neighbor);
                            path.put(neighbor, current);
                        }
                    }
                    current.SetVisited(true);
                    
                    
                } 
            }
            
            
        }
        
    }

    public static void rebuildPath(Node current, Hashtable<Node, Node> path){
        LinkedList<Node> finalPath = new LinkedList<>();

        Node parent = path.get(current);
        finalPath.push(current);
        current.SetNode(Node.NodeType.path);

        while (parent != null && parent.GetNodeType()!= Node.NodeType.start){
            finalPath.push(parent);
            parent.SetNode(Node.NodeType.path);
            parent = path.get(parent);
        }

        System.out.println("Cost: "+ finalPath.size());

    }

}