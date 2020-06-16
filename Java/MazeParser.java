import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;



public class MazeParser {
    public static void main(String[]args){
        Maze m = new Maze();
        Scanner sc = new Scanner(System.in);
        
        String fileName;

        System.out.print("Input File Name: ");
        fileName = sc.nextLine().trim();
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Node> nL = new ArrayList<Node>();

        char line;

        while((line = br.read()) != -1){
            char c = line;
            
        }



    }
}