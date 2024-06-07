import java.util.*;
import java.io.*;

/**
 * This class generates random graphs with given numbers of nodes and edges,
 * and writes them to text files in a specified directory.
 */
public class RandomGraphGenerator {

    private static final String DATA_DIR =  "../data/";
    private static final String FILENAME_FORMAT = DATA_DIR + "Data_%d_%d.txt";
    private static final Random RANDOM = new Random();
    public ArrayList<String[]> FileNames = new ArrayList<>();

    /**
     * Generates random graphs with different numbers of nodes and edges,
     * and writes them to text files in the specified directory.
     */
    public void run() {
        int[] V_arr={50,60,70,80,90,100,120,130,150,200};
        int[] E_arr={100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1500,1600,1700};
        ArrayList<String> List = new ArrayList<>();

        Random R = new Random();
        String n ="";
        for(int v:V_arr) {
            for (int e:E_arr) {
                int counter=0;

                try{
                    FileWriter fWriter = new FileWriter(String.format(FILENAME_FORMAT,v,e));
                    String[] list={String.format(FILENAME_FORMAT,v,e),Integer.toString(v),Integer.toString(e)};
                    FileNames.add(list);
                    while(counter!=e) {
                        String node1="Node"+R.nextInt(v);
                        String node2="Node"+R.nextInt(v);
                        if (counter==0) {
                            List.add("Node0"+" "+"Node3");
                            fWriter.write("Node0"+" "+"Node3"+" "+R.nextInt(10)+"\n");
                            counter++;
                        }
                        if (!node1.equals(node2) && !List.contains(node1+" "+node2)) {
                            List.add(node1+" "+node2);
                            fWriter.write(node1+ " "+node2+" "+R.nextInt(10)+"\n");
                            counter++;
                        }

                    }
                    fWriter.close();
                    List.clear();
                } catch(IOException o){System.out.println(o);}
            }
        }
    }

    /**
     * The main method creates a new instance of the RandomGraphGenerator class
     * and runs its `run` method.
     * @param args the command-line arguments
     */
    public static void main(String ... args) {
        RandomGraphGenerator c = new RandomGraphGenerator();
        c.run();
    }
}
