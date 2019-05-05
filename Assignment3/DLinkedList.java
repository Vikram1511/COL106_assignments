
 public class DLinkedList<E> {
	
	protected Node<E> header = new Node<E>();
	protected Node<E> trailer  =  new Node<E>();
	protected int length = 0;
	
	public void addElements(E elem) {
		Node<E> t = new Node<E>(elem);
		if (header.next == trailer || trailer.prev == header) {
			header.next = t;
			trailer.prev = t;
	        t.next = trailer;
	        t.prev = header;
	        length++;
			
		}
		
		else {
			Node<E> temp = trailer.prev;
			temp.next = t;
			t.prev = temp;
			t.next = trailer;
			trailer.prev = t;
			length++;
			
		}
	}

	public void addNode(Node<E> node){
		if (header.next == trailer || trailer.prev == header) {
			header.next = node;
			trailer.prev = node;
	        node.next = trailer;
	        node.prev = header;
	        length++;
			
		}
		
		else {
			Node<E> temp = trailer.prev;
			temp.next = node;
			node.prev = temp;
			node.next = trailer;
			trailer.prev = node;
			length++;
			
		}

	}


	public void delete(Node<E> node){
		Node<E> temp1= node.prev;
		Node<E> temp2 = node.next;
		temp1.next  = temp2;
		temp2.prev = temp1;
		length--;
	}

	
	public void insertBeginning(E elem) {
		Node<E> t = new Node<E>(elem);
		Node<E> first = header.next;
		//Node<E> afterFirst = first.next;
		header.next = t;
		t.prev = header;
		t.next = first;
		first.prev = t;
		length++;
	}
	
	
	public E removeFirst() {
		Node<E> first = header.next;
		Node<E> afterFirst = first.next;
		header.next = afterFirst;
		afterFirst.prev = header;
		length--;
		return first.elem;
	   
		
		}
	
	public int size() {
		return length;
	}
	
	public E getIndex(int i) {
		Node<E> current  = header; 
		for(int index = 0; index <= i;index++) {
			current = current.next;
		}
		return current.elem;
	
		}
   
	
	
	
	
	public void toPrint() {
		Node<E> Current = header;
		String  s= "DLinkedList[ ";
		for(int i = 0;i < size();i++) {
			Current = Current.next;
			s = s + Current.elem + " ";
			
		}
		s = s + "]";
		System.out.println(s);
		}
	
	
	public void insertAtIndex(E elem ,int i) {
		Node<E> t = new Node<E>(elem) ;
		Node<E> current = header;
		for(int m=0;m<i;m++ ) {
			current = current.next;
		}
		Node<E> afterCurrent =current.next; 
		current.next = t;
		t.prev = current;
		t.next = afterCurrent;
		afterCurrent.prev = t;
		length++;
		
	}
	
	public void remove(E p) {
		Node<E> current = header;
		while(current.elem != p ) {
			current = current.next;
		}
		Node<E> tempNode1  = current.prev;
		Node<E> tempNode2 = current.next;
		tempNode1.next = tempNode2;
		tempNode2.prev = tempNode1;
		current=null;
		length = length-1;
		
		
	}
	
	public E removeLast() {
		Node<E> t = trailer.prev;
		Node<E> u = t.prev;
		u.next = trailer;
		trailer.prev = u;
		length--;
		return t.elem;
	}

	public DLinkedList() {
		header.next = trailer;
		trailer.prev = header;
	}	
	
	public static void main(String[] args) {
		DLinkedList<String> l1 = new DLinkedList<String>();
		l1.addElements("Ram");
		l1.addElements("Mphan");
		l1.addElements("Bill");
		l1.addElements("Sam");
		l1.addElements("mak");
		l1.remove("Ram");
		l1.toPrint();
		//System.out.println(l1.toPrint());
	}
	

	}



class Node<E>{
	protected Node<E> prev;
	protected Node<E> next;
	protected E elem;
	protected Node(E elem ) {
		this.elem = elem;
		this.prev = null;
		this.next = null;
		
	}
	protected Node() {
		this.prev = null;
		this.next = null;
		
	}
	

}
