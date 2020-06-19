import java.util.Scanner;
import java.util.Set;
//import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class astaragain {

        public static void main(String[] args) throws Exception{
    
            Maze m = new Maze();
            Scanner sc = new Scanner(System.in);
            
            String fileName;
    
            System.out.println("Input File Name: ");
            fileName = sc.nextLine().trim();
            String filePath = (System.getProperty("user.dir")+"/"+fileName);
            System.out.println(filePath);
            File file = new File(filePath);
    
            MazeParser parser = new MazeParser(file);
            parser.readFile(m);
    
            sc.close();
    
            m.PrintMaze();
            aStarSearch(m);
            m.PrintMaze();
        }


        public static void aStarSearch(Maze m){

            Node start = m.GetStart();
            Node end = m.GetEnd();
            ArrayList<Node> neighbours;
            Hashtable <Node, Node> path = new Hashtable <Node, Node>();

            // PriorityQueue<Node> frontier = new PriorityQueue<Node>(0, new NodeComparator());
            PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return  Integer.compare(getHeuristic(n1, end, start), getHeuristic(n2,end, start));
                }
            });
            Set<Node> explored = new HashSet<Node>();

            frontier.add(start);
            while(!frontier.isEmpty()){
                Node current  = frontier.poll();
                explored.add(current);
                // System.out.println("Node: " + current.GetNodeTypeName() + " ("+current.getRow() + "," +current.getCol()+")");


                if(current.GetNodeType()== Node.NodeType.end){
                    System.out.println("Explored Nodes: "+ explored.size());
                    System.out.println("Yay we made it to the end");
                    rebuildPath(current, path);
                    return;
                }

                neighbours = m.getNeighbours(current, current.getCol(), current.getRow());
                for(Node neighbour:neighbours){
                    if (!neighbour.IsVisited() && neighbour.GetNodeType()!=Node.NodeType.wall){
                        if (!frontier.contains(neighbour)|| !explored.contains(neighbour)){
                            frontier.add(neighbour);
                            path.put(neighbour,current);
                        }
                        else if(frontier.contains(neighbour)){
                            frontier.remove(neighbour);
                        }
                    }
            
                    
                }   
                current.SetVisited(true);

            }


            
        }

        public static int getHeuristic(Node current, Node end, Node start){
            int startPath = Math.abs(current.getCol()-start.getCol())+Math.abs(current.getRow()-start.getRow());
            int heuristic = Math.abs(current.getCol()-end.getCol())+Math.abs(current.getRow()-end.getRow()); 
            return startPath + heuristic;
        }
        
        public static void rebuildPath(Node current, Hashtable<Node, Node> path){
            LinkedList<Node> finalPath = new LinkedList<>();
    
            Node parent = path.get(current);
            finalPath.push(current);
            current.SetNode(Node.NodeType.path);
            //System.out.println(current.GetNodeTypeName());
    
            while (parent != null && parent.GetNodeType()!= Node.NodeType.start){
                finalPath.push(parent);
                //System.out.println(parent.GetNodeTypeName());
                parent.SetNode(Node.NodeType.path);
                parent = path.get(parent);
            }
    
            System.out.println("Cost: "+ finalPath.size());
    
        }

        
    
}

