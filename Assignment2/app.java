import java.io.*;
public class app {
		static int lineCounter;
		static int line;
	public static void main(String args[]) throws IOException{
		BufferedReader bf1 = new BufferedReader(new FileReader("Output_withoutError.txt"));
		BufferedReader bf2 = new BufferedReader(new FileReader("Output_case.txt"));
		String line1 = bf1.readLine();
		String line2 =  bf2.readLine();
		while(line1 != null){
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
 				 if(line1.equals(line2)){
						line1 = bf1.readLine();
						line2 = bf2.readLine();
						line++;
				 }
				 else{
					lineCounter++;
					line++;
					System.out.println(line+ " "+ line1+ " "+ line2) ;
					line1 = bf1.readLine();
					line2 = bf2.readLine();
					
				 }

		}

		System.out.println(lineCounter);
		bf1.close();
		bf2.close();


	
	   
		
	
	}
}

