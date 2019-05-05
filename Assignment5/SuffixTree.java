import java.util.*;
import java.io.*;
 

public class SuffixTree{
	static nodeST root = new nodeST();
	public static void main(String[] args) throws FileNotFoundException, IOException{
		BufferedReader bf = new BufferedReader(new FileReader(args[0]));
		String input_line = bf.readLine();
		input_line = input_line+"$";
		char[] array = input_line.toCharArray();
		int length = input_line.length();
		int i = 0;
		for(i=0;i<length;i++){
			insert(input_line.substring(i,length),i,length-1,array);
		} 
		
//		printTree();
		int number_cases = Integer.parseInt(bf.readLine());
		String line = bf.readLine();
		while(line!=null){
			Pattern_Matching(line,input_line);
			line=bf.readLine();
		}
	}


	public static  nodeST checkNode(Integer start_index,char[] array,nodeST node){
		for(int i =0;i<node.getChildList().size();i++){
			nodeST node_temp = node.getChildList().getIndex(i);
			if(node_temp.getStartPoint()!=null) {
					char x = array[node_temp.getStartPoint()];
					if(x==array[start_index]){
						return node.getChildList().getIndex(i);
					}
			}
			else {
				return null;
			}
		}
		return null;
	}

	public static nodeST searchPatternNode(char c,nodeST node,char[] array){
		nodeST current = node;
		if(node.getChildList()!=null) {
			for(int i=0;i<node.getChildList().size();i++){
				if(node.getChildList().getIndex(i).starter_index_leaf==null) {
					char x = array[node.getChildList().getIndex(i).getStartPoint()];
					if(x==c){
					return node.getChildList().getIndex(i);
					}
				}
			}
			return null;
		}
		else {
			return null;

		}
	}
	

	public static nodeST nodeSplit(Integer st_p,Integer new_end_point,nodeST current){
		
		nodeST temp = new nodeST(st_p,new_end_point);
       // nodeST end_label  =new nodeST(st_p);
		temp.setParentNode(current);
		temp.setChildlist(current.getChildList());
		for(int i=0;i<current.getChildList().size();i++) {
			current.getChildList().getIndex(i).setParentNode(temp);
		}
		current.setChildlist(null);
		current.addChildNode(temp);
		current.setEndPoint(st_p-1);
//		current.addChildNode(end_label);
//		end_label.setParentNode(current);
//		System.out.println(temp.getStartPoint()+" "+temp.getEndPoint());
		return current;
	}


	public static void insert(String s, Integer start_index,Integer end_index,char[] array){
		if(root.getChildList()==null){
			nodeST temp = new nodeST(start_index,end_index);
			nodeST end_label = new nodeST(start_index);
			root.addChildNode(temp);
			temp.setParentNode(root);
			temp.addChildNode(end_label);
			end_label.setParentNode(temp);
//			System.out.println(s+" "+temp.getStartPoint() +" "+temp.getEndPoint());
		}
		else{
			nodeST current = checkNode(start_index,array,root);
			if(current==null){
				nodeST temp = new nodeST(start_index,end_index);
				nodeST end_label = new nodeST(start_index);
				temp.setParentNode(root);
				end_label.setParentNode(temp);
				root.addChildNode(temp);
				temp.addChildNode(end_label);
//				System.out.println(s+" "+temp.getStartPoint() +" "+temp.getEndPoint());

			}
			else{
				nodeST ex = null;
				Integer n;
				Integer k;
				Integer n_copy =0;
				Integer k_copy= 0;
				int p = s.length();
				int i = 0;
				while(i<=p-1){
					if(current!=null){
						 n  = current.getStartPoint();
						 k = start_index+i;
						while(n<=current.getEndPoint() && array[n] == array[k]){
							n++;
							k++;
							i++;
							if(i>p-1){
								nodeST node = nodeSplit(n,current.getEndPoint(),current);
								nodeST end_label = new nodeST(start_index);
								node.addChildNode(end_label);
								end_label.setParentNode(node);
//								System.out.println(s+" "+node.getStartPoint() +" "+node.getEndPoint());
								break;
								}
						}
						if(i>p-1){
							break;
						}
						else{
							 ex = current;
							current = checkNode(k,array,current);
							k_copy  = k;
							n_copy = n;
						}

					}
					else{
						nodeST temp = new nodeST(k_copy,end_index);
						if(ex.getEndPoint()-ex.getStartPoint()>0) {
							nodeST c = nodeSplit(n_copy,ex.getEndPoint(),ex);
							c.addChildNode(temp);
							temp.setParentNode(c);
//							System.out.println(s+" "+c.getStartPoint() +" "+c.getEndPoint());
						}
						else {
							ex.addChildNode(temp);
							temp.setParentNode(ex);
//							System.out.println(s+" "+ex.getStartPoint() +" "+ex.getEndPoint());
						}
						nodeST end_label = new nodeST(start_index);
						end_label.setParentNode(temp);
						temp.addChildNode(end_label);
						
						//make changes in print statement
						
						break;

					}
				}

			}
			
		}

	}


//	public static void searchPattern(String pattern,String input,char[] array){
//		int length  = pattern.length();
//		char qustn_mark = '?';
//		char star_mark = '*';
//		char[] pattern_array  = pattern.toCharArray();
//		if(pattern.charAt(0)=="?".toCharArray()[0]){
//			length--;
//			int i=1;
//			while(length!=0){
//				if(pattern.charAt(i)=="?".toCharArray()[0]){
//					i++;
//					length--;
//				}
//				else{
//					break;
//				}
//				
//			}
//			if(length==0){
//				for(int k=0;k<input.length();k++){
//					System.out.println(k+" "+(k+pattern.length()-1));
//				}	
//			}
//			else{
//				//for example "??tt"
//				//write code for this
//				
//
//
//				
//			}
//		}
//		else if(length==1 && pattern.charAt(0)=="*".toCharArray()[0]){
//			for(int i=0;i<input.length();i++){
//				for(int k=i;k<input.length();k++){
//					System.out.println(i+" "+k);
//				}
//			}
//
//		}
//		else{
//			char x = pattern.charAt(0);
//			nodeST current = searchPatternNode(x,root,array);
//			if(current!=null){
//				int pat_ind=1;
//				int j = current.getStartPoint()+1;
//				int currentNode_len = current.getEndPoint()-current.getStartPoint();
//				while(currentNode_len!=0){
//					if(pat_ind<pattern.length() && (pattern_array[pat_ind]==qustn_mark || pattern_array[pat_ind]==array[j])){}
//				}
//			}
//
//
//
//
//		}
//
//
//	}

	public static void Pattern_Matching(String pattern,String input) throws IOException{
		Integer pattern_length;
		char[] input_array = input.toCharArray();
		char pattern_ch;
		char inp_ch;
		char star = '*';
		char qstn_mark = '?';
		nodeST found;
		pattern_length = pattern.length()-1;
		Integer init = 0;
		Integer start_point;
		pattern_ch = pattern.charAt(init);
		Integer input_num = 1;

		//if pattern's 1st char is not "?"
		if(pattern.length()==1 && pattern.charAt(0)=='*'){
			position(input,pattern,root,pattern.length(),true);
		}

		else if((pattern_ch != qstn_mark)){
			found = searchPatternNode(pattern_ch,root,input_array);
			if(found == null){
				input_num = input_num-1;
			}
			else{
				start_point = found.getStartPoint();
				start_point++;
				Integer input_index = start_point;
				init++;
				while((init <= pattern_length) && (input_index <= input.length()-1)){
					pattern_ch = pattern.charAt(init);

					//case when a char is not "?" 

					if(pattern_ch != qstn_mark){
						if(input_index <= found.getEndPoint()){
							inp_ch = input.charAt(input_index);
							if(inp_ch == pattern_ch){
								init++;
								input_index++;
								if(init == pattern_length+1){
									position(input,pattern,found,pattern_length+1,false);
									input_num--;
								}
							}
							else{
								input_num--;
								init = pattern_length+1;
							}
						}
						else{
							if(found.getChildList() == null){
								input_num--;
								init = pattern_length+1;
							}
							else{
								found = searchPatternNode(pattern_ch,found,input_array);
								if(found == null){
									input_num--;
									init = pattern_length+1;
								}
								else{
									init++;
									input_index = found.getStartPoint()+1;
									if(init == pattern_length+1){
										position(input,pattern,found,pattern_length+1,false);
										input_num--;
									}
								}
							}
						}
					}

					//case when a "?" occurs in between the pattern

					else{
						init++;
						if(init == pattern_length+1){
							if(input_index <= found.getEndPoint()){
								position(input,pattern,found,pattern_length+1,false);
								input_num--;
							}
							else if(input_index == found.getEndPoint()+1){
								if(found.getChildList() == null){
									input_num--;
								}
								else{
									position(input,pattern,found,pattern_length+1,false);
									input_num--;
								}
							}
						}
						else{
							if(input_index < found.getEndPoint()){
								input_index++;
							}
							else if(input_index == found.getEndPoint()){
								if(found.getChildList() == null){
									input_num--;
									init = pattern_length+1;
								}
								else{
									pattern_ch = pattern.charAt(init);
									if(pattern_ch == qstn_mark){
										init++;
										if(init == pattern_length+1){
											position(input,pattern,found,pattern_length+1,false);
											input_num--;
										}
										else{
											DLinkedList<nodeST> current_list = new DLinkedList<nodeST>();
											Node<nodeST> temp_now = found.getChildList().header.next;
											while(temp_now != found.getChildList().trailer){
												current_list = check(pattern,init,temp_now.elem,input,current_list);
												temp_now = temp_now.next;
											}
											Node<nodeST> current_list_node = current_list.header.next;
 											while(current_list_node != current_list.trailer){
 												position(input,pattern,current_list_node.elem,pattern_length+1,false);
 												current_list_node = current_list_node.next;
												}
											input_num--;
											init = pattern_length+1;
										}			
									}
									else{
										found = searchPatternNode(pattern_ch,found,input_array);
										if(found == null){
											input_num--;
											init = pattern_length+1;
										}
										else{
											input_index = found.getStartPoint()+1;
											init++;
											if(init == pattern_length+1){
												position(input,pattern,found,pattern_length+1,false);
												input_num--;
											}
										}	
									}	
								}
							}
							else if(input_index == found.getEndPoint()+1){
								if(found.getChildList() == null){
									input_num--;
									init = pattern_length+1;
								}
								else{
									DLinkedList<nodeST> current_list = new DLinkedList<nodeST>();
									Node<nodeST> temp_now = found.getChildList().header.next;
									while(temp_now != found.getChildList().trailer){
										current_list = check(pattern,init,temp_now.elem,input,current_list);
										temp_now = temp_now.next;
									}
									Node<nodeST> current_list_node = current_list.header.next;
 									while(current_list_node != current_list.trailer){
 										position(input,pattern,current_list_node.elem,pattern_length+1,false);
 										current_list_node = current_list_node.next;
										}
									input_num--;
									init = pattern_length+1;
								}				
							}
						}
					}
				}
			}
		}

		//if pattern's 1st char is "?"

		else{
			int i=1;
			int len = pattern.length();
			len--;
			while(len!=0){
				if(pattern.charAt(i)=='?'){
					i++;
					len--;
				}
				else{
					break;
				}
				
			}
			if(len==0){
				position(input,pattern,root,pattern.length(),true);
			}	
			else{

			init++;
			DLinkedList<nodeST> current_list = new DLinkedList<nodeST>();
			Node<nodeST> temp_now = root.getChildList().header.next;
			while(temp_now != root.getChildList().trailer){
				current_list = check(pattern,init,temp_now.elem,input,current_list);
//				System.out.println(current_list.getIndex(current_list.size()-1).getStartPoint());
				temp_now = temp_now.next;
			}
			Node<nodeST> current_list_node = current_list.header.next;
			while(current_list_node != current_list.trailer){
				position(input,pattern,current_list_node.elem,pattern_length+1,false);
				current_list_node = current_list_node.next;
			}
			input_num--;
			init = pattern_length+1;
			}
		}
	}
	
	
	public static DLinkedList<nodeST> check(String pattern,Integer start_point,nodeST node,String input,DLinkedList<nodeST> nodeList){
		char[] input_array = input.toCharArray();
		Integer input_len = input.length()-1;
		Integer pattern_len = pattern.length()-1;
		char qstn_mark = '?';
		char pattern_ch;
		char inp_ch;
		Integer point_pattern = start_point;
		Integer point_input = node.getStartPoint()+1; 
		
		while((point_pattern <= pattern_len) && (point_input <= input_len)){
			pattern_ch = pattern.charAt(point_pattern);
			if(pattern_ch != qstn_mark){
				if(point_input <= node.getEndPoint()){
					    inp_ch = input.charAt(point_input);
					    if(inp_ch == pattern_ch){
						   point_input++;
						   point_pattern++;
						   if(point_pattern == pattern_len+1){
							nodeST temp = new nodeST();
							temp = node;
							nodeList.addElements(temp);;
							return nodeList;
						}
					}
					else{
						return nodeList;
					}
				}
				else{
					if(node.getChildList() == null){
						return nodeList;
					}
					else{
						node = searchPatternNode(pattern_ch,node,input_array);
						if(node == null){
							return nodeList;
						}
						else{
							point_pattern++;
							point_input = node.getStartPoint()+1;
							if(point_pattern == pattern_len+1){
								nodeST temp = new nodeST();
								temp = node;
								nodeList.addElements(temp);
								return nodeList;		
							}
						}
					}
				}
			}
			else{
				point_pattern++;
				
				if(point_pattern == pattern_len+1){
					if(point_input == node.getEndPoint()+1){
						if(node.getChildList() == null){
							return nodeList;
						}
						else{
							nodeST temp = new nodeST();
							temp = node;
							nodeList.addElements(temp);
							return nodeList;		
						}
					}
					else{
						nodeST temp = new nodeST();
						temp = node;
						nodeList.addElements(temp);
						return nodeList;
					}
				}
				else{
					if(point_input < node.getEndPoint()){
						//point_pattern++;
						point_input++;
						if(point_input == input_len+1){
							return nodeList;
						}
						
					}
					else if(point_input == node.getEndPoint()){
						//point_pattern++;
						if(point_pattern == pattern_len+1){
							nodeST temp = new nodeST();
							temp = node;
							nodeList.addElements(temp);
							return nodeList;
						}
						else{
							if(node.getChildList() == null){
								return nodeList;
							}
							else{
								if(pattern.charAt(point_pattern) == qstn_mark){
									point_pattern = point_pattern+1;
									for(int i=0;i<node.getChildList().size();i++) {
										if(node.getChildList().getIndex(i).starter_index_leaf==null) {
											nodeList =  check(pattern,point_pattern,node.getChildList().getIndex(i),input,nodeList); 
										}
									}
									return nodeList;
								}
								else{	
									node = searchPatternNode(pattern.charAt(point_pattern),node,input_array);
									if(node == null){
										return nodeList;
									}
									else{
										point_pattern++;
										point_input = node.getStartPoint()+1;
										if(point_pattern == pattern_len+1){
											nodeST temp = new nodeST();
											temp = node;
											nodeList.addElements(temp);;
											return nodeList;
										}
									}			
								}
							}
						}
					}
					else if(point_input == node.getEndPoint()+1){
						if(node.getChildList() == null){
							return nodeList;
						}	
						else{
							for(int i=0;i<node.getChildList().size();i++) {
								if(node.getChildList().getIndex(i).starter_index_leaf==null) {
									nodeList =  check(pattern,point_pattern,node.getChildList().getIndex(i),input,nodeList); 
								}
							}
							return nodeList;
						}
					}
				}
			}
		}
		                  return nodeList;
	}

	public static void position(String input,String pattern,nodeST node,int n,boolean f) throws IOException{
		
		BufferedWriter writer1 = new BufferedWriter(new FileWriter("small_output.txt",true));
		if(f==false) {
		nodeST current = node;
		DLinkedList<nodeST> Queue1 = new DLinkedList<nodeST>();
		DLinkedList<nodeST> Queue2 = new DLinkedList<nodeST>();
		Queue1.addElements(current);
			while(current!=null ) {
				for(int i =0;i< current.sizeChildren();i++) {
					if(current.getChildList().getIndex(i).starter_index_leaf==null) {
						Queue1.addElements(current.getChildList().getIndex(i));
					}
					if(current.getChildList().getIndex(i).starter_index_leaf!=null) {
						Queue2.addElements(current.getChildList().getIndex(i));
				   }
				
		     	
		     	
		     	
			}
				Queue1.removeFirst();
				current = Queue1.header.next.elem;
		}
		for(int j=0;j<Queue2.size();j++){
			System.out.println(Queue2.getIndex(j).starter_index_leaf+ " " +(Queue2.getIndex(j).starter_index_leaf+(n-1)));
			writer1.write(Queue2.getIndex(j).starter_index_leaf+ " " +(Queue2.getIndex(j).starter_index_leaf+(n-1)));
			writer1.write("\n");
		}
		}
		else {
			if(n==1 && pattern.charAt(0)=='*') {
				for(int i=0;i<input.length();i++){
					for(int k=i;k<input.length();k++){
						System.out.println(i+" "+k);
						writer1.write(i+" "+k);
						writer1.write("\n");
					}
				}
			}
			else {
			for(int k=0;k<input.length()-1;k++){
				int t = k+n-1;
				if(t<input.length()-1) {
					System.out.println(k+" "+ t);
					writer1.write(k+" "+ t);
					writer1.write("\n");
				}
			
			}
		}
	}
		writer1.close();
		
	}
	
	 public static void printTree() throws IOException {
	     // BufferedWriter writer ;
	     // writer  = new BufferedWriter(new FileWriter("Output.txt",true));
		DLinkedList<nodeST> Queue1 = new DLinkedList<nodeST>();
		nodeST current = root;
		Queue1.addElements(current);
			while(current!=null) {
				for(int i =0;i< current.sizeChildren();i++) {
					Queue1.addElements(current.getChildList().getIndex(i));
				}
				if(current.starter_index_leaf==null) {
					System.out.println(current.getStartPoint()+" "+current.getEndPoint());
				}
				else {
					System.out.println(current.starter_index_leaf+" i am leaf");
				}
				Queue1.removeFirst();
		     	current = Queue1.header.next.elem;
		     	
		     	
			}
		}

}

class nodeST{
	    private nodeST parentNode;
		private Integer start_point;
		private Integer end_point;
		public Integer starter_index_leaf;
		private DLinkedList<nodeST> listChild;
		
		
		public nodeST(){
			
			this.parentNode = null;
			this.listChild = null;
		}
		 
		public nodeST(int s){
			this.starter_index_leaf = s;
		}
		public nodeST(Integer s,Integer e) {
			this.start_point=s;
			this.end_point=e;
			this.listChild = null;
			this.parentNode=null;
		}
		
		public void setParentNode(nodeST p) {
			this.parentNode = p;
			
		}
		public nodeST getParentNode(){
			return this.parentNode;
		}
		
		public void setChildlist() {
			this.listChild = new DLinkedList<nodeST>();
		}
		public void setChildlist(DLinkedList<nodeST> x) {
			this.listChild = x;
		}
		
		public DLinkedList<nodeST> getChildList(){
			return this.listChild;
		}
		
		public void addChildNode(nodeST p) {
			if (this.getChildList()!=null){
				this.listChild.addElements(p);;
			}
			else{
				this.setChildlist();
				this.listChild.addElements(p);
			}

		}
		
		public void setStartPoint(Integer s) {
			this.start_point = s;
		}
		public void setEndPoint(Integer e){
			this.end_point=e;
		}
		public Integer getStartPoint() {
			return this.start_point;
		}
		public Integer getEndPoint(){
			return this.end_point;
		}
		public int sizeChildren() {
			if(this.getChildList()!=null) {
					return this.listChild.size();
			}
			else {
				return 0;
			}
			}

}
