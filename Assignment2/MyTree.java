import java.util.*; 
import java.io.*;
public class MyTree {
	
    private nodeTree root;
	private int size = 0;
	public BinaryTree BST= new BinaryTree();
	public static int lineCounter=0;
	static int countErrorLine;
	public static DLinkedList<String> FileNames= new DLinkedList<String>();
    

	public void setRootNode(String p) {
		nodeTree root1 = new nodeTree(p);
		root1.setLevel(1);
		BST.addNode(p);
		this.root = root1;
		BST.getRoot().setReference(root);
	}
	


	public nodeTree getRootNode(){
		return this.root;
	}
	


	
	public void addElement(String n, String p) {
	
		nodeTree tempNode = new nodeTree(n);
		if(root==null) {
			setRootNode(p);
			root.setChildlist();
			root.addChildNode(tempNode);
			tempNode.setParentNode(root);
			tempNode.setLevel(2);
			BST.addNode(n,tempNode);
			size++;
			
		}
		else {
			nodeTree Boss = searchForNode(p);
		   if(root.getElement()==p && root.getChildList()==null) {
			root.setChildlist();
			root.addChildNode(tempNode);
			tempNode.setParentNode(root);
			tempNode.setLevel(2);
			BST.addNode(n,tempNode);
			size++;
			
		}
		else if(Boss.getChildList()==null && Boss != root){
			Boss.setChildlist();
			Boss.addChildNode(tempNode);
			tempNode.setParentNode(Boss);
			tempNode.setLevel(Boss.getLevel()+1);
			BST.addNode(n, tempNode);
			size++;
			
		}
		else if(root.getElement()==p && root.getChildList()!=null) {
			root.addChildNode(tempNode);
			tempNode.setParentNode(root);
			tempNode.setLevel(2);
			BST.addNode(n,tempNode);
			size++;
			
		}
		else {
			Boss.addChildNode(tempNode);
			tempNode.setParentNode(Boss);
			tempNode.setLevel(Boss.getLevel()+1);
			BST.addNode(n, tempNode);
			size++;
		
		}
		}
		
		}
	





	public String children(String p){
		nodeTree current = searchForNode(p);
		String s = "";
		if(current.getChildList()!=null) {
			for(int i=0;i< current.sizeChildren();i++) {
				s = s+current.getChildList().getIndex(i).getElement() + " ";
			}
			return s;
	     }
		else {
			s=s+" this nodeB does not have any child nodes";
			return s;
		}
}	
	





	
	public nodeTree searchForNode(String s) {
			nodeB found = BST.searchNode(s);
			if(found==null) {
				return null;
			}
			else {
			return found.getReference();
	        }
	}
	
	
	


	
	public boolean isInternal(String p) {
		nodeTree temp = searchForNode(p);
		return temp.getChildList()!=null;
		
	}
	



	
	public boolean isExternal(String p) {
		nodeTree temp = searchForNode(p);
		return temp.getChildList()==null;
	}
	


	public nodeTree getParent(String n){
	 nodeTree s = searchForNode(n);
	 return s.getParentNode();
	}
	
	


	public void delete(String n,String p) {
		nodeTree tempNode1= searchForNode(p);
		nodeTree tempNode2= searchForNode(n);
		for(int i=0;i<tempNode2.sizeChildren();i++) {
			tempNode2.getChildList().getIndex(i).setParentNode(tempNode1);
			if(tempNode1.getChildList()==null) {
				tempNode1.setChildlist();
				tempNode1.addChildNode(tempNode2.getChildList().getIndex(i));
			}
			else {
			tempNode1.addChildNode(tempNode2.getChildList().getIndex(i));
			}
		}
		tempNode2.getParentNode().getChildList().remove(tempNode2);
		BST.remove(n);
		size--;
		
	}
	



	public void LowestCommonBoss(String s1, String s2) throws IOException{
		BufferedWriter writer ;
		writer  = new BufferedWriter(new FileWriter("Output.txt",true));
		nodeTree found1 = searchForNode(s1);
		nodeTree found2 = searchForNode(s2);
		if(found1.getLevel()==found2.getLevel()) {
				while(found1!=found2) {
					found1 = found1.getParentNode();
					found2 = found2.getParentNode();
				}
				System.out.println(found1.getElement());
				writer.write(found1.getElement());
		}
		else if(found1.getLevel()<found2.getLevel()) {
			while(found2.getLevel()!=found1.getLevel()) {
				found2 = found2.getParentNode();
			}
			if(found1==found2) {
				System.out.println(found1.getParentNode().getElement());
				writer.write(found1.getParentNode().getElement());
			}
			else {
				while(found1!=found2) {
					found1 = found1.getParentNode();
					found2 = found2.getParentNode();
				}
				System.out.println(found1.getElement());
				writer.write(found1.getElement());
				
			}
		}
		else {
			while(found1.getLevel()!=found2.getLevel()) {
				found1 = found1.getParentNode();
			}
			if(found1==found2) {
				System.out.println(found1.getParentNode().getElement());
				writer.write(found1.getParentNode().getElement());
			}
			else {
				while(found1!=found2) {
					found1 = found1.getParentNode();
					found2 = found2.getParentNode();
				}
				System.out.println(found1.getElement());
				writer.write(found1.getElement());
				
			}
			
		}
		writer.write("\n");
		writer.close();
		
	}
	
	
	/*public void postOrder(nodeTree nodeB,int i) {
		nodeTree current = nodeB;
		if(current.getLevel()<i) {
			if(current.getChildList()!=null){
				for(nodeTree child : current.getChildList()) {
					
					postOrder(child,i);
					
				
				}
			
			}
			System.out.println(current.getElement());
			return;
		}
		
	}*/
	/*
		public void printEmployees(BufferedWriter bw) throws IOException {
	     	DLinkedList<nodeTree> Queue1 = new DLinkedList<nodeTree>();
		    nodeTree current = getRootNode();
		    Queue1.addElements(current);
		    while(true) {
			     int nodeCounts = Queue1.size();
			    if(nodeCounts==0) {
				     break;
			    }
			   while(nodeCounts>0) {
		     	    current = Queue1.header.next.elem;
		     	     bw.write(current.getElement() + " ");
		     	     Queue1.removeFirst();
	
				  for(int i =0;i< current.sizeChildren();i++) {
					  Queue1.addElements(current.getChildList().getIndex(i));
				   }
				    nodeCounts--;
		     	
			    }
			  bw.write("\n");
			
			
			
		}
		bw.close();
			
		
		} 
*/



  public void printEmployees() throws IOException {
	     BufferedWriter writer ;
	     writer  = new BufferedWriter(new FileWriter("Output.txt",true));
		DLinkedList<nodeTree> Queue1 = new DLinkedList<nodeTree>();
		nodeTree current = getRootNode();
		Queue1.addElements(current);
			while(current!=null) {
				for(int i =0;i< current.sizeChildren();i++) {
					Queue1.addElements(current.getChildList().getIndex(i));
				}
				writer.write(current.getElement());
				writer.write("\n");
				Queue1.removeFirst();
		     	current = Queue1.header.next.elem;
		     	
		     	
			}
			writer.close();
		}
	


	public MyTree() {
		
		this.root = null;
		this.size=0;
		
	}





	
	public static void  main(String[] args) throws NumberFormatException, IOException  {
		MyTree tree1 = new MyTree(); 
		
	
	 try {
		   
		   String line;
		   int num_Commands;
		   int numEmployees;
			BufferedWriter writer1;
		   BufferedReader reader  = new BufferedReader(new FileReader(args[0]));
		   numEmployees  =  Integer.parseInt(reader.readLine());
		   lineCounter++;
		   line = reader.readLine();
		   while(line!=null && line.split(" ").length==2) {
			   	String[] words = line.split(" ");
			   	if(words.length==2) {
			   			tree1.addElement(words[0], words[1]);
			   	}
			   	line = reader.readLine();
			   	lineCounter++;
		   	}
		   	if(line !=null) {
		   				num_Commands = Integer.parseInt(line);
		   				line = reader.readLine();
		   				lineCounter++;
		   				while(line!=null) {
		   					if(line.split(" ").length==3) {
		   						String[] words  = line.split(" ");
		   						if(words[0].compareTo("0")==0) {
		   							try {
										   tree1.addElement(words[1], words[2]);
										   line = reader.readLine();
		   								numEmployees++;
		   								lineCounter++;
		   							}
		   							catch(NullPointerException nl) {
		   								line=reader.readLine();
		   								lineCounter++;
		   								System.out.println(lineCounter);
		   								countErrorLine++;
		   								continue;
		   							}
					
		   						}
		   						else if(words[0].compareTo("1")==0) {
		   							try {
										   tree1.delete(words[1], words[2]);
									//	  tree1.BST.postOrder(tree1.BST.getRoot());
		   								line = reader.readLine();
		   								lineCounter++;
		   								numEmployees--;
		   							}
		   							catch(NullPointerException nl) {
		   								line=reader.readLine();
		   								lineCounter++;
		   								System.out.println(lineCounter);
		   								countErrorLine++;
		   								continue;
		   							}
					   
		   						}
		   						else {
		   							try {
										   tree1.LowestCommonBoss(words[1], words[2]);
		   								   line = reader.readLine();
		   								   lineCounter++;
		   							}
		   							catch(NullPointerException nl) {
		   								line=reader.readLine();
		   								lineCounter++;
		   								System.out.println(lineCounter);
		   								countErrorLine++;
		   								continue;
									   }
									   
		   						}
				   
				   
		   					}
		   					else {
		   						
								 //FileNames.addElements("Output_File"+"_"+(FileNames.size()+1)+".txt");
								//writer1 = new BufferedWriter(new FileWriter("Output.txt",true));
							    //tree1.BST.postOrder(tree1.BST.getRoot(),writer1);
								tree1.printEmployees();
								line = reader.readLine();
		   						lineCounter++;
								//writer1.close();
		   					}
			   
		   				}
		   	}
		   	reader.close();
		     
		   
		  	}
			catch(FileNotFoundException e) {
					throw new FileNotFoundException();
			} 
			 System.out.println(countErrorLine);
			
			 
	 		
		
		
	

}
}


