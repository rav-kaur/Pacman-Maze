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
                    nL.add(new Node (Node.NodeType.start, i, y));

                } else if (line.charAt(i)== '.'){
                    nL.add(new Node (Node.NodeType.end, i, y));

                }
            }
            m.PushArrayNode(nL);
        }
        br.close();


    }
}