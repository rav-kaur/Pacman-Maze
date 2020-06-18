import java.util.ArrayList;
import java.io.*;



public class MazeParser {

    File file;
    public MazeParser(){}

    public MazeParser(File file){
        this.file = file;
    }

    public void readFile (Maze m) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Node> nL;

        String line;
        int y = 0;

        while((line = br.readLine()) != null){
            
            nL = new ArrayList<Node>();
            

            for (int i = 0; i< line.length(); i++){
                if (line.charAt(i) == ' '){
                    Node current = new Node (Node.NodeType.space, i, y);
                    nL.add(current);
                    current.SetVisited(false);
                } else if (line.charAt(i)== '%'){
                    Node current = new Node (Node.NodeType.wall, i, y);
                    nL.add(current);
                    current.SetVisited(false);
                } else if (line.charAt(i)== 'P'){
<<<<<<< HEAD
                    Node start = new Node (Node.NodeType.start, i, y);
                    nL.add(start);
                    m.SetStart(start);
                } else if (line.charAt(i)== '.'){
                    Node end = new Node (Node.NodeType.end, i, y);
                    nL.add(end);
                    m.SetEnd(end);
=======
                    Node current = new Node (Node.NodeType.start, i, y);
                    nL.add(current);
                    m.SetStart(current);
                    current.SetVisited(false);
                } else if (line.charAt(i)== '.'){
                    Node current = new Node (Node.NodeType.end, i, y);
                    nL.add(current);
                    m.SetEnd(current);
                    current.SetVisited(false);
>>>>>>> Ravneet
                }
    

            }
            m.PushArrayNode(nL);
            y++;
        }
        br.close();


    }
}