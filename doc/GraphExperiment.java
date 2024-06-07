import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * The GraphExperiment class generates random graphs and processes them to record statistics.
 */
public class GraphExperiment {
    
    /**
     * The main method generates random graphs and processes them to record statistics.
     * 
     * @param args an array of command-line arguments
     */
    public static void main(String ... args) {
        
        // Create a RandomGraphGenerator object and run it
        RandomGraphGenerator RGG = new RandomGraphGenerator();
        RGG.run();
        
        // Initialize a String to record the data
        String dataRec = "Vertexs,Edges,V_counter,E_Counter,Q_counter,E.Log(V)\n";
        
        
        // Process each generated graph file
        for (String[] name : RGG.FileNames) {
            
            // Create a new Graph object
            Graph g = new Graph();
            
            try {
                // Open the graph file and read its contents
                FileReader fin = new FileReader(name[0]);
                Scanner graphFile = new Scanner(fin);
                
                // Read each line of the graph file
                String line;
                while (graphFile.hasNextLine()) {
                    line = graphFile.nextLine();
                    StringTokenizer st = new StringTokenizer(line);
                    
                    try {
                        // Parse the source node, destination node, and cost from the line
                        if (st.countTokens() != 3) {
                            System.err.println("Skipping ill-formatted line " + line);
                            continue;
                        }
                        String source = st.nextToken();
                        String dest = st.nextToken();
                        int cost = Integer.parseInt(st.nextToken());
                        g.addEdge(source, dest, cost);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping ill-formatted line " + line);
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }
            
            // Process the graph starting from the "Node0" node
            g.processRequest("Node0");
            
            // Record the data for this graph
            dataRec += name[1] + "," + name[2] + "," + g.V_counter + "," + g.E_counter + "," + g.Q_counter + "," + (int)(Integer.parseInt(name[1]) * Math.log(Integer.parseInt(name[2]))) + "\n";
        }
        
        // Write the recorded data to a CSV file
        try {
            FileWriter output = new FileWriter("../data/Expirement4.csv");
            output.write(dataRec);
            output.close();
        } catch (IOException o) {
            System.out.println("something went wrong..");} 
            
            for(String s:dataRec.split(","))
            {
            System.out.print(s+" ");
            }
        
    }
}
