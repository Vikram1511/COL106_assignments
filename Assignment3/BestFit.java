import javafx.util.Pair;
import java.util.*;
import java.io.*;

public class BestFit {
    static AVLtree<Integer> Binstree = new AVLtree<Integer>();
    static AVLtree<Integer> Bins_id_tree = new AVLtree<Integer>();
    static AVLtree<Integer> Object_id_tree = new AVLtree<Integer>();
    public static int lineCounter = 0;
    static int countErrorLine;
    
    

    public void add_bin(int id, int cap) {
        Bin tempBin = new Bin(id, cap);
        AVLtree.avlNode temp_id = Binstree.new avlNode(id);
        AVLtree.avlNode temp_cap = Binstree.new avlNode(cap);
        temp_id.refBin = tempBin;
        temp_cap.refBin = tempBin;
        Binstree.add(temp_cap);
        Bins_id_tree.add(temp_id);

    }

    public int add_object(int id, int size) throws IOException {
        BufferedWriter writer1;
        writer1 = new BufferedWriter(new FileWriter("small_output.txt",true));
        ObjOfBin tempObj = new ObjOfBin(id, size);
        Node<ObjOfBin> listNode = new Node<ObjOfBin>(tempObj);
        AVLtree.avlNode node = searchForBestFit(size, Binstree);
        if (node.refBin.getObjectList() == null) {
            node.refBin.setObjectList();
            node.refBin.getObjectList().addNode(listNode);
        } else {
            node.refBin.getObjectList().addNode(listNode);
        }
        AVLtree.avlNode temp_id = Object_id_tree.new avlNode(id);
        temp_id.refObjOfBin = tempObj;
        temp_id.refBin = node.refBin;
        temp_id.ref_in_List = listNode;
        Object_id_tree.add(temp_id);
        System.out.println(node.refBin.getId());
        writer1.write(((Integer)node.refBin.getId()).toString());
        writer1.newLine();
        writer1.close();
        return node.refBin.getId();
        
        

    }

    public void updateTree(AVLtree.avlNode node, AVLtree tree, int updated_elem) {
        AVLtree.avlNode tempNode = tree.new avlNode(updated_elem);
        tempNode.refBin = node.refBin;
        tempNode.refObjOfBin = node.refObjOfBin;
        tree.delete(node);
        tree.add(tempNode);

    }

    public Integer delete_object(int id) throws IOException {
    	BufferedWriter writer1;
        writer1 = new BufferedWriter(new FileWriter("small_output.txt",true));
        AVLtree.avlNode tempNode_id = Object_id_tree.searchForNode(id, Object_id_tree.getRoot());
        int update_elem = tempNode_id.refBin.getCapacity() + tempNode_id.refObjOfBin.getSize();
        int size = tempNode_id.refObjOfBin.getSize();
        tempNode_id.refBin.getObjectList().delete(tempNode_id.ref_in_List);
        AVLtree.avlNode Bintree_node = BestFit.searchBinNode(tempNode_id.refBin.getCapacity(), tempNode_id.refBin,Binstree, Binstree.getRoot());
        if(Bintree_node!=null) {
        tempNode_id.refBin.setCapacity(tempNode_id.refBin.getCapacity() + size);
        updateTree(Bintree_node, Binstree, update_elem);
        System.out.println(tempNode_id.refBin.getId());
        writer1.write(((Integer)tempNode_id.refBin.getId()).toString());;
        writer1.newLine();
        Object_id_tree.delete(tempNode_id);
        writer1.close();
        return tempNode_id.refBin.getId();
        }
        else {
        	return null;
        }

    }

   

    

    public static AVLtree.avlNode searchBinNode(int i, Bin refbin, AVLtree tree, AVLtree.avlNode current)
            throws NullPointerException {
                AVLtree.avlNode found = tree.searchForNode(i, current);
                if(found!=null){
                    if(found.refBin==refbin){
                        return found;
                    }
                    else{
                         if(found.getLeftChild().getElem()!=null){
                            AVLtree.avlNode x = searchBinNode(i,  refbin,  tree, found.getLeftChild());
                            if(x==null){
                                if(found.getRightChild().getElem()!=null){
                                    return searchBinNode(i,  refbin,  tree, found.getRightChild());
                                }
                            }
                            else{
                                return x;
                            }
                        
                        }
                        else{
                            if(found.getRightChild().getElem()!=null){
                                return searchBinNode(i,  refbin,  tree, found.getRightChild());
                            }
                            else{
                                return null;
                            }
                        }
                    }   
                }
                else{
                    return null;
                }
        return found;
               


    }
    
    public static AVLtree.avlNode searchForBestFit(int size, AVLtree tr) throws IOException{
    	BufferedWriter writer1;
        writer1 = new BufferedWriter(new FileWriter("small_output.txt",true));
            AVLtree tree = tr;
            AVLtree.avlNode current = tree.getRoot();
            while(current.getElem()!=null){
                current = current.getRightChild();
            }
            current = current.getParent();
            if(current.getElem().compareTo(size)>=0){
                    int tempcap = (int)current.getElem()-size;
                    current.refBin.setCapacity(tempcap);
                    AVLtree.avlNode tempNode = Binstree.new avlNode(tempcap);
                    tempNode.refBin = current.refBin;
                    tempNode.refObjOfBin = current.refObjOfBin;
                    tree.delete(current);
                    tree.add(tempNode);
                    return tempNode;
                    
            }
            else{
            	writer1.write("size exceeded");
            	writer1.newLine();
            	writer1.close();
                System.out.println("size exceeded");
                return null;
            }
    }

    public DLinkedList<Pair<Integer,Integer>> contents(int bin_id) throws IOException{
    	BufferedWriter writer1;
        writer1 = new BufferedWriter(new FileWriter("small_output.txt",true));
        DLinkedList<Pair<Integer,Integer>> list = new DLinkedList<Pair<Integer,Integer>>();
        AVLtree.avlNode found = Bins_id_tree.searchForNode(bin_id, Bins_id_tree.getRoot());
        Bin Node_bin = found.refBin;
        if(Node_bin.getObjectList()!=null){
        		Node<ObjOfBin> current = Node_bin.getObjectList().header.next;
              while(current.elem!=null){
                  writer1.write(current.elem.getId()+" " +current.elem.getSize());
                  writer1.newLine();
                  System.out.println(current.elem.getId()+" " +current.elem.getSize());
                    list.addElements(new Pair<Integer,Integer>(current.elem.getId(),current.elem.getSize()));
                    current = current.next;
                }
        }
       writer1.close();
        return list;

    }

    public static void main(String[] args) throws NumberFormatException,NullPointerException,IOException{
        
        BestFit object1 = new BestFit(); 
    
  
        try {
              
              String line;
              BufferedWriter writer1= new BufferedWriter(new FileWriter("small_output.txt",true));
              BufferedReader reader  = new BufferedReader(new FileReader(args[0]));
              line = reader.readLine();
              lineCounter++;
              while(line!=null) {
                    if(line.split(" ").length==3) {
                                String[] words  = line.split(" ");
                                      if(words[0].compareTo("1")==0) {
                                          try {
                                            
                                              object1.add_bin(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                                              line = reader.readLine();
                                              
                                              lineCounter++;
                                          }
                                          catch(NullPointerException nl) {
                                              line=reader.readLine();
                                              lineCounter++;
                                            // System.out.println(lineCounter);
                                              throw nl;
                                          }
                       
                                      }
                                      else if(words[0].compareTo("2")==0) {
                                          try {
                                              object1.add_object(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                                       //   tree1.BST.postOrder(tree1.BST.getRoot());
                                              
                                              line = reader.readLine();
                                              lineCounter++;
                                              
                                          }
                                          catch(NullPointerException nl) {
                                              line=reader.readLine();
                                              lineCounter++;
                                              //System.out.println(lineCounter);
                                              throw nl;
                                          }
                          
                                      }
                    }
                    else if(line.trim().isEmpty()){
                        lineCounter++;
                    
                        line = reader.readLine();
                       
                        
                        

                        
                    }

                    else{   
                         String[] words  = line.split(" ");       
                            if(words[0].compareTo("3")==0) {
                                try {
                                        //System.out.println(object1.Object_id_tree.searchForNode(Integer.parseInt(words[1]),object1.Object_id_tree.getRoot() ).refObjOfBin.getId());
                                        object1.delete_object(Integer.parseInt(words[1]));
                                        
                                        line = reader.readLine();
                                        lineCounter++;
                                       
                                    }
                                    catch(NullPointerException nl) {
                                              line=reader.readLine();
                                              lineCounter++;
                                              throw nl;
                                              //System.out.println(lineCounter);
                                              //countErrorLine++;
                                              //continue;
                                    }
                                          
                                }
                      
                                else {
                                      
                                    //FileNames.addElements("Output_File"+"_"+(FileNames.size()+1)+".txt");
                                   //writer1 = new BufferedWriter(new FileWriter("Output.txt",true));
                                   //tree1.BST.postOrder(tree1.BST.getRoot(),writer1);
                                   try{
                                       /*if(lineCounter==149){
                                        object1.contents(Integer.parseInt(words[1]));
                                       }*/
                                        object1.contents(Integer.parseInt(words[1]));
                                        
                                         line = reader.readLine();
                                        lineCounter++;
                                        
                                   }
                                   catch(NullPointerException nl){
                                    line=reader.readLine();
                                    lineCounter++;
                                  //  System.out.println(lineCounter);
                                    throw nl;
                                   }
                                   //writer1.close();
                                  }
                  
                              }
                  }
                  reader.close();
                  //writer1.close();
                  
                  
                
              
                 }
               catch(FileNotFoundException e) {
                       throw new FileNotFoundException();
               } 
                System.out.println(countErrorLine);
                
               
                
                
           
           
       
   
   
    }

 
    

    
}






