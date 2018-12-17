package Ants;

import Display.*;
import Graph.*;
import IO.*;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.*;
 import java.io.PrintWriter;


/**
 * Solves the Traveling Salesman Problem using vanilla Ant Colony Optimization.
 */
public class TravelingSalesman {

    private Graph graph;
    private int numOfAnts, generations;
	String lokasi;

    /**
     * Construct TravelingSalesman.
     * @param ants          the number of ants to run per generation
     * @param generations   the number of generations to run
     * @param evaporation   the rate of evaporation
     * @param alpha         the impact of pheromones on decision making
     * @param beta          the impact of distance in decision making
     */
    public TravelingSalesman (int ants, int generations, double evaporation, int alpha, int beta,String lokasi) {
        this.numOfAnts = ants;
        this.generations = generations;
		String a=lokasi;
		this.lokasi=lokasi;
        graph = IO.Import.getGraph(evaporation, alpha, beta,a);
    }

    /**
     * Run the algorithm.
     */
    public void run () {
		
		
       // WindowTSP windowTSP = new WindowTSP(graph.getVertices());

	   	PrintWriter pw = null;
			try {
				pw = new PrintWriter(new File(lokasi));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			StringBuilder builder = new StringBuilder();
			
			
			builder.append("data "+lokasi);
			builder.append(",algoritma ant colony\n");
			builder.append("no percobaan,");
			builder.append("solusi awal,");
			builder.append("solusi ahir,");
			builder.append("rute terbaik,");
			builder.append("waktu\n");
	   
	   		
		double rataRata=0;
		double terbaik=0;
		double terburuk=0;
		
		for(int ul=1;ul<6;ul++){
				
			
			builder.append(ul+",");
		
			Ant bestAnt = null;
			int bestEval = 0;

			//delay(1000); // Allow WindowTSP to load.
			final  long startTime = System.currentTimeMillis();
			long kira=0;
			for (int i = 0; i < generations; i++) {
				
				Ant[] ants = createAnts(numOfAnts);
				
			
				
				Ant ant = travel(ants);
				if(System.currentTimeMillis()-startTime>26000)break;
				updatePheromones(ants);

				if (bestAnt == null) {
					bestAnt = ant;
					bestEval = ant.eval();
				} else if (ant.eval() < bestEval) {
					bestAnt = ant;
					bestEval = ant.eval();
				}
				if(i==0)builder.append(bestEval+",");
				
				//windowTSP.draw(bestAnt.getTour());
				if(System.currentTimeMillis()-startTime>26000)break;
				//System.out.println(System.currentTimeMillis()-startTime);
				if(i==0)kira=System.currentTimeMillis()-startTime;
				
				if(System.currentTimeMillis()-startTime+kira>26000) break;
			}

			
			
			final long endTime = System.currentTimeMillis();
				
				double hasil=bestEval;
				
				rataRata=rataRata+hasil;
				if(ul==1){
					terbaik=hasil;
					terburuk=terbaik;
				}
				if(hasil<terbaik)terbaik=hasil;
				if(hasil>terburuk)terburuk=hasil;
				
				
				builder.append(hasil+",");
				builder.append(bestAnt+",");
				builder.append((endTime - startTime)+"\n");
				
				System.out.println("Finished");
				
				
					
				
		
		}
		
		
				builder.append("Rata-rata :,");
				builder.append((rataRata/5)+",");
				builder.append("Solusi Terbaik :,");
				builder.append(terbaik+",");
				builder.append("Solusi Terburuk :,");
				builder.append(terburuk+"\n");
				pw.write(builder.toString());
				pw.close();
		

				
		
    }

    /**
     * Create ants and put them on random starting positions on the graph.
     * @param quantity  the quantity of ants to create
     * @return          an array of the ants created
     */
    private Ant[] createAnts (int quantity) {
        Ant[] ants = new Ant[quantity];
        for (int i = 0; i < quantity; i++) {
            ants[i] = new Ant(graph);
        }
        return ants;
    }

    /**
     * Let each ant in the input array travel until an entire tour is completed.
     * @param ants      the ants to allow to travel
     * @return          the ant with the best evaluation
     */
    private Ant travel (Ant[] ants) {

        Ant bestAnt = null;
        int bestEval = 0;

        for (Ant ant : ants) {
				
            while (ant.notFinished()) {
                ant.travel();
            }

            if (bestAnt == null) {
                bestAnt = ant;
                bestEval = ant.eval();
            } else if (ant.eval() < bestEval) {
                bestAnt = ant;
                bestEval = ant.eval();
            }
        }

        return bestAnt;
    }

    /**
     * Update the pheromones in the graph based on an array of ants with
     * completed tours.
     * @param ants  the ants that will be used to update the pheromones
     */
    private void updatePheromones (Ant[] ants) {
        for (Ant ant : ants) {
            graph.updatePheromone(ant);
        }
    }

    /**
     * Sleep the thread for a specified amount of time.
     * @param ms    milliseconds to sleep for
     */
    private static void delay (int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
