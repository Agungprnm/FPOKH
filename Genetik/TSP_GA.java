/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.*;
 import java.io.PrintWriter;

public class TSP_GA {

    public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String lok[]={"small1.csv","small2.csv","medium1.csv","medium2.csv","big1.csv","big2.csv","hiddeninstance1.csv","hiddeninstance2.csv"};
		
		
		int i=sc.nextInt();
		
		String nh="hasil "+lok[i];
		
		PrintWriter pw = null;
			try {
				pw = new PrintWriter(new File(nh));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			StringBuilder builder = new StringBuilder();
			
			
			builder.append("data "+lok[i]);
			builder.append(",algoritma genetik\n");
			builder.append("no percobaan,");
			builder.append("solusi awal,");
			builder.append("solusi ahir,");
			builder.append("rute terbaik,");
			builder.append("waktu\n");
			
			
		
		
				String direktori= "C:\\Users\\nyoman\\Desktop\\OKH\\datasets\\"+lok[i];
				File file = new File(direktori); // TODO: read about File Names
					
			double rataRata=0;
			double terbaik=0;
			double terburuk=0;
			
			try {
				TourManager.destinationCities.clear();
				Scanner inputStream = new Scanner(file);
				
				int jumlahCity=inputStream.nextInt();
				
				City city[]=new City[jumlahCity];
				
				
				int  index=0;
				while (inputStream.hasNext()){
					String ci=inputStream.next();
					String splitCity[]= ci.split(",");
				String xx1=Integer.toString(index+1);
					city[index]=new City(Integer.parseInt(splitCity[0]),Integer.parseInt(splitCity[1]), xx1);
				
					
					TourManager.addCity(city[index]);
					index++;
				}
				inputStream.close();
			
				long startTime=0, endTime=0;
			
			for(int ul=1;ul<6;ul++){
				
			builder.append(ul+",");

				// Initialize population
				startTime = System.currentTimeMillis();
				Population pop = new Population(510, true);
				builder.append(pop.getFittest().getDistance()+",");
			
			
				// Evolve population for 100 generations
				pop = GA.evolvePopulation(pop);
				boolean tesss=true;
				for (int k = 0; k < 100; k++) {
					pop = GA.evolvePopulation(pop);
					
					
						if(System.currentTimeMillis()-startTime>1000){
							 endTime = System.currentTimeMillis();
							 tesss=false;
							break;
						}
					
				}
				if(tesss) endTime = System.currentTimeMillis();
				
				// Print final results
				String sem="";
				String xxx=pop.getFittest().toString();
			xxx=xxx.substring(1,xxx.length()-1);
			xxx=xxx.replace("|"," -> ");
				
				for(int sd=1;sd<12;sd++){
					if((xxx.substring(sd,sd+1)).equals("-")){
					xxx=xxx+" -> "+xxx.substring(0,sd);break;}
				}
				
				
				
				
				double hasil=pop.getFittest().getDistance();
				
				rataRata=rataRata+hasil;
				
				if(ul==1){
					terbaik=pop.getFittest().getDistance();
					terburuk=terbaik;
				}	
				else{
					if(hasil<terbaik)terbaik=hasil;
					if(hasil>terburuk)terburuk=hasil;
				}
				builder.append(hasil+",");
				builder.append(xxx+",");
				builder.append((endTime - startTime)+"\n");
				
				System.out.println("Finished");
				
				
			}
			
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
}