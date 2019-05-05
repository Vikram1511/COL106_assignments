import java.util.*;
import java.io.*;
public class Compress{
	static int MAX_SIZE = (int)(Math.pow(2,18)+1);
	static nodeDict[] dict = new nodeDict[MAX_SIZE];
	static int size=0;
	public static void main(String[] args) throws FileNotFoundException,IOException {
		
		compress(args[0],args[1]);
	}



	public static boolean contains(String s){
		if(s.length()==1){
			if(dict[(int)s.toCharArray()[0]].getKey().equals(s)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			int codeHash  = hashCode(s);
			int offset=1;
			int temp_const =codeHash;
			if(dict[codeHash]==null){
				return false;
			}
			else{
				int count=0;
				while(dict[codeHash].getKey().equals(s)==false ){
					codeHash=Math.abs((temp_const+(int)Math.pow(offset,2)))%MAX_SIZE;
					offset++;
					count++;
					if(dict[codeHash]==null){
						return false;
					}
					if(count==MAX_SIZE){
						break;
					}

				}
				if(dict[codeHash].getKey().equals(s)){
					return true;
				}
				else{
					return false;
				}

			}
		}
	}

	public static nodeDict find(String key){
		int index = hashCode(key);
		int offset = 1;
		int temp_const = index;
		int count=0;
		while(dict[index].getKey().equals(key)==false){
			index = Math.abs((temp_const+(int)Math.pow(offset,2)))%MAX_SIZE;
			offset++;
			count++;
			if(count==MAX_SIZE){
				break;
			}
		}
		if(dict[index].getKey().equals(key)==false){
			return null;
		}
		return  dict[index];

	}

	public static byte[] getCodeValues(String key){
		nodeDict f = find(key);
		return  f.getCode();

	}

	public static int hashCode(String s){
		int x = 33;
		int indexCode=0;
		char[] arr= s.toCharArray();
		for(int i=0;i<arr.length;i++){
			indexCode = indexCode+((int)arr[i])*((int)(Math.pow(x,i)));
		}
		return Math.abs((int)(indexCode % MAX_SIZE));
}
	
	public static Integer getHashIndex(String s){ 
		int x = 33;
		int indexCode=0;
		char[] arr= s.toCharArray();
		for(int i=0;i<arr.length;i++){
			indexCode = indexCode+((int)arr[i])*((int)(Math.pow(x,i)));
		}
		int index = Math.abs((int)(indexCode%MAX_SIZE));
		int offset = 1;
		int temp_const = index;
		int count=0;
		while(isOccupied(index) ){
			
			index = Math.abs((temp_const + (int)Math.pow(offset,2))) % MAX_SIZE;
			offset = offset+1;
			count++;
			if(count==MAX_SIZE){
				break;
			}
		}
		if(isOccupied(index)){
			return null;
		}
		return index;
	}

	public static boolean isOccupied(int index){
		if(dict[index]==null){
			return false;
		}
		else{
			return true;
		}


	}

	// public static byte[] setByte(){
	// 	byte[] temp = new byte[2];


	// } 


	static void compress(String strFile,String OutputFile) throws IOException, FileNotFoundException{
		OutputStream os = new FileOutputStream(OutputFile);
		for(byte i=0;i>=0;++i){
			byte[] code = new byte[2];
			code[0]=0;
			code[1]=i;
			nodeDict temp = new nodeDict(Character.toString((char)i),code);
			dict[i]=temp;
			size++;
		}

		byte next_code = -128; 
		byte next_code_0 = 0;
		int round=128;
		BufferedReader buffer = new BufferedReader(new FileReader(strFile));
        int c = 0;
        String s = "";
        while((c = buffer.read()) != -1) {
            char character = (char) c;          
			int i=0;
			s= s+Character.toString(character);
			
			if(size<=(int)Math.pow(2,16)){
				while(contains(s)==true){
					s = s+Character.toString((char)buffer.read());
				}
				os.write(getCodeValues(s.substring(0,s.length()-1)));
					byte[] code=new byte[2];
				if(next_code_0!=256){	
					if(round!=256){
						
						code[0]=next_code_0;
						code[1]=next_code;
						round++;
						if(next_code==127){
							next_code=-128;
						}
						else{
							next_code++;
						}
					}
					else{
						code[0]=next_code_0++;
						code[1] = next_code;
						round++;
						if(next_code==127){
							next_code=-128;
						}
						else{
							next_code++;
						}
					}
					 
					if(getHashIndex(s)!=null){
						int next_index = getHashIndex(s);
						nodeDict temp = new nodeDict(s,code);
						dict[next_index]=temp;
					}
					
					size++;

					s = s.substring(s.length()-1,s.length());
					
					if(round==256){
						round=0;
						next_code=0;
						next_code_0++;
						if(next_code_0==0){

						}
					}

				}
				
			}
			else{
					while(contains(s)==true){
					      s = s+Character.toString((char)buffer.read());
				    }
				    os.write(getCodeValues(s.substring(0,s.length()-1)));
				    s = s.substring(s.length()-1,s.length());

				}
		
	}

	}

}

class nodeDict{
		String key;
		byte[] code_value;

		void setKey(String s){
			this.key=s;
		}
		String getKey(){
			return this.key;
		}

		void setCodeValue(byte[] cd){
			this.code_value=cd;
		}
		byte[] getCode(){
			return this.code_value;
		}

		public nodeDict(String key,byte[] code_value){
			this.key=key;
			this.code_value=code_value;
		}
		public nodeDict(String key){
			this.key=key;
			this.code_value=null;
		}
	}


