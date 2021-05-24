package item;

/**
 * Class that models a used product type item.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class UsedProduct extends Product {

	private static final String type = "UsedProduct";
	private double basePrice;
	
	/**
	 * Used product item constructor.
	 * @param code
	 * @param name
	 * @param basePrice
	 */
	public UsedProduct(String code, String name, double basePrice) {
		super(code, name);
		this.basePrice = basePrice;
	}
	
	@Override
	/**
	 * Getter method for used product type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter method for used product base price.
	 */
	public double getPrice() {
		return this.basePrice;
	}
	
	@Override
	/**
	 * To string method for used product name as a string.
	 */
	public String nameToString() {
		return getName() + "\n";
	}
	
	/**
	 * To string method for sale info of used product.
	 */
	public String saleInfoToString() {
		return saleInfoToString();
	}

	/**
	 * Getter method for used product tax.
	 */
	public double getTax() {
		return getTax();
	}

	/**
	 * Getter method for used product total price for sale.
	 */
	public double getPreTotal() {
		return getPreTotal();
	}
	
}
