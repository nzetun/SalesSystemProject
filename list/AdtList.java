package list;

import java.util.Comparator;
import java.util.Iterator;


/**
 * A linked list implementation for type <T> instances.
 * 
 * @author jbargen and nzetocha
 *
 * @param <T>
 */
public class AdtList<T extends Comparable<T>> implements Iterable<T> {

	private ListNode<T> head;
	private int size;
	private Comparator<T> comparator;
	
	/**
	 * Constructor for list.
	 */
	public AdtList(Comparator<T> cmp) {
		this.head = null;
		this.size = 0;
		this.comparator = cmp;
	}
	
	/**
	 * This function returns the size of the list, the number of elements currently
	 * stored in it.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Method reports if the list is empty or not.
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}

	/**
	 * This function clears out the contents of the list, making it an empty list.
	 */
	public void clear() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * This method adds the given <T> instance to the correct position
	 * in the list using a comparator.
	 * 
	 * @param t
	 */
	public void addInOrder(T t) {		
		if(size == 0) {
			addToStart(t);
		} else {
			ListNode<T> a = new ListNode<T>(t);
			ListNode<T> b = head;
			if(this.comparator.compare(a.getListItem(), b.getListItem()) > 0) {
				ListNode<T> temp = b;
				head = a;
				a.setNext(temp);
				size++;
			} else {
				while((b.getNext() != null) && this.comparator.compare(a.getListItem(), b.getNext().getListItem()) < 0) {
					b = b.getNext();
				}
				a.setNext(b.getNext());
				b.setNext(a);
				size++;
			}
		}
	}
	
	/**
	 * This method adds the given <T> instance to the beginning of the
	 * list.
	 * 
	 * @param t
	 */
	public void addToStart(T t) {
		ListNode<T> newItem = new ListNode<T>(t);
		newItem.setNext(this.head);
		this.head = newItem;
		this.size++;
	}
	
	/**
	 * This method adds the given <T> at the given index.
	 */
	public void addElementAtPosition(T t, int position) {
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds");
		}
		if(position == 0) {
			addToStart(t);
		} else {
			ListNode<T> previous = this.getListNode(position - 1);
			ListNode<T> current = previous.getNext();
			ListNode<T> newItem = new ListNode<T>(t);
			newItem.setNext(current);
			previous.setNext(newItem);
			this.size++;
			return;
		}
	}
	
	/**
	 * This method adds the given <T> instance to the end of the list.
	 * 
	 * @param t
	 */
	public void addToEnd(T t) {
		this.addElementAtPosition(t, this.size);
	}
	
	/**
	 * This is a private helper method that returns a ListNode<T>
	 * corresponding to the given position.
	 * 
	 * @param position
	 */
	private ListNode<T> getListNode(int position) {
		ListNode<T> current = head;
		for(int i=0; i<position; i++) {
			current = current.getNext();
		}
		return current;
	}
	
	/**
	 * This method removes the <T> from the given <code>position</code>,
	 * indices start at 0. Implicitly, the remaining elements' indices are reduced.
	 * 
	 * @param position
	 */
	public void remove(int position) {
		if(position < 0 || position >= this.size) {
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds");
		}
		if(position == 0) {
			ListNode<T> current = this.getListNode(position);
			head = current.getNext();
			this.size--;
		} else {
			ListNode<T> previous = this.getListNode(position - 1);
			ListNode<T> current = previous.getNext();
			previous.setNext(current.getNext());
			this.size--;
		}
	}
	
	/**
	 * Returns the <T> element stored at the given <code>position</code>.
	 * 
	 * @param position
	 * @return
	 */
	public T getElement(int position) {
		if(position < 0 || position >= size) {
			throw new IndexOutOfBoundsException("Index " + position + " is out of bounds");
		}
		ListNode<T> current = this.getListNode(position);
		return current.getListItem();
	}
	
	/**
	 * Method allows for the linked list to be iterated.
	 */
	public Iterator<T> iterator() {
		return new ListIterator<T>(this);
	}
	
	/**
	 * Method to return the head of the linked list.
	 */
	public ListNode<T> getHead() {
		return this.head;
	}
	
	/**
	 * Method to return the tail of the linked list.
	 */
	public ListNode<T> getTail() {
		return getListNode(size-1);
	}
	
	/**
	 * Method to return linked list comparator.
	 */
	public Comparator<T> getComparator() {
		return this.comparator;
	}
	
	
}
