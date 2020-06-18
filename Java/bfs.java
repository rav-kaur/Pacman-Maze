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
        String filePath = (System.getProperty("user.dir")+"/Java/"+fileName);
        File file = new File(filePath);

        MazeParser parser = new MazeParser(file);
        parser.readFile(m);

        sc.close();
        SolveMaze_BFS(m);
    }

    public static boolean SolveMaze_BFS(Maze m)
	{
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();
        Node start = m.GetStart();
        //Node end = m.GetEnd();

        System.out.println("Start:" + start.getRow() +"," + start.getCol());

        Queue<Node> frontier = new LinkedList<>();
        Set<Node> explored = new HashSet<Node>();

        frontier.add(start);
        while(!frontier.isEmpty()) {
            Node current = frontier.remove();

            if (!current.IsVisited()){

                System.out.println("Node: " + current.GetNodeTypeName() + " ("+current.getRow() + "," +current.getCol()+")");
                
                if (current.GetNodeType() == Node.NodeType.end){
                    rebuildPath(current, path);
                    return true;
                } else {
                    ArrayList<Node> neighbors = m.getNeighbours(current, current.getCol(), current.getRow());
                    for (Node neighbor : neighbors){
                        if (!explored.contains(neighbor) && !neighbor.IsVisited()){
                            //System.out.println("neighbor");
                            frontier.add(neighbor);
                            path.put(neighbor, current);
                        }
                    }
                    current.SetVisited(true);
                    explored.add(current);
                } 
                //i++;
            }
            
            
        }
        return false;
    }

    public static void rebuildPath(Node current, Hashtable<Node, Node> path){
        LinkedList<Node> finalPath = new LinkedList<>();

        Node parent = path.get(current);
        finalPath.push(current);
        System.out.println(current.GetNodeTypeName());

        while (parent != null){
            finalPath.push(parent);
            System.out.println(parent.GetNodeTypeName());
            parent = path.get(parent);
        }

        System.out.println(finalPath.size());


    }


}