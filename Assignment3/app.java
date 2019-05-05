import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class app {
		static int lineCounter = 0;
		static int line = 0;
	public static void main(String args[]) throws IOException{
		
		BufferedReader bf2 = new BufferedReader(new FileReader("small_output.txt"));
		String line2 =  bf2.readLine();
		line++;
		while(line2 != null && line!=50){
		/*	 
			String[] words = line1.split(" ");
			if(words[0].equals(words[1])){
				line1 = bf1.readLine();
				lineCounter++;
				continue;

			}
			else{
				System.out.println(lineCounter++);
			}
			*/	
			BufferedReader bf1 = new BufferedReader(new FileReader("output.txt"));
			String line1 = bf1.readLine();
			while(line1!=null) {
				if(line2.equals(line1)) {
					lineCounter++;
					line2 = bf2.readLine();
					line++;
					break;
				}
				else {
					line1 = bf1.readLine();
				}
				
			}
			/*
			 line++;
				
 				 if(line1.equals(line2)){
						line1 = bf1.readLine();
						line2 = bf2.readLine();
						
				 }
				 else {
					lineCounter++;
					System.out.println(line+ " "+ line1+ " "+ line2) ;
					line1 = bf1.readLine();
					line2 = bf2.readLine();
					
				 }

		}*/

			bf1.close();
	}
		System.out.println(lineCounter);
		
		bf2.close();
	
}
}

