package IO;

import java.io.InputStream;
import Graph.*;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.*;
import java.io.InputStream;

/**
 * Helper class for reading the bays29 data set and converting it to a graph.
 */
public class Import {

    /**
     * Read the specified data set and return a graph based on the set.
     * @return          the graph representing the data set
     */
    public static Graph getGraph (double evaporationRate, int alpha, int beta, String lokasi) {
			
			
			String direktori= "C:\\Users\\nyoman\\Desktop\\OKH\\datasets\\"+lokasi;
			File file = new File(direktori); // TODO: read about File Names
		
		int numOfCities=0;
		   Vertex[] vertices= new Vertex[numOfCities];
		try {
			Scanner inputStream = new Scanner(file);
			
			numOfCities=inputStream.nextInt();
			System.out.println(numOfCities);
			vertices = new Vertex[numOfCities];
			
			int  index=0;
			
			while (inputStream.hasNext()){
				String ci=inputStream.next();
				String splitCity[]= ci.split(",");

				 int x = (int)Double.parseDouble(splitCity[0].trim());
				int y = (int)Double.parseDouble(splitCity[1].trim());
				
				Vertex v = new Vertex(Integer.toString(index+1), x, y);
				vertices[index] = v;
				
				index++;
			}
			
			inputStream.close();
		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     

   

        Graph graph = new Graph(evaporationRate, alpha, beta);

        // Create the spine of the graph (the vertices).
        for (int i = 0; i < numOfCities; i++) {
            graph.addVertex(vertices[i]);
        }

        // Create the edges of the graph (connect every vertex to each other).
        for (Vertex v : graph) {
            for (int i = 0; i < numOfCities; i++) {
                if (vertices[i] != v) {
                    graph.addEdge(v, vertices[i]);
                }
            }
        }

        return graph;
    }

    /**
     * Removes duplicate what spaces in a String.
     * Example: "   2  3  3,2   " becomes " 2 3 3,2 "
     * @param s     the String to parse
     * @return      the String minus the duplicate white spaces
     */
    private static String removeWhiteSpace (String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i-1) == ' ') {
                if (i != s.length()) {
                    s = s.substring(0, i) + s.substring(i+1, s.length());
                    i--;
                } else {
                    s = s.substring(0, i);
                    i--;
                }
            }
        }
        return s;
    }

    /**
     * Read from a file and load it to a String.
     * @param fileName  the name of the file to read (within the same root as this class)
     * @return          a String with the contents of the file
     */
    private static String read (String fileName) {
        InputStream stream = Import.class.getResourceAsStream(fileName);
        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
