import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import java.util.*;



public class bfs {

    

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
        SolveMaze_BFS(m);
        m.PrintMaze();

    }

    public static void SolveMaze_BFS(Maze m)
	{
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();
        Node start = m.GetStart();

        Queue<Node> frontier = new LinkedList<>();
        Set<Node> explored = new HashSet<Node>();

        frontier.add(start);
        while(!frontier.isEmpty()) {
            Node current = frontier.remove();

            if (!current.IsVisited()){
                
                if (current.GetNodeType() == Node.NodeType.end){
                    System.out.println("Explored: " + explored.size());
                    rebuildPath(current, path);
                    
                } else {
                    ArrayList<Node> neighbors = m.getNeighbours(current, current.getCol(), current.getRow());
                    for (Node neighbor : neighbors){
                        if (!explored.contains(neighbor) && !neighbor.IsVisited() && neighbor.GetNodeType() != Node.NodeType.wall){
                            frontier.add(neighbor);
                            path.put(neighbor, current);
                        }
                    }
                    current.SetVisited(true);
                    explored.add(current);
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