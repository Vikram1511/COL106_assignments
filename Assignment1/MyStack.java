
import java.util.*;
public class MyStack<E> {
	private DLinkedList<E> S= new DLinkedList<E>();
	private int size = 0;
	
	public void push(E item) {
		
		S.insertBeginning(item);
		size++;
	}
	
	
	public E pop() throws EmptyStackException {
		if(size==0) {
			throw new EmptyStackException();
		}
		else {
		      size = size -1;
		     return  S.removeFirst();
			
		}
		
	}
	
	
	public E peek() throws EmptyStackException {
		if(size==0) {
			throw new EmptyStackException();
		}
		else {
			return S.header.next.elem;
		}
	
	}
	
	
	public Boolean empty() {
		if (S.size() ==0) {
			return true;
		}
		else {
			return false;
		}
		
	}
}


