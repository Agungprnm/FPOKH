/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.*;

public class TSP_GA {

    public static void main(String[] args) {
    
	
		String lok[]={"small1.csv","small2.csv","medium1.csv","medium2.csv","big1.csv","big2.csv"};
		
		double terbaik=0;
		double hasil=1000000;
		int mutasi=100;
		double tblokal[][]=new double[5][2];
		double tb[][]=new double[5][2];
		
		
		
		for(int x=0;x<5;x++){
			tblokal[x][0]=100000;
			tb[x][0]=100000;
			tblokal[x][1]=100000;
			tb[x][1]=100000;
		}
		
		
		for(int aa=0;aa<40;aa++){
			
			
			mutasi=mutasi+20;
			System.out.println(mutasi);
			//GA.gantitournamenSize(mutasi);
			
			for(int i=0;i<5;i++){

				String direktori= "C:\\Users\\nyoman\\Desktop\\OKH\\datasets\\"+lok[i];
				File file = new File(direktori); // TODO: read about File Names
				System.out.println("data  : "+lok[i]);
			
			try {
				TourManager.destinationCities.clear();
				Scanner inputStream = new Scanner(file);
				
				int jumlahCity=inputStream.nextInt();
				
				City city[]=new City[jumlahCity];
				
				
				int  index=0;
				while (inputStream.hasNext()){
					String ci=inputStream.next();
					String splitCity[]= ci.split(",");
			   
					city[index]=new City(Integer.parseInt(splitCity[0]),Integer.parseInt(splitCity[1]));
				
					
					TourManager.addCity(city[index]);
					index++;
				}
				inputStream.close();
				
				
				

				// Initialize population
				Population pop = new Population(510, true);
				System.out.println("Initial distance: " + pop.getFittest().getDistance());

				// Evolve population for 100 generations
				pop = GA.evolvePopulation(pop);
				for (int k = 0; k < mutasi; k++) {
					pop = GA.evolvePopulation(pop);
				}

				// Print final results
				
				System.out.println("Finished");
				System.out.println("Final distance: " + pop.getFittest().getDistance());
				System.out.println("Solution:");
				System.out.println(pop.getFittest()+"\n\n\n");
				
				
				hasil=pop.getFittest().getDistance();
				tb[i][1]=hasil;				
				
				if(tblokal[i][1]>hasil){
						tblokal[i][1]=hasil;
						tblokal[i][0]=mutasi;
				
				
				}
			
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		
		double index=0;
			for(int g=0;g<5;g++){
					index=index+tb[g][0]-tb[g][1];

			}
			if(index>0){
				for(int kj=0;kj<5;kj++){
						tb[kj][0]=tb[kj][1];
					
				}
						terbaik=mutasi;
			}
		}
		for(int yy=0;yy<5;yy++)
			System.out.println("data : "+lok[yy]+"jumlah generations terbaik : "+tblokal[yy][0]);
		
		System.out.println("jumlah generations terbaik : "+(terbaik));
		System.out.println("-------------------------COMPLETE--------------------------");
    }
}