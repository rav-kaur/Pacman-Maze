import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import java.util.*;


/**This class contains functions that will parse through a maze and perform an Depth-First Search algorithm to find the 
 * a path from the start node to the end node
 */
public class dfs {

    public static void main(String[]args) throws Exception {
        Maze m = new Maze();
        Scanner sc = new Scanner(System.in);
        
        String fileName;

        // takes file name as user input
        System.out.println("Input File Name: ");
        fileName = sc.nextLine().trim();
        String filePath = (System.getProperty("user.dir")+"/"+fileName);
        File file = new File(filePath);

        // parses the file
        MazeParser parser = new MazeParser(file);
        parser.readFile(m);

        sc.close();

        // solves the maze using bfs algorithm
        SolveMaze_DFS(m);
        m.PrintMaze();

    }

    /**
     * Depth-First Search Algorithm that takes a Maze (by reference) in as a parameter 
     * and uses the dfs algorithm to find a solution from the start node to end node
     * @param m - Maze that needs to be solved
     */
    public static void SolveMaze_DFS(Maze m)
	{
        // keeps track of the path that is explored in bfs algorithm
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();
        
        Node start = m.GetStart();

        Stack<Node> nodeStack = new Stack<>(); // keeps track of future nodes to explore
        Set<Node> explored = new HashSet<Node>(); // keeps track of nodes already explored

        nodeStack.add(start);
        while(!nodeStack.isEmpty()) {
            Node current = nodeStack.pop();

            if (!current.IsVisited()){
                
                /**
                 * if the current node is the end node -- it will start to rebuild path
                 * using backtracking and a hastable named path which keeps track of the 
                 * path explored
                 */
                if (current.GetNodeType() == Node.NodeType.end){
                    System.out.println("Explored Nodes: "+ explored.size());
                    rebuildPath(current, path);
                } else {
                    
                    explored.add(current);
                    // finds all 4 neighbors of the current node
                    ArrayList<Node> neighbors = m.getNeighbours(current, current.getCol(), current.getRow());
                    for (Node neighbor : neighbors){
                        // only adds if it's not already explored and if it's not a wall
                        if (!explored.contains(neighbor) && neighbor.GetNodeType() != Node.NodeType.wall){
                            nodeStack.add(neighbor);
                            path.put(neighbor, current);
                        }
                    }
                    current.SetVisited(true); // sets the current to visited so it's not checked again
                } 
            }
        }
    }

    /**
     * This function back tracks and rebuilds the path from the end node to the start node
     * @param current - the end of the maze
     * @param path - keeps track of the path that solveMaze_DFS follows (node, parent)
     */
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