import java.util.*;
import java.io.*;
import java.lang.*;
public class AVLtree<E extends Comparable<? super E>>{
     class avlNode{
        private E elem;
        public DLinkedList<Integer> Objects;
        public int count=1;
        private avlNode leftChild;
        private avlNode rightChild;
        private avlNode parent;
        public Bin refBin;
        public ObjOfBin refObjOfBin;
        public Node<ObjOfBin> ref_in_List;

        public void setElem(E elem){
            this.elem = elem;
        }
 
        public void setParent(avlNode node){
            this.parent = node;
        }
        public avlNode getParent(){
            return this.parent;
        }
        public void setLeftChild(avlNode node){
            this.leftChild = node;
            if(node!=null){
                    node.setParent(this); 
             }
        }
        public void setRightChild(avlNode node ){
            this.rightChild = node;
            if(node!=null){
                 node.setParent(this);
            }
        }
        
        public avlNode getLeftChild(){
            return this.leftChild;
        }
        public E getElem(){
            return this.elem;
        }
        public avlNode getRightChild(){
            return this.rightChild;
        }

        public void setLeafNode(){
            this.setRightChild(new avlNode()) ;
            this.setLeftChild(new avlNode());

        }
        public boolean isRightChild(avlNode p){
                return p.getRightChild()==this;

        }
        public boolean isLeftChild(avlNode p){
            return p.getLeftChild()==this;
         }

        public avlNode(E i){
            this.elem = i;
            this.leftChild=null;
            this.rightChild = null;
        }
        public avlNode(){
            this.elem = null;
        }


    }

    private avlNode root;
    public  avlNode z;
    public avlNode y;
    public avlNode x;

    public avlNode getNode(E i){
        return new avlNode(i);
    }
    public void setRoot(avlNode node){
        this.root = node;
     }
    public avlNode getRoot(){
        return this.root;
    }
    public Boolean isExternal(avlNode node){
        if(node.getRightChild().getElem()==null&& node.getLeftChild().getElem()==null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLeaf(avlNode node){
        return node.getElem()==null;
    }

    public int height(avlNode node){
        if(isLeaf(node)){
            return 0;
        }
        else{
            return 1+ Math.max(height(node.getLeftChild()),height(node.getRightChild()));

        }
        
    }

    public avlNode maxHeight(avlNode node){
        if(height(node.getRightChild())>height(node.getLeftChild())){
            return node.getRightChild();
        }
        else{
            return node.getLeftChild();
        }

    }

    public int depth(avlNode node){
        int depth = 0;
        while(node!=root){
            node = node.getParent();
            depth =depth+1;

        }
        return depth;
    }


    public void add(E i){
        avlNode temp = new avlNode(i);
        if(root==null){
            setRoot(temp);
            getRoot().setLeafNode();
        }
        else{

            avlNode current = root;
            while(current.getElem()!=null){
                if(current.getElem().compareTo(i)>0){
                    current = current.getLeftChild();
                }
               /* else if(current.getElem().compareTo(i)==0){
                    current.count+=1;
                    return;
                }*/
                else{
                    current = current.getRightChild();
                }

            }
            current.setElem(i);
            current.setLeafNode();
            rebalancing(current,true,false);
            


        }
    }


        public void add(avlNode node){
            if(root==null){
                setRoot(node);
                getRoot().setLeafNode();
            }
            else{
    
                avlNode current = root;
                while(current.getElem()!=null){
                    if(current.getElem().compareTo(node.getElem())>0){
                        current = current.getLeftChild();
                    }
                   /* else if(current.getElem().compareTo(i)==0){
                        current.count+=1;
                        return;
                    }*/
                    else{
                        current = current.getRightChild();
                    }
    
                }
                node.setParent(current.getParent());
                if(current.isRightChild(current.getParent())){
                    current.getParent().setRightChild(node);
                }
                else{
                    current.getParent().setLeftChild(node);
                }
                node.setLeafNode();
                rebalancing(node,true,false);
                
    
    
            }

    }


    public boolean isBalance(avlNode node){
            return Math.abs(height(node.getRightChild())-height(node.getLeftChild())) <=1;

    }

    public void link(avlNode parent,avlNode child,boolean left,boolean right){
        if(left=true && right ==false){
            parent.setLeftChild(child);
            child.setParent(parent);
        }
        if(right==true && left ==false){
            parent.setRightChild(child);
            child.setParent(parent);
        }


    }


    public void rebalancing(avlNode node,boolean addition,boolean deletion){
        
        avlNode current  = node;
        while(isBalance(current)){
            if(current==root){
                return;
            }
            else{
                current= current.getParent();
            }
        }
        z = current;
        
        if(addition==true&&deletion==false){
             
             if(node.getElem().compareTo(z.getElem())>=0){
                 y = z.getRightChild();
                if(node.getElem().compareTo(y.getElem())>=0){
                      x = y.getRightChild();
                }
                else{
                    x = y.getLeftChild();
                }
            }
            else{
                y = z.getLeftChild();
                if(node.getElem().compareTo(y.getElem())>=0){
                    x = y.getRightChild();
                }
                else{
                    x = y.getLeftChild();
                 }
            }
        }
        if(addition==false&&deletion==true){
            
            y = maxHeight(current);
            if(height(y.getLeftChild())==height(y.getRightChild())){
                if(y.isRightChild(z)){
                    x= y.getRightChild();
                }
                else{
                    x = y.getLeftChild();
                }
            }
            else{
                x = maxHeight(y);
            }
        }

        if(y.isLeftChild(z) && x.isLeftChild(y)){
                
                avlNode t3 = y.getRightChild();
                
                if(z==root){
                    setRoot(y);
                    link(y,z,false,true);
                    link(y,x,true,false);
                    link(z,t3,true,false);
                }
                else if(z.isRightChild(z.getParent())){
                    link(z.getParent(),y,false,true);
                    link(y,z,false,true);
                    link(z,t3,true,false);
                    if(deletion==true){
                        rebalancing(y,false,true);
                    }
                }
               else{
                 link(z.getParent(),y,true,false);
                 link(y,z,false,true);
                 link(z,t3,true,false);
                 if(deletion==true){
                        rebalancing(y,false,true);
                    }
            }
        }

        else if(y.isRightChild(z) && x.isRightChild(y)){
            
            avlNode t2 =y.getLeftChild();
           
            
            if(z==root){
                    setRoot(y);
                    link(y,x,false,true);
                    link(y,z,true,false);
                    link(z,t2,false,true);
                }
            else if(z.isRightChild(z.getParent())){
                link(z.getParent(),y,false,true);
                link(y,z,true,false);
                link(z,t2,false,true);
                if(deletion==true){
                        rebalancing(y,false,true);
                    }
            }
            else{
                link(z.getParent(),y,true,false);
                link(y,z,true,false);
                link(z,t2,false,true);
                if(deletion==true){
                        rebalancing(y,false,true);
                }
            }
        }
        else if(y.isRightChild(z) && x.isLeftChild(y) ){
            
            avlNode t2 =x.getLeftChild();
            avlNode t3 = x.getRightChild();
            
            if(z==root){
                    setRoot(x);
                    link(x,y,false,true);
                    link(x,z,true,false);
                    link(z,t2,false,true);
                    link(y,t3,true,false);
                }
            else if(z.isRightChild(z.getParent())){
                link(z.getParent(),x,false,true);
                link(x,z,true,false);
                link(x,y,false,true);
                link(z,t2,false,true);
                link(y,t3,true,false);
                if(deletion==true){
                        rebalancing(x,false,true);
                    }
            }
            else{
                link(z.getParent(),x,true,false);
                link(x,z,true,false);
                link(x,y,false,true);
                link(z,t2,false,true);
                link(y,t3,true,false);     
                if(deletion==true){
                        rebalancing(x,false,true);
                    }      
            }

        }

        else{
            
            avlNode t2 =x.getLeftChild();
            avlNode t3 = x.getRightChild();
            if(z==root){
                    setRoot(x);
                    link(x,z,false,true);
                    link(x,y,true,false);
                    link(y,t2,false,true);
                    link(z,t3,true,false);
                  
                }
            else if(z.isRightChild(z.getParent())){
                link(z.getParent(),x,false,true);
                link(x,y,true,false);
                link(x,z,false,true);
                link(z,t3,true,false);
                link(y,t2,false,true);
                if(deletion==true){
                        rebalancing(x,false,true);
                }
               
            }
            else{
                link(z.getParent(),x,true,false);
                link(x,y,true,false);
                link(x,z,false,true);
                link(z,t3,true,false);
                link(y,t2,false,true);
                if(deletion==true){
                        rebalancing(x,false,true);
                }
                
            }
        }




    }




    public avlNode searchForNode(E i,avlNode node) throws NullPointerException{     
        avlNode current = node;
        if(current.getElem()!=null) {
        	if(current.getElem().compareTo(i)==0){
            return current;
        }
        else if(isExternal(current)){
            return null;
        }
        
        else{
            if(current.getElem().compareTo(i)>0){
                return  searchForNode(i,current.getLeftChild());
            }
            else{
                return searchForNode(i,current.getRightChild());
            }
          }
        }
    else {
    	return null;
    }
}
        
        

    

    public void deleteExternal(avlNode node){
                    node.setRightChild(null);
                    node.setLeftChild(null);
                    
                        node.setElem(null);
                   
    }
    public void delete_IfLeftLeaf(avlNode node){
                   avlNode temp = node.getParent();
                    node.setLeftChild(null);
                    node.getRightChild().setParent(node.getParent());
                    if(node.getParent().getRightChild()==node){
                        node.getParent().setRightChild(node.getRightChild());
                    }
                    else{
                        node.getParent().setLeftChild(node.getRightChild());
                    }
                    
                   
                        node=null;
                    
                    rebalancing(temp,false,true);
    }

    public void delete_IfRightLeaf(avlNode node){
                    avlNode temp = node.getParent();
                    node.setRightChild(null);
                    node.getLeftChild().setParent(node.getParent());
                    if(node.getParent().getRightChild()==node){
                        node.getParent().setRightChild(node.getLeftChild());
                    }
                    else{
                        node.getParent().setLeftChild(node.getLeftChild());
                    }
                    
                    
                        node=null;
                   
                    rebalancing(temp,false,true);
    }

    public void delete(avlNode node){
                
                //avlNode node  = searchForNode(i,getRoot());
            //if(node.count==1){
                if(isExternal(node)){
                    
                       deleteExternal(node);
                       rebalancing(node.getParent(),false,true);
                
                   
                }
                else if(!isLeaf(node.getRightChild()) && isLeaf(node.getLeftChild())){
                        
                            delete_IfLeftLeaf(node);
                        
                }

                else if(!isLeaf(node.getLeftChild()) && isLeaf(node.getRightChild())){
                    
                       delete_IfRightLeaf(node);
                   

                }
                else{
                   
                        avlNode  current = node.getRightChild();
                        while(current.getElem()!=null){
                            current = current.getLeftChild();
                        }
                        current = current.getParent();
                        node.setElem(current.getElem());
                        node.refBin = current.refBin;
                        node.refObjOfBin = current.refObjOfBin;
                        if(isExternal(current)){
                            deleteExternal(current);
                            rebalancing(current.getParent(),false,true);
                        }
                        else{
                            delete_IfLeftLeaf(current);
                       }
                   


                }
            }
            /*else{
                node.count--;
            }*/
            

    public void printLevelOrder(){
        DLinkedList<avlNode> queue1 = new DLinkedList<avlNode>();
        avlNode current = getRoot();
        queue1.addElements(current);
        
        
        while(true) {
            int nodeCount = queue1.size();
            if(nodeCount==0) {
                break;
            }
            while(nodeCount>0){
                 current=queue1.header.next.elem;
                 System.out.print(current.getElem()+" ");
                 queue1.removeFirst();
                 
               
              if(current.getLeftChild().getElem()!=null){
                  queue1.addElements(current.getLeftChild());
              }
              if(current.getRightChild().getElem()!=null){
                  queue1.addElements(current.getRightChild());
              }
              
              nodeCount--;
            
            
        }
            System.out.println("");
        }
    }

    public ArrayList<avlNode> iterate(avlNode node){
        ArrayList<avlNode> arr = new ArrayList<avlNode>(2);
        if (node.getLeftChild().getElem()!=null){
            arr.add(node.getLeftChild());
        }
        if(node.getRightChild().getElem()!=null){
            arr.add(node.getRightChild()); 
        }
        return arr; 

    }

    public void preOrder(avlNode node){
        if(node.getElem()!=null){
            System.out.println(node.getElem());
        }
        for(avlNode child : iterate(node)){
            if(!isLeaf(child)){
                preOrder(child);
            }
        }


    }

    public void postOrder(avlNode node){
        for(avlNode child : iterate(node)){
            if(!isLeaf(child)){
                postOrder(child);
            }
            
        }
        System.out.print(node.getElem()+" ");
    }

    
    public void inOrder(avlNode node){
        if(!isLeaf(node.getLeftChild())){
            inOrder(node.getLeftChild());
        }

        System.out.print(node.getElem()+" ");

        if(!isLeaf(node.getRightChild())){
            inOrder(node.getRightChild());
        }

    }

    

    



    public static void  main(String[] args) {
        AVLtree<Integer> tree1 = new AVLtree<Integer>();
       /* tree1.add(10);
        tree1.add(5);
        tree1.add(10);
        tree1.add(5);
        tree1.add(10);
        tree1.add(9);
        tree1.add(9);
        tree1.printLevelOrder();
       System.out.println(tree1.searchForNode(10, tree1.getRoot()));*/
        //System.out.println(tree1.searchForNode(9, tree1.getRoot()).count);
        tree1.add(10);
        tree1.add(5);
        tree1.add(15);
        tree1.add(13);
        tree1.add(12);
        tree1.add(150);
        tree1.add(110);
        tree1.add(55);
        tree1.add(154);
        tree1.add(106);
        tree1.add(45);
        tree1.add(145);
        tree1.add(130);
        tree1.add(51);
        tree1.add(65);
       
        System.out.println(tree1.isBalance(tree1.searchForNode(10,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(13,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(106,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(145,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(130,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(55,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(150,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(12,tree1.getRoot())));
        System.out.println(tree1.isBalance(tree1.searchForNode(106,tree1.getRoot())));
        System.out.println(tree1.getRoot().getElem());
        tree1.printLevelOrder();
        tree1.inOrder(tree1.getRoot());

       

    }

}