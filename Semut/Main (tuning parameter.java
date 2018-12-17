import Ants.TravelingSalesman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		System.out.println("------------------ANT COLONY OPTIMIZATION------------------");
		
		String lok[]={"small1.csv","small2.csv","medium1.csv","medium2.csv","big1.csv"};
		
		double tblokal[][]=new double[5][2];
		double tb[][]=new double[5][2];
		
		for(int x=0;x<5;x++){
			tblokal[x][0]=100000;
			tb[x][0]=100000;
			tblokal[x][1]=100000;
			tb[x][1]=100000;
		}
		
		
		
				int hasil=0;
				double terbaik=0;
				int ants    = 80;         
				int gen     = 50;          
				double evap = 0.08;         
				int alpha   = 9;            
				int beta    = 11;   
		
		

				
				
			
		
		for(int aa=0;aa<10;aa++){
			System.out.print("iterasi ke : "+aa);
			
			gen=gen+10;
			
			for(int i =0;i<5;i++){
				System.out.println("Data : "+lok[i]+"\n\n");
		   

			

				
				String lokasi=lok[i];
				TravelingSalesman travelingSalesman = new TravelingSalesman(ants, gen, evap, alpha, beta, lokasi);
				
					hasil=travelingSalesman.run();
					
				tb[i][1]=hasil;				
				
				if(tblokal[i][1]>hasil){
						tblokal[i][1]=hasil;
						tblokal[i][0]=gen;
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
						terbaik=gen;
			}
			
			
		/*	if(index>2){
				for(int kj=0;kj<5;kj++){
						tb[kj][0]=tb[kj][1];
					
				}
						terbaik=ants;
			}
			*/
			
			
		}	
		
		for(int yy=0;yy<5;yy++)
			System.out.println("data : "+lok[yy]+"jumlah gen terbaik : "+tblokal[yy][0]);
		
		System.out.println("gen terbaik : "+terbaik);
		System.out.println("-------------------------COMPLETE--------------------------");
    }



}
