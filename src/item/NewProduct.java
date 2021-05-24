package item;

/**
 * Class that models a new product item.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class NewProduct extends Product {

	private static final String type = "NewProduct";
	private double basePrice;
	
	/**
	 * New product item constructor.
	 * @param code
	 * @param name
	 * @param basePrice
	 */
	public NewProduct(String code, String name, double basePrice) {
		super(code, name);
		this.basePrice = basePrice;
	}
	
	@Override
	/**
	 * Getter method for new product type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter method for new product price.
	 */
	public double getPrice() {
		return basePrice;
	}
	
	@Override
	/**
	 * To string method for new product item.
	 */
	public String nameToString() {
		return getName() + "\n";
	}
	
	/**
 	 * To string method for sale info of new product.
	 */
	public String saleInfoToString() {
		return saleInfoToString();
	}

	/**
	 * Getter method for new product tax.
	 */
	public double getTax() {
		return getTax();
	}

	/**
	 * Getter method for new product total price before sale.
	 */
	public double getPreTotal() {
		return getPreTotal();
	}
	
}
