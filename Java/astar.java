import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**This class contains functions that will parse through a maze and perform an A star search algorithm to find the 
 * most optimal path from the start node to the end node
 */
public class astar {

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
            aStarSearch(m);
            m.PrintMaze();
        }


        /**
         * This function will perform an A star search on the maze to find a solution from start to end
         * @param m maze
         */
        public static void aStarSearch(Maze m){

            Node start = m.GetStart();
            Node end = m.GetEnd();
            ArrayList<Node> neighbours;
            Hashtable <Node, Node> path = new Hashtable <Node, Node>();

            /** Priority queue organized by shortest possible distance (inital path + heuristic) */
            PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return  Integer.compare(getHeuristic(n1, end, start), getHeuristic(n2,end, start));
                }
            });
            /** Explored keeps track of all nodes that are reached */
            Set<Node> explored = new HashSet<Node>();

            frontier.add(start);
            while(!frontier.isEmpty()){
                Node current  = frontier.poll();
                explored.add(current);

                /** Goal node is found */
                if(current.GetNodeType()== Node.NodeType.end){
                    System.out.println("Explored Nodes: "+ explored.size());
                    rebuildPath(current, path);
                    return;
                }
                /**Get all neighbours for current node and add them to frontier if they have not already been visited and are a space */
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

        /**
         * This function takes in current, start and end nodes and returns the shortest path from start to current + current to end
         * @param current 
         * @param end 
         * @param start
         * @return
         */
        public static int getHeuristic(Node current, Node end, Node start){
            int startPath = Math.abs(current.getCol()-start.getCol())+Math.abs(current.getRow()-start.getRow());
            int heuristic = Math.abs(current.getCol()-end.getCol())+Math.abs(current.getRow()-end.getRow()); 
            return startPath + heuristic;
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

