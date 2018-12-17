/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.*;

public class Test{

    public static void main(String[] args) {
    
	
		String lok[]={"small1.csv","small2.csv","medium1.csv","medium2.csv","big1.csv","big2.csv"};
	
		
		int i=5;
		
			String direktori= "C:\\Users\\nyoman\\Desktop\\OKH\\datasets\\"+lok[i];
			File file = new File(direktori); // TODO: read about File Names
			System.out.println("data  : "+lok[i]);
		
		try {
			Scanner inputStream = new Scanner(file);
			
			int jumlahCity=inputStream.nextInt();
			
			
			
			int tes[][]=new int[jumlahCity][2];
			
			int  index=0;
			while (inputStream.hasNext()){
				String ci=inputStream.next();
				String splitCity[]= ci.split(",");
		   
				tes[index][0]=Integer.parseInt(splitCity[0]);
				tes[index][1]=Integer.parseInt(splitCity[1]);
			
				
				index++;
			}
			inputStream.close();
			
			
			for(int k=0;k<jumlahCity;k++){
				for(int j=0;j<jumlahCity;j++){
					
					if(k!=j){
						if(tes[k][0]==tes[j][0]){
							if(tes[k][1]==tes[j][1])System.out.println(tes[k][0]+" "+tes[k][1]);
						}
							
					}

				
				}	
			
			}


			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
}