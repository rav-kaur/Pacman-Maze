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
            y++;
            nL = new ArrayList<Node>();

            for (int i = 0; i< line.length(); i++){
                if (line.charAt(i) == ' '){
                    nL.add(new Node (Node.NodeType.space, i, y));

                } else if (line.charAt(i)== '%'){
                    nL.add(new Node (Node.NodeType.wall, i, y));

                } else if (line.charAt(i)== 'P'){
                    Node start = new Node (Node.NodeType.start, i, y);
                    nL.add(start);
                    m.SetStart(start);
                } else if (line.charAt(i)== '.'){
                    Node end = new Node (Node.NodeType.end, i, y);
                    nL.add(end);
                    m.SetEnd(end);
                }
            }
            m.PushArrayNode(nL);
        }
        br.close();


    }
}