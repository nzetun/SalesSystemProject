package list;

/**
 * This class holds the properties of
 * a list of type <T> node.
 * 
 * @author jbargen and nzetocha
 *
 * @param <T>
 */
public class ListNode<T> {

	private ListNode<T> next;
	private T item;
	
	/**
	 * Creates an instance of a list node.
	 * 
	 * @param item
	 */
	public ListNode(T item) {
        this.item = item;
        this.next = null;
    }

	/**
	 * Gets the item from the node.
	 */
    public T getListItem() {
        return item;
    }

    /**
     * Gets the next node.
     */
    public ListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     * 
     * @param next
     */
    public void setNext(ListNode<T> next) {
        this.next = next;
    }
    
    /**
     * Confirms that there is a next node.
     */
    public boolean hasNext() {
    	return (this.next != null);
    }
	
    
    
}
