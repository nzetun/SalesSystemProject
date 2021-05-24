package item;

/**
 * Class that extends the item class but only to 
 * products.
 * 
 * @author jbargen and nzetocha
 */
public abstract class Product extends Item {
	
	/**
	 * Product constructor
	 * @param code
	 * @param name
	 */
	public Product(String code, String name) {
		super(code, name);
		
	}

	/**
	 * Getter method for item type.
	 */
	public abstract String getType();
	
	/**
	 * Getter method for item price.
	 */
	public abstract double getPrice();
	
	/**
	 * To string method for name of item.
	 */
	public abstract String nameToString();
	
	/**
	 * To string method for sale info of item.
	 */
	public abstract String saleInfoToString();
	
	/**
	 * Abstract method for returning item taxes.
	 */
	public abstract double getTax();
	
	/**
	 * Abstract method for returning item pre total amount.
	 */
	public abstract double getPreTotal();

}

