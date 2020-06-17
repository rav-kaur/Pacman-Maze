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
                    Node current = new Node (Node.NodeType.space, i, y);
                    nL.add(current);


                } else if (line.charAt(i)== '%'){
                    Node current = new Node (Node.NodeType.wall, i, y);
                    nL.add(current);
                } else if (line.charAt(i)== 'P'){
                    Node current = new Node (Node.NodeType.start, i, y);
                    nL.add(current);

                } else if (line.charAt(i)== '.'){
                    Node current = new Node (Node.NodeType.space, i, y);
                    nL.add(current);

                }
    

            }
            m.PushArrayNode(nL);
        }
        br.close();


    }
}