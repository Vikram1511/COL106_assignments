class nodeTree{
		
		private nodeTree parentNode;
		private String elem;
		private int level;
		private DLinkedList<nodeTree> listChild;
		
		
		public nodeTree(){
			
			this.parentNode = null;
			this.listChild = null;
			this.elem = null;
		}
		 
		public nodeTree(String i) {
			this.elem = i;
			this.listChild = null;
			this.parentNode=null;
		}
		public void setLevel(int i) {
			this.level = i;
		}
		
		public int getLevel() {
			return this.level;
		}
		public void setParentNode(nodeTree p) {
			this.parentNode = p;
			
		}
		public nodeTree getParentNode(){
			return this.parentNode;
		}
		
		public void setChildlist() {
			this.listChild = new DLinkedList<nodeTree>();
		}
		
		public DLinkedList<nodeTree> getChildList(){
			return this.listChild;
		}
		
		public void addChildNode(nodeTree p) {
				this.listChild.addElements(p);;
		}
		
		public void setElement(String i) {
			this.elem = i;
		}
		
		public String getElement() {
			return this.elem;
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
