import java.util.Scanner;
//import java.util.ArrayList;
import java.io.*;

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



    }

    void SolveMaze_BFS(Maze m)
	{
        
	}

    
    
}