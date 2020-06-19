
import java.util.Scanner;
import java.util.Stack;
//import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;


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

        m.PrintMaze();
        aStarSearch(m);
    }

    public static void aStarSearch(Maze m) {
        //get starting coordinates
        Stack<Node> parents = new Stack<Node>();
        Node start = m.GetStart();
        Node end = m.GetEnd();
        Node current = start;
        Node next = current;
    
        ArrayList<Node> neighbours;

       int deadEnd = 0;
       int travelled = 1;
       int futurePath = 100000;
       int path=0;
       int count = 0;
       boolean value = true;

        while(value){
            deadEnd = 0;
            /* Push current node onto stack and get neighbours for current node */
            if(!current.IsVisited()){
                parents.push(current);//Push current node onto stack
            }
            current.SetVisited(true); 
            neighbours = m.getNeighbours(current, current.getCol(), current.getRow());
        
            /**
             * This for loop goes through all of the neighbours of current and finds the shortest path to the end point
             */
            for(int i =0;i<neighbours.size();i++){
                Node neighbour = neighbours.get(i);
            
                //If visited, it is probably a previous node
                if(!neighbour.IsVisited()){
                    if(neighbours.get(i).GetNodeTypeName()==" "){  
                        path = (Math.abs(neighbour.getCol()-end.getCol()))+(Math.abs(neighbour.getRow()-end.getRow()))+travelled; 
                        System.out.println("Path: " +path);
                        if(path<futurePath)    {
                            next = neighbour;
                            System.out.println("Next variable: [ "+ next.GetNodeTypeName()+" ]");
                            futurePath = path;
                        }      
                    }
                    else if (neighbour.GetNodeTypeName()=="."){
                        System.out.println("Wooo we made it to the end");
                        value = false;
                        System.out.println("Stack size: "+ parents.size());
                        break;
                    }
                    else if(neighbour.GetNodeTypeName()=="%"){
                        System.out.println("Edge");
                        deadEnd++;
                    }
                }
                else{
                    System.out.println("Old");
                    deadEnd++;
                }
            }
            if (deadEnd<4){
                current = next;
                current.SetNode(Node.NodeType.path);
                travelled++;
            }
            if(deadEnd==4){

                parents.pop(); 
                current.SetNode(Node.NodeType.space); 
                current = parents.peek();
                travelled--;
            }
            // travelled++;
            count++;
            System.out.println("travelled:" + travelled);
            System.out.println("Count: " + count);

            futurePath = 1000000;
            System.out.println("Deadend: " +deadEnd);
            // m.PrintMaze();
        }
        m.PrintMaze();

    }

    public static Node getPath(Node neighbour, Node end, int travelled){
        int path = (Math.abs(neighbour.getCol()-end.getCol()))+(Math.abs(neighbour.getRow()-end.getRow()))+travelled;

        //using manhattan distance

        return neighbour;
    }

    public static void readStack(Stack<Node> parents){
        System.out.println("top of stack: " + parents.peek().GetNodeTypeName());
    }
}