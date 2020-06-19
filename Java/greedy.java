import java.io.*;
import java.util.*;



public class greedy {

    

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
        SolveMaze_Greedy(m);
        m.PrintMaze();

    }

    public static void SolveMaze_Greedy(Maze m)
	{
        Hashtable <Node, Node> path = new Hashtable <Node, Node>();
        Node start = m.GetStart();
        Node endNode = m.GetEnd();

        int countExplored = 0;
        
        PriorityQueue<Map.Entry<Node, Integer>> frontier = new PriorityQueue<>((a, b)->b.getValue()-a.getValue());

        frontier.offer(new AbstractMap.SimpleEntry<>(start, 0));
        
        while(!frontier.isEmpty()) {
        	
            Node current = frontier.poll().getKey();
            
            
            if (!current.IsVisited()){
                
                if (current.GetNodeType() == Node.NodeType.end){
                    System.out.println("Explored: " + countExplored);
                    rebuildPath(current, path);
                    return;
                } else {
                    ArrayList<Node> neighbors = m.getNeighbours(current, current.getCol(), current.getRow());
                    
                    
                    for (Node neighbor : neighbors){
                       
                    	if (!neighbor.IsVisited() && neighbor.GetNodeType() != Node.NodeType.wall)
                    	{
                        	frontier.offer(new AbstractMap.SimpleEntry<>(neighbor, neighbor.StraighLineDistance(endNode)));
                            path.put(neighbor, current);
                        }
                    }
                    
                    current.SetVisited(true);
                    countExplored++;
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