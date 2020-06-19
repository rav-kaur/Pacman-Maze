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

        // takes file name as user input
        System.out.println("Input File Name: ");
        fileName = sc.nextLine().trim();
        String filePath = (System.getProperty("user.dir")+"/Java/"+fileName);
        File file = new File(filePath);

        // parses the file
        MazeParser parser = new MazeParser(file);
        parser.readFile(m);

        sc.close();

        // solves the maze using bfs algorithm
        SolveMaze_BFS(m);
        m.PrintMaze();

    }

    /**
     * Breath-First Search Algorithm that takes a Maze (by reference) in as a parameter 
     * and uses the bfs algorithm to find a solution from the start node to end node
     * @param m - Maze that needs to be solved
     */
    public static void SolveMaze_BFS(Maze m)
	{
        // keeps track of the path that is explored in bfs algorithm
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();

        Node start = m.GetStart(); // start node

        Queue<Node> frontier = new LinkedList<>(); // keeps track of future nodes to explore
        Set<Node> explored = new HashSet<Node>(); // keeps track of nodes already explored

        frontier.add(start);

        // loops while frontier is not empty
        while(!frontier.isEmpty()) {

            Node current = frontier.remove();

            if (!current.IsVisited()){

                /**
                 * if the current node is the end node -- it will start to rebuild path
                 * using backtracking and a hastable named path which keeps track of the 
                 * path explored
                 */

                if (current.GetNodeType() == Node.NodeType.end){
                    System.out.println("Explored: " + explored.size());
                    rebuildPath(current, path);
                } 
                else {
                    
                    // finds all 4 neighbors of the current node
                    ArrayList<Node> neighbours = m.getNeighbours(current, current.getCol(), current.getRow());

                    for (Node neighbour : neighbours){
                        // only adds if it's not already explored and if it's not a wall
                        if (!explored.contains(neighbour) && !neighbour.IsVisited() && neighbour.GetNodeType() != Node.NodeType.wall){
                            frontier.add(neighbour);
                            path.put(neighbour, current);
                        }
                    }
                    current.SetVisited(true); // sets the current to visited so it's not checked again
                    explored.add(current);
                } 
            }
            
            
        }
        
    }

    /**
     * This function back tracks and rebuilds the path from the end node to the start node
     * @param current - the end of the maze
     * @param path - keeps track of the path that solveMaze_BFS follows (node, parent)
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