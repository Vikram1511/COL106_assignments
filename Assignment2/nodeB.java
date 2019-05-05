import java.util.ArrayList;
class nodeB{
		private nodeTree reference;
		private nodeB leftchild;
		private nodeB rightchild;
		private nodeB parent;
		private String elem;
		private int level;
		
		
		
		public void setReference(nodeTree refr) {
			this.reference = refr;
		}
		public nodeTree getReference() {
			return this.reference;
		}
		public void setLevel(int i) {
			this.level =  i;
		}
		public int getLevel() {
			return this.level;
		}
		
		public void setParent(nodeB p) {
			this.parent= p;
		}
		public nodeB getParent() {
			return this.parent;
		}
		public void setElement(String i) {
			this.elem = i;
		}
		public String getElement() {
			return this.elem;
		}
		public void set_leafChilds() {
			
			nodeB leftLeaf= new nodeB();
			nodeB rightLeaf = new nodeB();
			this.leftchild = leftLeaf;
			this.rightchild = rightLeaf;
			leftLeaf.setParent(this);
			rightLeaf.setParent(this);
			
		}
		
		public void setLeftChild(nodeB p) {
			this.leftchild = p;
			
		}
		
		
		
		public void setRightChild(nodeB t) {
			this.rightchild = t;
		}
		
		public nodeB getRightChild() {
			return this.rightchild;
		}
		public nodeB getLeftChild() {
			return this.leftchild;
		}
		public ArrayList<nodeB> getChildren() {
			ArrayList<nodeB> arr = new ArrayList<nodeB>(2);
			arr.add(this.getLeftChild());
			arr.add(this.getRightChild());
			return arr;
			
		}
		
	
		public nodeB() {
			this.leftchild=null;
			this.rightchild=null;
			this.parent=null;
		}
		
		
		public nodeB(String i) {
			this.elem = i;
			this.leftchild=null;
			this.rightchild=null;
			this.parent = null;
		}
		
		
		
	}

