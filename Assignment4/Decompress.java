import java.util.*;
import java.io.*;
public class Decompress{

	static int MAX_SIZE = (int)(Math.pow(2,16)+1);
	static nodeDict[] dict = new nodeDict[MAX_SIZE];
	static int size=0;
	
	static int getIndex(byte[] arr){
		String b1 = Integer.toBinaryString(arr[0]);
		String b2 = Integer.toBinaryString(arr[1]);
		while(b1.length()<8){
			b1 = "0"+b1;
		}
		if(b1.length()==32){
			b1 = b1.substring(24,32);
		}
		while(b2.length()<8){
			b2="0"+b2;
		}
		if(b2.length()==32){
			b2=b2.substring(24,32);
		}
		String result = b1+b2;
		int num = Integer.parseInt(result,2);
		return num;

	}

	static boolean isPresent(int index){
		if(dict[index]==null){
			return false;
		}
		else{
			return true;
		}

	}

	static String getString(int code){
		if(dict[code]==null){
			return null;
		}
		else{
			return dict[code].getKey();
		}

	}
	static void decompress(String FileInput,String FileOutput) throws FileNotFoundException, IOException{
		BufferedWriter os = new BufferedWriter(new FileWriter(FileOutput,true));
		for(byte i=0;i>=0;i++){
			byte[] code = new byte[2];
			code[0] = 0;
			code[1] = i;
			nodeDict temp = new nodeDict(Character.toString((char)i),code);
			dict[i]= temp;
			size++;
		}
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(FileInput));
		buffer.read();
		int current = buffer.read();
		
        int c = 0;
        int next_ind = 128;
       	os.write(Character.toString((char)current));
        while((c = buffer.read()) != -1) {
            byte[] arr = new byte[2];
            arr[0] = (byte)c;
            arr[1] = (byte)buffer.read();
            // byte[] code_val = get8bitArray(arr);
            int prev = current;
            int index = getIndex(arr);
            current = index;
            if(isPresent(index)){
            	os.write(dict[index].getKey());
            	String p = getString(prev);
            	String curr = getString(current).substring(0,1);
            	String rs = p+curr;
            	nodeDict tempr = new nodeDict(rs);
            	if(next_ind<MAX_SIZE){
            		dict[next_ind] = tempr;
            	}
            	next_ind++;
            	size++;
            }
            else{
            	String p = getString(prev);
            	String curr = getString(prev).substring(0,1);
            	String res = p+curr;
            	os.write(res);
            	dict[next_ind]=new nodeDict(res);
            	current = next_ind;
            	next_ind++;
            	size++;
            }





            
  

			}
			os.close();
			buffer.close();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		decompress(args[0],args[1]);
		
	}



}