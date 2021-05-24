package item;

/**
 * Class that models a stores item object and provides
 * getter and file I/O functionality.
 * 
 * @author nzetocha2 and jbargen
 *
 */
public abstract class Item {
	
	private String code;
	private String name;
	
	/**
	 * Items constructor
	 * @param code
	 * @param name
	 */
	public Item(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/**
	 * Getter method for item code.
	 * @return
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Getter method for item name.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for item type.
	 * @return
	 */
	public abstract String getType();
	
	/**
	 * Getter method for item price.
	 * @return
	 */
	public abstract double getPrice();
	
	/**
	 * To string method for name of item.
	 * @return
	 */
	public abstract String nameToString();
	
	/**
	 * To string method for sale info of item.
	 * @return
	 */
	public abstract String saleInfoToString();
	
	/**
	 * Abstract method for returning item taxes.
	 * @return
	 */
	public abstract double getTax();
	
	/**
	 * Abstract method for returning item pre total amount.
	 * @return
	 */
	public abstract double getPreTotal();
	
}