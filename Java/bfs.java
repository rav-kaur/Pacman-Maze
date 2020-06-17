import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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

        m.PrintMaze();


        ArrayList<Node> path = SolveMaze_BFS(m);
        Arrays.toString(path.toArray());

    }

    public static ArrayList<Node> SolveMaze_BFS(Maze m)
	{
        Node start = m.GetStart();
        Node end = m.GetEnd();
        LinkedList<Node> nextToVisit = new LinkedList<>();

        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()){
            Node current = nextToVisit.remove();

            if (current.IsVisited()){
                continue;
            }

            if (current.IsWall()){
                current.SetVisited(true);
                continue;
            }

            if (current == end){
                return pathFinder(current);
            }

            ArrayList<Node> neighbours = m.getNeighbours(current, current.getCol(), current.getRow());
            for (Node neighbour : neighbours){
                nextToVisit.add(neighbour);
                neighbour.SetVisited(true);
            }
        }

        return null;
        

    }
    
    public static ArrayList<Node> pathFinder(Node currentNode){
        ArrayList<Node> path = new ArrayList<Node>();
        Node iter = currentNode;

        while (iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }
        return path;
    }

    
    
}