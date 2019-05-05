
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
