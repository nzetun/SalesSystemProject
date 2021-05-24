package list;

import java.util.Iterator;

/**
 * Class for making MggList type iterable with enhanced for
 * loop.
 * 
 * @author nzetocha and jbargen
 *
 * @param <T>
 */
public class ListIterator<T extends Comparable<T>> implements Iterator<T> {
	
	ListNode<T> current;
	
	/**
	 * Sets the iterator for list.
	 * @param list
	 */
	public ListIterator(AdtList<T> list) {
		current = list.getHead();
	}
	
	/**
	 * Returns whether the list has a next
	 * element.
	 */
	public boolean hasNext() {
		return current != null;
	}
	
	/**
	 * Method for moving to the next element in
	 * iterable list.
	 */
	public T next() {
		T t = current.getListItem();
		current = current.getNext();
		return t;
	}
	
	
	
}
