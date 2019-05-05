import java.io.BufferedWriter;
import java.io.IOException;

public class BinaryTree{
	
	private nodeB root;
	private int size;
	
	
	public void setRoot(String i) {
		nodeB rt = new nodeB(i);
		this.root = rt;
		this.root.set_leafChilds();
		this.root.setLevel(1);
		size++;
	}
	
	
	public nodeB getRoot() {
		return this.root;
	}
	
	/*
	 * This method signature is helping method for general tree to add node and its reference
	 * it takes two parameters
	 */
	
	 
	public void addNode(String i,nodeTree refr) {
		if(root==null) {
		    setRoot(i);
			getRoot().setReference(refr);
		}
		else {
		if(root.getLeftChild().getElement()==null && i.compareTo(root.getElement())<0) {
			root.getLeftChild().setElement(i);
			root.getLeftChild().set_leafChilds();
			root.getLeftChild().setLevel(2);
		    root.getLeftChild().setReference(refr);
		    size++;
			
		}
		else if(root.getRightChild().getElement()==null && i.compareTo(root.getElement())>0) {
			root.getRightChild().setElement(i);
			root.getRightChild().set_leafChilds();
			root.getRightChild().setLevel(2);
			root.getRightChild().setReference(refr);
			size++;
		}
		else {
			nodeB current =root;
			while(current.getElement()!=null) {
				if(i.compareTo(current.getElement())<0) {
					current=current.getLeftChild();
				}
				else {
					current=current.getRightChild();
				}
			}
			
		
			current.setElement(i);
			current.setLevel(current.getParent().getLevel()+1);
			current.set_leafChilds();
			current.setReference(refr);
			size++;
			
		
			}
		}
}
	
	/*
	 * add node for general case
	 */
	

	public void addNode(String i) {
		if(root==null) {
			setRoot(i);
		}
		else {
			if(root.getLeftChild().getElement()==null && i.compareTo(root.getElement())<0) {
				root.getLeftChild().setElement(i);
				root.getLeftChild().set_leafChilds();
				root.getLeftChild().setLevel(2);
				size++;
		
			
			}
			else if(root.getRightChild().getElement()==null && i.compareTo(root.getElement())>0) {
				root.getRightChild().setElement(i);
				root.getRightChild().set_leafChilds();
				root.getRightChild().setLevel(2);
				size++;
		
			}
			else {
				nodeB current =root;
				while(current.getElement()!=null) {
					if(i.compareTo(current.getElement())<0) {
						current=current.getLeftChild();
					}	
					else if(i.compareTo(current.getElement())>=0){
						current=current.getRightChild();
					}
				}
			
		
				current.setElement(i);
				current.setLevel(current.getParent().getLevel()+1);
				current.set_leafChilds();
				size++;
		
			
				}
		}
	}
	
	

	/*
	 * operating Binary Search on the given tree
	 */
	

	public  nodeB searchNode(String i) {
			if(this.root.getElement().equals(i)){
				return this.root;
			}
			else{
				nodeB current = this.root;
				while(current.getElement()!=null) {
					if(i.compareTo(current.getElement())<0) {
						current=current.getLeftChild();
					}
					else if(i.compareTo(current.getElement())>0) {
						current = current.getRightChild();
					}
					if(current.getElement()!=null && i.equals(current.getElement())){
						return current;
					}
					
				}
				return null;
				
			}
		}
	
	
	/*
	 * to get the parent of given node
	 */

	public nodeB Parent(String i) {
		nodeB temp = searchNode(i);
		return temp.getParent();
	}
	
	/*
	 * removing an element from the tree
	 */
	


	public void remove(String i) {
		nodeB deletedNode = searchNode(i);
		if(deletedNode.getLeftChild().getElement()==null && deletedNode.getRightChild().getElement() ==null) {
			deletedNode.setElement(null);
			deletedNode.setReference(null);
			deletedNode.setRightChild(null);
			deletedNode.setLeftChild(null);
			size--;
		}
		else if(deletedNode.getLeftChild().getElement()==null && deletedNode.getRightChild().getElement()!=null) {
			nodeB tempNode_parent = deletedNode.getParent();
			nodeB  tempNode_child = deletedNode.getRightChild();
			if(tempNode_parent.getLeftChild()==deletedNode) {
				tempNode_parent.setLeftChild(tempNode_child);
				tempNode_child.setParent(tempNode_parent);
				deletedNode.setLeftChild(null);
				deletedNode.setReference(null);
				deletedNode = null;
				size--;
			}
			else {
				tempNode_parent.setRightChild(tempNode_child);
				tempNode_child.setParent(tempNode_parent);
				deletedNode.setLeftChild(null);
				deletedNode.setReference(null);
				deletedNode = null;
				size--;
				
			}
			
		}
		else if(deletedNode.getLeftChild().getElement()!=null && deletedNode.getRightChild().getElement()==null) {
			nodeB tempNode_parent = deletedNode.getParent();
			nodeB  tempNode_child = deletedNode.getLeftChild();
			if(tempNode_parent.getLeftChild()==deletedNode) {
				tempNode_parent.setLeftChild(tempNode_child);
				tempNode_child.setParent(tempNode_parent);
				deletedNode.setRightChild(null);
				deletedNode.setReference(null);
				deletedNode = null;
				size--;
			}
			else {
				tempNode_parent.setRightChild(tempNode_child);
				tempNode_child.setParent(tempNode_parent);
				deletedNode.setRightChild(null);
				deletedNode.setReference(null);
				deletedNode = null;
				size--;
				
			}
			
		}
		else {
			nodeB current = deletedNode.getRightChild();
			while(current.getElement()!=null) {
				current = current.getLeftChild();
			}
			nodeB current_parent = current.getParent();
			deletedNode.setElement(current_parent.getElement());
			deletedNode.setReference(current_parent.getReference());
			current_parent.setLeftChild(null);
			current_parent.getParent().setLeftChild(current_parent.getRightChild());
			current_parent.getRightChild().setParent(current_parent.getParent());
			current_parent.setReference(null); 
			current_parent=null;
			size--;
			
			
		}
		
	}
	
	/*
	 * printing post order
	 */
	


	public void postOrder(nodeB node1,BufferedWriter bw) throws IOException {
		nodeB current  =node1;
		 
		if(current.getElement()!=null) {
			for(nodeB child :current.getChildren()) {
				if(child!=null){
						postOrder(child,bw);
				}
			}
			
			bw.write(current.getElement() + " " + current.getReference().getElement());
			bw.write("\n");
		}
		return;
		
		
	}
	
	/*
	 * printing level order
	 */
	


	public void levelOrder(BufferedWriter bw) throws IOException {
		DLinkedList<nodeB> queue1 = new DLinkedList<nodeB>();
		nodeB current = getRoot();
		queue1.addElements(current);
		
		
		while(true) {
			int nodeCount = queue1.size();
			if(nodeCount==0) {
				break;
			}
			while(nodeCount>0){
				 current=queue1.header.next.elem;
				 bw.write(current.getElement()+" ");
				 queue1.removeFirst();
				 
			   
			  if(current.getLeftChild().getElement()!=null){
				  queue1.addElements(current.getLeftChild());
			  }
			  if(current.getRightChild().getElement()!=null){
				  queue1.addElements(current.getRightChild());
			  }
			  
			  nodeCount--;
			
			
		}
			bw.write("\n");
	}
		bw.close();
}
	



	public BinaryTree() {
		this.root=null;
		this.size=0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
