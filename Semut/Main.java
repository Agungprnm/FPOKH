import Ants.TravelingSalesman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		System.out.println("------------------ANT COLONY OPTIMIZATION------------------");
		
	
		
		
		
		Scanner sc=new Scanner(System.in);
		String lok[]={"small1.csv","small2.csv","medium1.csv","medium2.csv","big1.csv","big2.csv","hiddeninstance1.csv","hiddeninstance2.csv"};
		
		int i=sc.nextInt();
			
			
				System.out.println("Data : "+lok[i]+"\n\n");
		   
/*
			 int ants    = 100;          // Number of ants to run per generation.
            int gen     = 100;          // Number of generations.
            double evap = 0.1;          // Evaporation rate of pheromones.
            int alpha   = 1;            // Impact of pheromones on decision making.
            int beta    = 5;            // Impact of distance on decision making.
*/
				int ants    = 1;         
				int gen     = 1;          
				double evap = 0.08;         
				int alpha   = 1;            
				int beta    = 13;   

				
				String lokasi=lok[i];
				TravelingSalesman travelingSalesman = new TravelingSalesman(ants, gen, evap, alpha, beta, lokasi);
				boolean test=true;
				
				while(test){
					try{
						travelingSalesman.run();
						test=false;
					}
					catch(Exception e){
						System.out.println("ngulang");
					}
				}
				
				
				
			}
			
		
		
		
	



}
